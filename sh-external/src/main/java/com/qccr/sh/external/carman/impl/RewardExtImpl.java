package com.qccr.sh.external.carman.impl;

import com.alibaba.fastjson.JSON;
import com.qccr.sh.external.carman.RewardExt;
import com.qccr.sh.external.carman.bo.RewardBO;
import com.qccr.sh.page.PageQuery;
import com.qccr.sh.page.Pagination;
import com.qccr.sh.response.Response;
import com.qccr.sh.response.ResponseUtil;
import com.towell.carman.entity.order.Reward;
import com.towell.carman.entity.order.RewardVo;
import com.towell.carman.service.order.RewardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xianchao.yan on 2015/11/3.
 */
@Service("rewardExt")
public class RewardExtImpl implements RewardExt {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RewardService rewardService;

    @Override
    public Response<BigDecimal> getHisRewardSums(Integer storeId) {
        logger.info("查询历史奖励总金额,storeId={}", storeId);
        try {
            Double priceSum = rewardService.sumClearDoneByStoreId(storeId);
            logger.info("查询历史奖励总金额结束,storeId={},response={}", storeId, priceSum);
            double returnPrice = 0;
            if (priceSum != null) {
                returnPrice = priceSum.doubleValue();
            }
            BigDecimal sums = new BigDecimal(Double.toString(returnPrice));
            return ResponseUtil.success(sums);
        } catch (Exception e) {
            logger.error("查询历史奖励总金额异常,storeId=" + storeId, e);
            return ResponseUtil.exception(e.getMessage());
        }
    }

    @Override
    public Response<BigDecimal> getRewardSums(Integer storeId) {
        try {
            logger.info("查询当前奖励金额,storeId={}", storeId);
            Double priceSum = rewardService.sumUnclearByStoreId(storeId);
            logger.info("查询当前奖励金额结束,storeId={},response={}", storeId, priceSum);
            double returnPrice = 0;
            if (priceSum != null) {
                returnPrice = priceSum.doubleValue();
            }
            BigDecimal sums = new BigDecimal(Double.toString(returnPrice));
            return ResponseUtil.success(sums);
        } catch (Exception e) {
            logger.error("查询当前奖励金额异常,storeId=" + storeId, e);
            return ResponseUtil.exception(e.getMessage());
        }
    }

    @Override
    public Response<Pagination<RewardBO>> page(PageQuery<Integer> query) {
        try {
            logger.info("查询历史奖励金额,query={}", JSON.toJSONString(query));
            RewardVo rewardVo = rewardService.findRewardHistory(query.getParam(), query.getStart(), query.getPageSize());
            logger.info("查询历史奖励金额结束,query={},response={}", JSON.toJSONString(query), JSON.toJSONString(rewardVo));
            if (rewardVo == null || rewardVo.getRewardList().size() <= 0) {
                Pagination<RewardBO> pagination = new Pagination<RewardBO>(query.getPageSize(), query.getCurrentPage(), 0);
                return ResponseUtil.success(pagination);
            }
            List<RewardBO> rewardBOList = new ArrayList<RewardBO>();
            for (Reward reward : rewardVo.getRewardList()) {
                RewardBO rewardBO = new RewardBO();
                BeanUtils.copyProperties(reward, rewardBO);
                rewardBOList.add(rewardBO);
            }
            Pagination<RewardBO> pagination = new Pagination<RewardBO>(query.getPageSize(), query.getCurrentPage(), rewardVo.getTotal());
            pagination.setDataList(rewardBOList);
            return ResponseUtil.success(pagination);
        } catch (Exception e) {
            logger.error("查询历史奖励金额异常,query=" + JSON.toJSONString(query), e);
            return ResponseUtil.exception(e.getMessage());
        }

    }


}
