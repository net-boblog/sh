package com.qccr.sh.external.orderCenter;

import com.qccr.knife.result.Result;
import com.qccr.sh.external.orderCenter.bo.OrderBO;
import com.qccr.sh.external.orderCenter.bo.OrderQuery;
import com.qccr.sh.page.Pagination;
import com.qccr.sh.response.Response;

/**
 * Created by dongxc on 2015/12/1.
 * 订单相关接口
 */
public interface OrderExt {

    /**
     * 查询代收货订单ID列表
     * @param orderQuery
     * @return
     */
    public Response<Pagination<OrderBO>> queryDeliveryOrderList(OrderQuery orderQuery);

    /**
     * 查询订单ID查询订单详情
     * @param orderQuery
     * @return
     */
    public Response<OrderBO> queryOrderDetail(OrderQuery orderQuery);

    /**
     * 根据验证码查询服务
     * @param smsCode
     * @param storeId
     * @return
     */
    public Response<OrderBO> queryOrderDetailBySmscodeAndStoreid(String smsCode, Long storeId);

    /**
     * 根据订单ID查询订单信息
     * @param orderId  订单ID
     * @return
     */
    public Result<OrderBO> queryByOrderId(long orderId);
}
