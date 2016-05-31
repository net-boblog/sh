package com.qccr.sh.external.carman;

import com.qccr.sh.external.carman.bo.*;
import com.qccr.sh.page.PageQuery;
import com.qccr.sh.page.Pagination;
import com.qccr.sh.response.Response;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by xianchao.yan on 2015/11/3.
 */
public interface DrawMoneyExt {

    /**
     * 根据门店id查询订单可提款金额（不包含异常和待审核）
     *
     * @param storeId
     * @return
     */
    Response<OrderCanDrawBO> getOrderCanDrawMoneySums(Integer storeId);

    /**
     * 根据门店id查询异常金额
     *
     * @param storeId
     * @return
     */
    Response<BigDecimal> getExceptionSums(Integer storeId);

    /**
     * 根据门店id查询待审核金额
     *
     * @param storeId
     * @return
     */
    Response<BigDecimal> getWaitAuditSums(Integer storeId);

    /**
     * 根据参数分页查询提款记录
     *
     * @param pageQuery
     * @return
     */
    Response<Pagination<DrawRecordBO>> page(PageQuery<DrawRecordQueryBO> pageQuery);

    /**
     * 查询提款记录详情
     *
     * @param pageQuery
     * @return
     */
    Response<Pagination<DrawMoneyDetailBO>> detail(PageQuery<DrawRecordQuery> pageQuery);

    /**
     * 根据门店id查询订单可提款详情
     *
     * @param pageQuery
     * @return
     */
    Response<Pagination<ServiceDetailBO>> getCanDrawMoneyDetail(PageQuery<DrawDetailQuery> pageQuery);

    /**
     * 用户提款
     *
     * @param storeId
     * @return
     */
    Response<Integer> drawMoney(Integer storeId);

    /**
     * 查询提款规则
     *
     * @param storeId
     * @return
     */
    Response<DrawMoneyRule> getDrawMoneyRule(Integer storeId);

    /**
     * 获取所有的服务类别
     *
     * @return
     */
    Response<List<ServerCatagoryBO>> getAllServerCatagory();

}
