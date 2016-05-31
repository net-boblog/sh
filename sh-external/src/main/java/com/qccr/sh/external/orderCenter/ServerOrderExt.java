package com.qccr.sh.external.orderCenter;

import com.qccr.knife.result.Result;
import com.qccr.sh.external.crm.bo.ProductBO;
import com.qccr.sh.external.orderCenter.bo.ServerOrderBO;
import com.qccr.sh.external.orderCenter.bo.ServerOrderQuery;
import com.qccr.sh.page.Pagination;
import com.qccr.sh.response.Response;

import java.util.List;

/**
 * Created by dongxc on 2015/12/1.
 * 服务订单相关接口
 */
public interface ServerOrderExt {

    /**
     * 查询已验证订单列表
     * @param serverOrderQuery 请求参数对象
     * @return
     */
    public Response<Pagination<ServerOrderBO>> queryByStoreIdAndSmscode(ServerOrderQuery serverOrderQuery);

    /**
     *  查询某个门店下的一些订单里的服务费用
     * @param storeId  商店ID
     * @param orderIdArr  订单列表
     * @return
     */
    public Response<List<ServerOrderBO>> queryByStoreIdAndOrderids(Long storeId, Long[] orderIdArr);

    /**
     *
     * @param smsCode          核销码
     * @param serverOrderId    服务订单id 传0
     * @param storeId         【必须】门店id
     * @return
     */
    public Result<String> verifyInit(String smsCode, long serverOrderId, int storeId);

    /**
     * 获取服务订单数
     * @param listResult
     * @return
     */
    public Response<List<ProductBO>> countOrder(List<ProductBO> listResult);

}
