package com.qccr.sh.controller;

import com.alibaba.fastjson.JSONObject;
import com.qccr.sh.biz.UserBiz;
import com.qccr.sh.external.carman.RewardExt;
import com.qccr.sh.external.carman.bo.RewardBO;
import com.qccr.sh.page.PageQuery;
import com.qccr.sh.page.Pagination;
import com.qccr.sh.response.Response;
import com.qccr.sh.util.BT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

/**
 * Created by xianchao.yan on 2015/11/3.
 */
@Controller
@RequestMapping(value = "/page/storeReward")
public class StoreRewardController {

    private static final Logger log = LoggerFactory.getLogger(StoreRewardController.class);
    @Autowired
    private RewardExt rewardExt;
    @Autowired
    private UserBiz userBiz;

    @RequestMapping(value = "/index")
    public String index(HttpServletRequest request) {

        //获取门店id
        String userName = request.getRemoteUser();
        Integer storeId = userBiz.getStoreId(userName);

        //历史奖励总金额
        Response<BigDecimal> rewardResponse = rewardExt.getHisRewardSums(storeId);
        if (rewardResponse.isSuccess() && rewardResponse.getData() != null) {
            request.setAttribute("rewardSums", rewardResponse.getData());
        }

        return "/page/storereward/index.jsp";
    }

    @RequestMapping(value = "/getHisReward")
    public void getHisReward(HttpServletRequest request, HttpServletResponse response) {
        try {
            //获取门店id
            String userName = request.getRemoteUser();
            Integer storeId = userBiz.getStoreId(userName);

            String pageStr = request.getParameter("page");
            int page = 1;
            if (!BT.isEmpty(pageStr)) {
                page = Integer.valueOf(pageStr);
            }

            PageQuery<Integer> query = new PageQuery<Integer>(page, storeId);
            //门店的历史奖励
            Response<Pagination<RewardBO>> result = rewardExt.page(query);

            JSONObject jsonObject = new JSONObject();
            if (result.isSuccess()) {
                jsonObject.put("flag", "1");
                jsonObject.put("info", "查询成功");
                jsonObject.put("data", result.getData());
            } else {
                jsonObject.put("flag", "0");
                jsonObject.put("info", result.getMessage());
                jsonObject.put("data", null);
            }
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(jsonObject.toString());
        } catch (Exception e) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("flag", "0");
            jsonObject.put("info", e.getMessage());
            response.setCharacterEncoding("UTF-8");
            log.error(e.getMessage(),e);
            try {
                response.getWriter().print(jsonObject.toString());
            } catch (Exception e1) {
                log.error(e1.getMessage(),e1);
            }
        }
    }

}
