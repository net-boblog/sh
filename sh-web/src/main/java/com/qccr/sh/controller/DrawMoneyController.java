package com.qccr.sh.controller;

import com.alibaba.fastjson.JSONObject;
import com.qccr.sh.biz.UserBiz;
import com.qccr.sh.external.carman.DrawMoneyExt;
import com.qccr.sh.external.carman.RewardExt;
import com.qccr.sh.external.carman.bo.*;
import com.qccr.sh.page.PageQuery;
import com.qccr.sh.page.Pagination;
import com.qccr.sh.response.Response;
import com.qccr.sh.util.BT;
import com.qccr.sh.util.FileUtil;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by xianchao.yan on 2015/11/3.
 */
@Controller
@RequestMapping(value = "/page/drawmoney")
public class DrawMoneyController {
    Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private DrawMoneyExt drawMoneyExt;

    @Autowired
    private RewardExt rewardExt;

    @Autowired
    private UserBiz userBiz;

    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request) {

        String userName = request.getRemoteUser();

        Integer storeId = userBiz.getStoreId(userName);

        //可提款金额=订单可提款金额+奖励金额
        boolean isCanDraw = false;
        String reason = "";
        Response<OrderCanDrawBO> ordeCanDraw = drawMoneyExt.getOrderCanDrawMoneySums(storeId);
        Response<BigDecimal> reward = rewardExt.getRewardSums(storeId);
        if (ordeCanDraw.isSuccess() && ordeCanDraw.getData() != null && reward != null && reward.getData() != null) {
            request.setAttribute("orderCanDraw", ordeCanDraw.getData().getSums().add(reward.getData()));
            //是否可以提款
            isCanDraw = ordeCanDraw.getData().isCanDrawMoney();
            reason = ordeCanDraw.getData().getReason();
        }
        //是否可以提款
        request.setAttribute("isCanDraw", isCanDraw);
        request.setAttribute("reason", reason);

        //待审核金额
        Response<BigDecimal> waitAudit = drawMoneyExt.getWaitAuditSums(storeId);
        if (waitAudit.isSuccess() && waitAudit.getData() != null) {
            request.setAttribute("waitAudit", waitAudit.getData());
        }

        //异常金额
        Response<BigDecimal> exception = drawMoneyExt.getExceptionSums(storeId);
        if (exception.isSuccess() && exception.getData() != null) {
            request.setAttribute("exception", exception.getData());
        }

        //提款规则
        Response<DrawMoneyRule> ruleResponse = drawMoneyExt.getDrawMoneyRule(storeId);
        if (ruleResponse.isSuccess() && ruleResponse.getData() != null) {
            DrawMoneyRule drawMoneyRule = ruleResponse.getData();
            String rule = "1. 用户第一次提款需满足" + drawMoneyRule.getFirstAmount() + "元； 2. 每隔"
                    + drawMoneyRule.getTimeCondition() + "天可提取一次，金额需满足" + drawMoneyRule.getPriceCondition() + "元以上。";
            request.setAttribute("rule", rule);

        }

        return "/page/drawmoney/index.jsp";
    }

    @RequestMapping(value = "/drawRecord")
    public void getDrawMoneyRecord(HttpServletRequest request, HttpServletResponse response) {

        String userName = request.getRemoteUser();

        Integer storeId = userBiz.getStoreId(userName);

        String beginTimeStr = request.getParameter("beginTime");
        String endTimeStr = request.getParameter("endTime");
        String statusStr = request.getParameter("status");
        String pageStr = request.getParameter("page");

        Date beginTime = null;
        Date endTime = null;
        Integer status = null;
        int page = 1;

        try {
            String format = "yyyy-MM-dd";
            if (!BT.isEmpty(beginTimeStr)) {
                beginTime = BT.Str2Date(beginTimeStr, format);
            }
            if (!BT.isEmpty(endTimeStr)) {
                java.text.DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date sEndTime = df.parse(endTimeStr);
                Calendar calendar = Calendar.getInstance();//日历对象
                calendar.setTime(sEndTime);//设置当前日期
                calendar.add(Calendar.DATE, 1);//天加1
                endTime = calendar.getTime();
            }
            if (!BT.isEmpty(statusStr)) {
                status = Integer.valueOf(statusStr);
            }
            if (!BT.isEmpty(pageStr)) {
                page = Integer.valueOf(pageStr);
            }

            //查询提款记录
            DrawRecordQueryBO param = new DrawRecordQueryBO();
            param.setStoreId(storeId);
            param.setStartDate(beginTime);
            param.setEndDate(endTime);
            param.setStatus(status);
            PageQuery<DrawRecordQueryBO> pageQuery = new PageQuery<DrawRecordQueryBO>(page, param);
            Response<Pagination<DrawRecordBO>> result = drawMoneyExt.page(pageQuery);
            JSONObject jsonObject = new JSONObject();
            if (result!=null && result.isSuccess()) {

                jsonObject.put("flag", "1");
                jsonObject.put("info", "查询成功");
                        jsonObject.put("data", result.getData());
            } else {
                jsonObject.put("flag", "0");
                jsonObject.put("info", result==null? "查询失败！" : result.getMessage());
                jsonObject.put("data", null);
            }
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(jsonObject.toString());


        } catch (Exception e) {
            log.error(e.getMessage(), e);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("flag", "0");
            jsonObject.put("info", e.getMessage());
            response.setCharacterEncoding("UTF-8");

            try {
                response.getWriter().print(jsonObject.toString());
            } catch (IOException e1) {
                log.error(e1.getMessage(), e1);
            }
        }
    }

    @RequestMapping(value = "/canDrawDetail")
    public String getCanDrawDetail(HttpServletRequest request) {

        String userName = request.getRemoteUser();

        Integer storeId = userBiz.getStoreId(userName);

        //订单金额
        Response<OrderCanDrawBO> ordeCanDraw = drawMoneyExt.getOrderCanDrawMoneySums(storeId);
        //奖励金额
        Response<BigDecimal> reward = rewardExt.getRewardSums(storeId);

            if(ordeCanDraw.isSuccess()){
                request.setAttribute("ordeCanDraw", ordeCanDraw.getData().getSums());
            }

            if(reward.isSuccess()){
                request.setAttribute("reward", reward.getData());
            }

        if (ordeCanDraw.isSuccess() && ordeCanDraw.getData() != null && ordeCanDraw.getData().getSums() !=null && reward.getData() != null) {
            request.setAttribute("canDraw", ordeCanDraw.getData().getSums().add(reward.getData()));
        }
        //setCategory(request);
        return "/page/drawmoney/canDrawDetail.jsp";
    }

    @RequestMapping(value = "/canDrawService")
    public void getCanDrawService(HttpServletRequest request, HttpServletResponse response) {

        String userName = request.getRemoteUser();

        Integer storeId = userBiz.getStoreId(userName);

        String pageStr = request.getParameter("page");
        String oneStr = request.getParameter("oneCategory");
        String twoStr = request.getParameter("twoCategory");

        int page = 1;
        int one = -1;
        int two = -1;

        try {
            if (!BT.isEmpty(pageStr)) {
                page = Integer.valueOf(pageStr);
            }
            if (!BT.isEmpty(oneStr)) {
                one = Integer.valueOf(oneStr);
            }
            if (!BT.isEmpty(twoStr)) {
                two = Integer.valueOf(twoStr);
            }

            DrawDetailQuery param = new DrawDetailQuery();
            param.setStoreId(storeId);
            param.setCategoryOne(one);
            param.setCategoryTwo(two);

            PageQuery<DrawDetailQuery> pageQuery = new PageQuery<DrawDetailQuery>(page, param);

            Response<Pagination<ServiceDetailBO>> queryResponse = drawMoneyExt.getCanDrawMoneyDetail(pageQuery);
             JSONObject jsonObject = new JSONObject();
            if (queryResponse != null && queryResponse.isSuccess()) {
                jsonObject.put("flag", "1");
                jsonObject.put("info", "查询成功");
                jsonObject.put("data", queryResponse.getData());
            } else {
                jsonObject.put("flag", "0");
                jsonObject.put("info",queryResponse==null? "查询失败！" : queryResponse.getMessage());
                jsonObject.put("data", null);
            }
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(jsonObject.toString());
        } catch (Exception e) {

            log.error(e.getMessage(), e);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("flag", "0");
            jsonObject.put("info", e.getMessage());
            response.setCharacterEncoding("UTF-8");
            try {
                response.getWriter().print(jsonObject.toString());
            } catch (IOException e1) {
                log.error(e1.getMessage(), e1);
            }
        }


    }

    @RequestMapping(value = "/drawDetail")
    public String getDrawDetail(HttpServletRequest request) {

        String userName = request.getRemoteUser();

        Integer storeId = userBiz.getStoreId(userName);
        String clearIdStr = request.getParameter("clearId");

        int page = 1;
        int one = -1;
        int two = -1;
        Integer clearId = null;

        try {
            clearId = new Integer(clearIdStr);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        if(clearId == null){
            return "/page/common/error.jsp";
        }

        DrawRecordQuery param = new DrawRecordQuery();
        param.setStoreId(storeId);
        param.setCleraId(clearId);
        param.setCategoryOne(one);
        param.setCategoryTwo(two);

        PageQuery<DrawRecordQuery> pageQuery = new PageQuery<DrawRecordQuery>(page, param);
        //只查一页
        pageQuery.setPageSize(1);

        Response<Pagination<DrawMoneyDetailBO>> queryResponse = drawMoneyExt.detail(pageQuery);

        if (queryResponse.isSuccess() && queryResponse.getData() != null && queryResponse.getData().getData() != null) {
            DrawMoneyDetailBO detailBO = queryResponse.getData().getData();
            request.setAttribute("drawMoney", detailBO.getSum());
            request.setAttribute("orderDrawMoney", detailBO.getOrderSum());
            request.setAttribute("rewardMoney", detailBO.getRewardSum());
            request.setAttribute("createTime", detailBO.getCreateTime());
            request.setAttribute("clearTime", detailBO.getClearTime());
            //是否已经结算
            request.setAttribute("isBalance", 3 == detailBO.getStatus());
        }

        //setCategory(request);

        request.setAttribute("clearId", clearId);

        return "/page/drawmoney/drawDetail.jsp";
    }

    @RequestMapping(value = "/serviceList")
    public void getServiceList(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getRemoteUser();

        Integer storeId = userBiz.getStoreId(userName);
        String clearIdStr = request.getParameter("clearId");

        String pageStr = request.getParameter("page");
        String oneStr = request.getParameter("oneCategory");
        String twoStr = request.getParameter("twoCategory");


        request.setAttribute("pageStr", pageStr);
        request.setAttribute("oneStr", oneStr);
        request.setAttribute("twoStr", twoStr);

        int page = 1;
        int one = -1;
        int two = -1;
        Integer clearId = null;

        try {
            if (!BT.isEmpty(clearIdStr)) {
                clearId = new Integer(clearIdStr);
            }

            if (!BT.isEmpty(pageStr)) {
                page = Integer.valueOf(pageStr);
            }

            if (!BT.isEmpty(oneStr)) {
                one = Integer.valueOf(oneStr);
            }

            if (!BT.isEmpty(twoStr)) {
                two = Integer.valueOf(twoStr);
            }

            DrawRecordQuery param = new DrawRecordQuery();
            param.setStoreId(storeId);
            param.setCleraId(clearId);
            param.setCategoryOne(one);
            param.setCategoryTwo(two);

            PageQuery<DrawRecordQuery> pageQuery = new PageQuery<DrawRecordQuery>(page, param);

            Response<Pagination<DrawMoneyDetailBO>> queryResponse = drawMoneyExt.detail(pageQuery);

            JSONObject jsonObject = new JSONObject();
            if (queryResponse != null && queryResponse.isSuccess()) {

                jsonObject.put("flag", "1");
                jsonObject.put("info", "查询成功");
                jsonObject.put("data", queryResponse.getData());
            } else {
                jsonObject.put("flag", "0");
                jsonObject.put("info", queryResponse==null? "查询失败！" : queryResponse.getMessage());
                jsonObject.put("data", null);
            }
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(jsonObject.toString());
        } catch (Exception e) {
            log.error(e.getMessage(), e);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("flag", "0");
            jsonObject.put("info", e.getMessage());
            response.setCharacterEncoding("UTF-8");
            try {
                response.getWriter().print(jsonObject.toString());
            } catch (IOException e1) {
                log.error(e1.getMessage(), e1);
            }
        }


    }

    @RequestMapping(value = "/doDrawMoney")
    public void doDrawMoney(HttpServletRequest request, HttpServletResponse response) {
        try {
            String userName = request.getRemoteUser();
            Integer storeId = userBiz.getStoreId(userName);

            Response<Integer> drawResponse = drawMoneyExt.drawMoney(storeId);

            JSONObject jsonObject = new JSONObject();
            if (drawResponse.isSuccess()) {

                jsonObject.put("flag", "1");
                jsonObject.put("info", "查询成功");
                jsonObject.put("data", drawResponse.getData());
            } else {
                jsonObject.put("flag", "0");
                jsonObject.put("info", drawResponse.getMessage());
                jsonObject.put("data", null);
            }
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(jsonObject.toString());

        } catch (Exception e) {
            log.error(e.getMessage(), e);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("flag", "0");
            jsonObject.put("info", e.getMessage());
            response.setCharacterEncoding("UTF-8");
            try {
                response.getWriter().print(jsonObject.toString());
            } catch (IOException e1) {
                log.error(e1.getMessage(), e1);
            }
        }
    }

    //导出数据
    @RequestMapping("downLoad")
    public void downLoad(
            HttpServletRequest request, HttpServletResponse response){

        String userName = request.getRemoteUser();
        Integer storeId = userBiz.getStoreId(userName);
        String clearIdStr = request.getParameter("clearId");

        String pageStr = request.getParameter("page");
        String oneStr = request.getParameter("oneCategory");
        String twoStr = request.getParameter("twoCategory");

        request.setAttribute("pageStr", pageStr);
        request.setAttribute("oneStr", oneStr);
        request.setAttribute("twoStr", twoStr);

        int page = 1;
        int one = -1;
        int two = -1;
        Integer clearId = null;

        try {

            WorkbookSettings wbs = new WorkbookSettings();
            wbs.setGCDisabled(true);
            Workbook wb = Workbook.getWorkbook(new File(DrawMoneyController.class.getResource("/xls/DrawMoneyDetailOrderModel.xls").getPath()), wbs);
            WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream(), wb, wbs);
            wbs.setWriteAccess(null);
            WritableSheet ws = wwb.getSheet(0);


            if (!BT.isEmpty(clearIdStr)) {
                clearId = new Integer(clearIdStr);
            }

            if (!BT.isEmpty(oneStr)) {
                one = Integer.valueOf(oneStr);
            }

            if (!BT.isEmpty(twoStr)) {
                two = Integer.valueOf(twoStr);
            }

            DrawRecordQuery param = new DrawRecordQuery();
            param.setStoreId(storeId);
            param.setCleraId(clearId);
            param.setCategoryOne(one);
            param.setCategoryTwo(two);

            int rowCount = 0;
            while (true){
                PageQuery<DrawRecordQuery> pageQuery = new PageQuery<DrawRecordQuery>(page, param);
                Response<Pagination<DrawMoneyDetailBO>> queryResponse = drawMoneyExt.detail(pageQuery);

                if (queryResponse != null && queryResponse.isSuccess() && queryResponse.getData()!=null) {
                    Pagination<DrawMoneyDetailBO> paginationData = queryResponse.getData();
                    if(paginationData != null && paginationData.getData() != null
                            && paginationData.getData().getServiceList() != null){
                        DrawMoneyDetailBO drawMoneyDetailBO = paginationData.getData();
                        List<ServiceDetailBO> serviceList = drawMoneyDetailBO.getServiceList();
                        for(int i=0;i<serviceList.size();i++){
                            this.insertExcelRow(ws, ++rowCount, serviceList.get(i));
                        }

                        //如果查出的数据数目等于PageSize并且偏移-offset小于total，则继续查询下一页
                        if(serviceList.size() == paginationData.getPageSize()
                                && (paginationData.getPageSize() * page) < paginationData.getTotalCount()){
                            page++;
                            continue;
                        }

                    }
                }

                break;
            }

            Date ddd = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String fileName = sdf.format(ddd) + ".xls";
            FileUtil.downFile(wwb, response, fileName);
        } catch (Exception e) {
            log.error("导出数据失败", e);
        }
    }

    private void insertExcelRow(WritableSheet ws, int row, ServiceDetailBO serviceDetailBO) throws WriteException {
        ws.addCell(new Label(0, row, "" + serviceDetailBO.getOrderNo()));
        ws.addCell(new Label(1, row, "" + serviceDetailBO.getServiceName()));
        ws.addCell(new Label(2, row, "" + serviceDetailBO.getCount()));
        ws.addCell(new Label(3, row, "" + serviceDetailBO.getMoney()));
        ws.addCell(new Label(4, row, "" + serviceDetailBO.getSmsCode()));
        ws.addCell(new Label(5, row, "" + serviceDetailBO.getSmsTimeStr()));
    }

}
