package com.qccr.sh.controller;

import com.alibaba.fastjson.JSONObject;
import com.qccr.sh.biz.ShopServiceBiz;
import com.qccr.sh.biz.UserBiz;
import com.qccr.sh.external.carman.CommentExt;
import com.qccr.sh.external.carman.bo.StoreCommentBO;
import com.qccr.sh.external.carman.bo.StoreCommentCountBO;
import com.qccr.sh.external.crm.StoreAccountExt;
import com.qccr.sh.external.crm.bo.StoreInfoBO;
import com.qccr.sh.page.Pagination;
import com.qccr.sh.response.Response;
import com.toowell.crm.facade.store.entity.CategoryRo;
import com.towell.carman.common.StateCode;
import com.towell.carman.service.user.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by dongxc on 2015/10/29.
 * 服务评价
 */
@Controller
@RequestMapping("/page/comment")
public class CommentController {
    private static final Logger log = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private ShopServiceBiz shopServiceBiz;

    @Autowired
    private UserBiz userBiz;

    @Autowired
    private StoreAccountExt storeAccountExt;

    @Autowired
    private CommentExt commentExt;

    @Autowired
    private CommentService commentService;

    /**
     * 查询商户提供的服务列表
     */
    @RequestMapping("/findService")
    @ResponseBody
    public Map<String,Object> findService(@RequestParam(defaultValue = "", required = false) String serviceParentId,
                                          HttpServletRequest request){
        Map<String,Object> result = new HashMap<String,Object>();
        try{
            List<CategoryRo> categoryRoList = shopServiceBiz.queryStoreService(userBiz.getStoreId(request.getRemoteUser()), serviceParentId);
            if(categoryRoList!=null && categoryRoList.size()>0){
                result.put("success",true);
                result.put("list",categoryRoList);
            }else{
                result.put("success",false);
                result.put("info","查询商户提供的服务列表无数据");
            }
        }catch (Exception ex){
            log.error("查询商户提供的服务列表时异常了",ex);
        }
        return result;
    }

    /**
     * 查询商户的信息、商户的平均分
     */
    @RequestMapping("/shopInfo")
    @ResponseBody
    public Map<String,Object> shopInfo(HttpServletRequest request){
        Map<String,Object> result = new HashMap<String,Object>();
        //获取登录的商户
        try{
            Integer storeId = userBiz.getStoreId(request.getRemoteUser());
            Response<StoreInfoBO> storeInfoBOResponse = storeAccountExt.getStoreInfo(storeId);
            if(storeInfoBOResponse!=null && storeInfoBOResponse.isSuccess()
                    && storeInfoBOResponse.getData()!=null && storeInfoBOResponse.getData().getStoreName()!=null
                    && !storeInfoBOResponse.getData().getStoreName().equals("")){
                result.put("storeName",storeInfoBOResponse.getData().getStoreName());
            }
            //获取商户的平均分
            Response<Double> avgResponse = commentExt.getCachedStoreAverageByStoreId(storeId);
            if(avgResponse!=null && avgResponse.isSuccess() && avgResponse.getData()!=null){
                result.put("avgScore",avgResponse.getData());
            }else{
                result.put("avgScore",0);
            }
            result.put("success",true);
        }catch (Exception ex){
            log.error("用户评价里查询登录的商户时异常了",ex);
        }
        return result;
    }

    /**
     * 查询门店的评价统计、积分统计等信息
     * @return
     */
    @RequestMapping("/stat")
    @ResponseBody
    public Map<String,Object> commentStat(@RequestParam(defaultValue = "", required = false) String serviceParentId,
                                          @RequestParam(defaultValue = "", required = false) String serviceId,
                                          @RequestParam(defaultValue = "", required = false) String createTimeStart,
                                          @RequestParam(defaultValue = "", required = false) String createTimeEnd,
                                          HttpServletRequest request){
        Map<String,Object> result = new HashMap<String,Object>();
        //获取登录的商户
        try{
            Integer storeId = userBiz.getStoreId(request.getRemoteUser());
            //二级服务集合
            List<Integer> serviceList = null;
            if(serviceParentId!=null && !serviceParentId.equals("") && serviceId!=null && !serviceId.equals("")){
                serviceList = new ArrayList<Integer>();
                serviceList.add(new Integer(serviceId));
            }else if(serviceParentId!=null && !serviceParentId.equals("")){
                List<CategoryRo> categoryRoList = shopServiceBiz.queryStoreService(userBiz.getStoreId(request.getRemoteUser()), serviceParentId);
                serviceList = new ArrayList<Integer>();
                for(CategoryRo categoryRo:categoryRoList){
                    serviceList.add(new Integer(categoryRo.getCategoryId()));
                }
            }

            Timestamp startTime = null;
            if(createTimeStart!=null && !createTimeStart.equals("")){
                startTime = Timestamp.valueOf(createTimeStart+" 00:00:00");
            }
            //查询结束时间非空时往后延一天
            Timestamp endTime = null;
            if(createTimeEnd!=null && !createTimeEnd.equals("")){
                java.text.DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date sEndTime = df.parse(createTimeEnd);
                Calendar calendar = Calendar.getInstance();//日历对象
                calendar.setTime(sEndTime);//设置当前日期
                calendar.add(Calendar.DATE, 1);//天加1
                createTimeEnd = df.format(calendar.getTime());
                endTime = Timestamp.valueOf(createTimeEnd + " 00:00:00");
            }
            Response<StoreCommentCountBO> storeCommentBOResponse = commentExt.queryStoreCommentCount(storeId,serviceList,startTime,endTime);
            if(storeCommentBOResponse!=null && storeCommentBOResponse.isSuccess() && storeCommentBOResponse.getData()!=null){
                StoreCommentCountBO commentCountBO = storeCommentBOResponse.getData();
                result.put("totalCount",commentCountBO.getTotalCount());
                result.put("imgCount",commentCountBO.getImageCommentCount());
                result.put("goodCommentCount",commentCountBO.getGoodCommentCount());   //好评 [4,5]
                result.put("mediumCommentCount",commentCountBO.getMediumCommentCount());  //中评  [3,4}
                result.put("badCommentCount",commentCountBO.getBadCommentCount());       //差评 [0,3)
                result.put("success",true);
            }else{
                result.put("success",false);
                result.put("info","查询商户评价统计失败");
            }
        }catch (Exception ex){
            result.put("success",false);
            result.put("info","查询商户评价统计异常了");
            log.error("查询门店的评价统计、积分统计等信息时异常了",ex);
        }
        return result;
    }

    /**
     * 查询门店的评价列表
     * @param pageStart  记录偏移
     * @param pageSize    查询记录数
     * @param serviceParentId 门店提供的一级服务，空表示全部
     * @param serviceId 门店提供的二级服务，空表示全部
     * @param commentTypeId 评价类型 0-全部评价 1-有图评价 2-好评 3-中评 4-差评
     * @param createTimeStart 下单开始时间
     * @param createTimeEnd 下单结束时间
     * @param request
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(defaultValue = "0", required = false) int pageStart,
                                    @RequestParam(defaultValue = "20", required = false) int pageSize,
                                    @RequestParam(defaultValue = "", required = false) String serviceParentId,
                                    @RequestParam(defaultValue = "", required = false) String serviceId,
                                    @RequestParam(defaultValue = "-1", required = false) int commentTypeId,
                                    @RequestParam(defaultValue = "", required = false) String createTimeStart,
                                    @RequestParam(defaultValue = "", required = false) String createTimeEnd,
                                    HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        try{
            Integer storeId = userBiz.getStoreId(request.getRemoteUser());
            //二级服务集合
            List<Integer> serviceList = null;
            if(serviceParentId!=null && !serviceParentId.equals("") && serviceId!=null && !serviceId.equals("")){
                serviceList = new ArrayList<Integer>();
                serviceList.add(new Integer(serviceId));
            }else if(serviceParentId!=null && !serviceParentId.equals("")){
                List<CategoryRo> categoryRoList = shopServiceBiz.queryStoreService(userBiz.getStoreId(request.getRemoteUser()), serviceParentId);
                serviceList = new ArrayList<Integer>();
                for(CategoryRo categoryRo:categoryRoList){
                    serviceList.add(new Integer(categoryRo.getCategoryId()));
                }
            }
            Timestamp startTime = null;
            if(createTimeStart!=null && !createTimeStart.equals("")){
                startTime = Timestamp.valueOf(createTimeStart+" 00:00:00");
            }
            //查询结束时间非空时往后延一天
            Timestamp endTime = null;
            if(createTimeEnd!=null && !createTimeEnd.equals("")){
                java.text.DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date sEndTime = df.parse(createTimeEnd);
                Calendar calendar = Calendar.getInstance();//日历对象
                calendar.setTime(sEndTime);//设置当前日期
                calendar.add(Calendar.DATE, 1);//天加1
                createTimeEnd = df.format(calendar.getTime());
                endTime = Timestamp.valueOf(createTimeEnd + " 00:00:00");
            }
            //查询评价列表
            Response<Pagination<StoreCommentBO>> commentPageResponse = commentExt.queryCommentByStoreId(storeId,serviceList,commentTypeId,
                    startTime,endTime,pageStart,pageSize);
            if(commentPageResponse!=null && commentPageResponse.isSuccess() && commentPageResponse.getData()!=null){
                Pagination<StoreCommentBO> pagination = commentPageResponse.getData();
                jsonObject.put("data", pagination.getDataList());
                jsonObject.put("total", pagination.getTotalCount());
                jsonObject.put("success",true);
            }else{
                jsonObject.put("success",false);
                jsonObject.put("info","查询门店的评价列表失败");
            }
        }catch (Exception ex){
            jsonObject.put("success",false);
            jsonObject.put("info","查询门店的评价列表时异常了");
            log.error("查询门店的评价列表时异常了",ex);
        }
        return jsonObject;
    }

    /**
     * 商户回复用户评论
     * @param type 类型   1 商品  2 服务
     * @param request
     * @return
     */
    @RequestMapping(value = "/replyComment",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> replyComment(@RequestParam(defaultValue = "2",required = false) int type,HttpServletRequest request){
        String reply = request.getParameter("reply");
        String userId = request.getParameter("userId");
        JSONObject jsonObject = new JSONObject();
        if (reply != null && userId != null){
            StateCode stateCode = commentService.replyComment(type,Long.parseLong(userId),reply);
            if(reply.length() > 0 && reply.length() <= 500){
                if (stateCode != null && stateCode.getCode() == 0){
                    jsonObject.put("success",true);
                    jsonObject.put("info","回复评论成功");
                }else{
                    jsonObject.put("success",false);
                    jsonObject.put("info","回复失败");
                }
            }else{
                jsonObject.put("success",false);
                jsonObject.put("info","回复内容必须少于500字");
            }
        }
        return jsonObject;
    }
}
