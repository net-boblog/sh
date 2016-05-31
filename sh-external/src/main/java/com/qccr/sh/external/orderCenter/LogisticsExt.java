package com.qccr.sh.external.orderCenter;

import com.qccr.sh.external.orderCenter.bo.LogisticsBO;
import com.qccr.sh.external.orderCenter.bo.LogisticsKD100VOBO;
import com.qccr.sh.response.Response;

/**
 * Created by dongxc on 2015/12/7.
 * 物流服务
 */
public interface LogisticsExt {

    /**
     * 根据订单ID查询物流信息
     * @param orderId 订单ID
     * @return
     */
    public Response<LogisticsKD100VOBO> queryKuaiDi100Data(Long orderId);

    /**
     * 根据订单ID查询订单的物流信息
     * @param orderId
     * @return
     */
    public Response<LogisticsBO> queryByOrderId(long orderId);
}
