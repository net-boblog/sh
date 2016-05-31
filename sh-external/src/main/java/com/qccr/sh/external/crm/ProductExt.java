package com.qccr.sh.external.crm;

import com.qccr.knife.result.Result;
import com.qccr.sh.external.crm.bo.ProductBO;
import com.qccr.sh.response.Response;

import java.util.List;

/**
 * Created by dongxc on 2015/12/2.
 */
public interface ProductExt {

    /**
     * 根据门店ID查询门店提供的服务项目、合作价格
     * @param storeId 门店ID
     * @return
     */
    public Response<List<ProductBO>> getProductsByStoreId(int storeId);

    /**
     * 查询服务的结算价
     * @param userName   登录商户账号
     * @param categoryId 二级服务ID
     * @return
     */
    public Result<Double> getProductContractPrice(String userName,String categoryId);

    /**
     * 添加服务
     * @param productBO
     * @return
     */
    public  Result<String> addProduct(ProductBO productBO);

    /**
     * 删除服务
     * @param productId 服务id
     * @param storeId 门店id
     * @return
     */
    public Result<Boolean> deleteByProductId(String productId,String storeId);

    /**
     * 修改服务
     * @param productBO
     * @return
     */
    public Result<Integer> modifyProduct(ProductBO productBO);

}
