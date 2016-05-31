package com.qccr.sh.external.carman;

import com.qccr.sh.external.carman.bo.RewardBO;
import com.qccr.sh.page.PageQuery;
import com.qccr.sh.page.Pagination;
import com.qccr.sh.response.Response;
import java.math.BigDecimal;

/**
 * Created by xianchao.yan on 2015/11/3.
 */
public interface RewardExt {

    /**
     * 获取历史奖励总金额
     * @param storeId
     * @return
     */
    Response<BigDecimal> getHisRewardSums(Integer storeId);

    /**
     * 根据门店id查询当前奖励金额
     *
     * @param storeId
     * @return
     */
    Response<BigDecimal> getRewardSums(Integer storeId);

    /**
     * 分页
     * @param query
     * @return
     */
    Response<Pagination<RewardBO>> page(PageQuery<Integer> query);
}
