package com.qccr.sh.external.carman.impl;

import com.alibaba.fastjson.JSON;
import com.qccr.sh.external.carman.DrawMoneyExt;
import com.qccr.sh.external.carman.bo.*;
import com.qccr.sh.page.PageQuery;
import com.qccr.sh.page.Pagination;
import com.qccr.sh.response.Response;
import com.qccr.sh.response.ResponseUtil;
import com.towell.carman.common.StateCode;
import com.towell.carman.common.Status;
import com.towell.carman.entity.merchant.Dictornary;
import com.towell.carman.entity.order.*;
import com.towell.carman.entity.rsp.merchant.AskClearRsp;
import com.towell.carman.service.goods.ServerService;
import com.towell.carman.service.order.ClearService;
import com.towell.carman.service.order.ServerOrderService;
import com.towell.carman.service.order.StorePickupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xianchao.yan on 2015/11/3.
 */
@Service("drawMoneyExt")
public class DrawMoneyExtImpl implements DrawMoneyExt {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ClearService clearService;
    @Autowired
    private ServerOrderService serverOrderService;
    @Autowired
    private StorePickupService storePickupService;
    @Autowired
    private ServerService serverService;


    @Override
    public Response<OrderCanDrawBO> getOrderCanDrawMoneySums(Integer storeId) {
        try {
            logger.info("查询订单可提款金额,storeId={}", storeId);
            ClearRsp clearRsp = clearService.getStoreShopClearConfirmByStoreId(storeId);
            logger.info("查询订单可提款金额结束,storeId={},response={}", storeId, JSON.toJSONString(clearRsp));
            double priceSum = 0D;
            boolean canDrawMoney = false;
            String reason = "";
            if (clearRsp != null) {
                priceSum = clearRsp.getOrderSum();
                if (clearRsp.getFlag() != null && clearRsp.getFlag() == 0) {
                    canDrawMoney = true;
                } else {
                    reason = clearRsp.getText();
                }
            }
            BigDecimal sums = new BigDecimal(Double.toString(priceSum));
            OrderCanDrawBO orderCanDrawBO = new OrderCanDrawBO();
            orderCanDrawBO.setCanDrawMoney(canDrawMoney);
            orderCanDrawBO.setReason(reason);
            orderCanDrawBO.setSums(sums);
            return ResponseUtil.success(orderCanDrawBO);
        } catch (Exception e) {
            logger.error("查询订单可提款金额异常,storeId=" + storeId, e);
            return ResponseUtil.exception(e.getMessage());
        }
    }

    @Override
    public Response<BigDecimal> getExceptionSums(Integer storeId) {
        try {
            logger.info("查询异常金额,storeId={}", storeId);
            double priceSum = serverOrderService.sumSpriceByAuditStatus(storeId, Status.AuditServerOrder.ABNORMAL);
            BigDecimal sums = new BigDecimal(Double.toString(priceSum));
            logger.info("查询异常金额结束,storeId={},response={}", storeId, priceSum);
            return ResponseUtil.success(sums);
        } catch (Exception e) {
            logger.error("查询异常金额异常,storeId=" + storeId, e);
            return ResponseUtil.exception(e.getMessage());
        }
    }

    @Override
    public Response<BigDecimal> getWaitAuditSums(Integer storeId) {
        try {
            logger.info("查询待审核金额,storeId={}", storeId);
            double priceSum = serverOrderService.sumSpriceByAuditStatus(storeId, Status.AuditServerOrder.AUDIT);
            BigDecimal sums = new BigDecimal(Double.toString(priceSum));
            logger.info("查询待审核金额结束,storeId={},response={}", storeId, priceSum);
            return ResponseUtil.success(sums);
        } catch (Exception e) {
            logger.error("查询待审核金额异常,storeId=" + storeId, e);
            return ResponseUtil.exception(e.getMessage());
        }
    }

    @Override
    public Response<Pagination<DrawRecordBO>> page(PageQuery<DrawRecordQueryBO> pageQuery) {
        DrawRecordQueryBO param = pageQuery.getParam();
        try {
            logger.info("分页查询门店提款记录,query={}", JSON.toJSONString(pageQuery));
            Timestamp startDate = (param.getStartDate() == null) ? null : new Timestamp(param.getStartDate().getTime());
            Timestamp endDate = (param.getEndDate() == null) ? null : new Timestamp(param.getEndDate().getTime());
            int status = (param.getStatus() == null) ? -1 : param.getStatus();
            ClearInfoListVo clearInfoListVo = clearService.queryClearForPageByTime(param.getStoreId(), pageQuery.getStart(), pageQuery.getPageSize(),
                    startDate, endDate, status);
            logger.info("分页查询门店提款记录结束,query={},response={}", JSON.toJSONString(pageQuery), JSON.toJSONString(clearInfoListVo));
            //返回空
            if (clearInfoListVo == null) {
                Pagination<DrawRecordBO> pagination = new Pagination<DrawRecordBO>(pageQuery.getPageSize(), pageQuery.getCurrentPage(), 0);
                return ResponseUtil.success(pagination);
            }

            List<DrawRecordBO> drawRecordBOList = new ArrayList<DrawRecordBO>();
            List<ClearInfoVo> clearInfoVos = clearInfoListVo.getClearList();
            for (ClearInfoVo clearInfoVo : clearInfoVos) {
                DrawRecordBO drawRecordBO = new DrawRecordBO();
                BeanUtils.copyProperties(clearInfoVo, drawRecordBO);
                drawRecordBOList.add(drawRecordBO);
            }
            Pagination<DrawRecordBO> pagination = new Pagination<DrawRecordBO>(pageQuery.getPageSize(), pageQuery.getCurrentPage(), clearInfoListVo.getTotal());
            pagination.setDataList(drawRecordBOList);
            return ResponseUtil.success(pagination);
        } catch (Exception e) {
            logger.error("分页查询门店提款记录异常，storeId=" + param.getStoreId() + ",startDate=" + param.getStartDate() +
                    ",endDate=" + param.getEndDate() + ",start=" + pageQuery.getStart() + ",limit=" + pageQuery.getPageSize(), e);
            return ResponseUtil.exception(e.getMessage());
        }
    }

    @Override
    public Response<Pagination<DrawMoneyDetailBO>> detail(PageQuery<DrawRecordQuery> pageQuery) {
        DrawRecordQuery param = pageQuery.getParam();
        try {
            logger.info("查询门店提款详情,query={}", JSON.toJSONString(pageQuery));
            ServerOrderListVo serverOrderListVo = serverOrderService.findServiceOrderByStoreId(param.getStoreId(), param.getCategoryOne(),
                    param.getCategoryTwo(), pageQuery.getStart(), pageQuery.getPageSize(), param.getCleraId());
            logger.info("查询门店提款详情结束,query={},response={}", JSON.toJSONString(pageQuery), JSON.toJSONString(serverOrderListVo));
            if (serverOrderListVo == null) {
                Pagination<DrawMoneyDetailBO> pagination = new Pagination<DrawMoneyDetailBO>(pageQuery.getPageSize(), pageQuery.getCurrentPage(), 0);
                return ResponseUtil.success(pagination);
            }

            List<ServerOrderVo> serverOrderVos = serverOrderListVo.getServerOrderList();
            List<ServiceDetailBO> serviceDetailBOs = new ArrayList<ServiceDetailBO>();
            for (ServerOrderVo serverOrderVo : serverOrderVos) {
                ServiceDetailBO detailBO = new ServiceDetailBO();
                BeanUtils.copyProperties(serverOrderVo, detailBO);
                serviceDetailBOs.add(detailBO);
            }

            DrawMoneyDetailBO drawDetail = new DrawMoneyDetailBO();
            drawDetail.setOrderSum(new BigDecimal(Double.toString(serverOrderListVo.getOrderSum())));
            drawDetail.setRewardSum(new BigDecimal(Double.toString(serverOrderListVo.getRewardSum())));
            drawDetail.setSum(new BigDecimal(Double.toString(serverOrderListVo.getSum())));
            drawDetail.setStatus(serverOrderListVo.getStatus());
            drawDetail.setCreateTime(serverOrderListVo.getCreateTime());
            drawDetail.setClearTime(serverOrderListVo.getClearTime());
            drawDetail.setServiceList(serviceDetailBOs);

            Pagination<DrawMoneyDetailBO> pagination = new Pagination<DrawMoneyDetailBO>(pageQuery.getPageSize(),
                    pageQuery.getCurrentPage(), serverOrderListVo.getTotal());
            pagination.setData(drawDetail);
            return ResponseUtil.success(pagination);
        } catch (Exception e) {
            logger.error("查询门店提款详情异常，storeId=" + param.getStoreId() + ",clearId=" + param.getCleraId() + ",categoryOne=" + param.getCategoryOne()
                    + ",catetoryTwo=" + param.getCategoryTwo() + ",start=" + pageQuery.getStart() + ",limit=" + pageQuery.getPageSize(), e);
            return ResponseUtil.exception(e.getMessage());
        }
    }

    @Override
    public Response<Pagination<ServiceDetailBO>> getCanDrawMoneyDetail(PageQuery<DrawDetailQuery> pageQuery) {
        DrawDetailQuery param = pageQuery.getParam();
        try {
            logger.info("查询门店可提款，query={}", JSON.toJSONString(pageQuery));
            ServerOrderListVo serverOrderListVo = serverOrderService.findServiceOrderByStoreId(param.getStoreId(), param.getCategoryOne(),
                    param.getCategoryTwo(), pageQuery.getStart(), pageQuery.getPageSize(), null);
            logger.info("查询门店可提款结束，query={},response={}", JSON.toJSONString(pageQuery), JSON.toJSONString(serverOrderListVo));
            if (serverOrderListVo == null) {
                Pagination<ServiceDetailBO> pagination = new Pagination<ServiceDetailBO>(pageQuery.getPageSize(), pageQuery.getCurrentPage(), 0);
                return ResponseUtil.success(pagination);
            }

            List<ServerOrderVo> serverOrderVos = serverOrderListVo.getServerOrderList();
            List<ServiceDetailBO> drawRecordDetailBOs = new ArrayList<ServiceDetailBO>();
            for (ServerOrderVo serverOrderVo : serverOrderVos) {
                ServiceDetailBO detailBO = new ServiceDetailBO();
                BeanUtils.copyProperties(serverOrderVo, detailBO);
                drawRecordDetailBOs.add(detailBO);
            }
            Pagination<ServiceDetailBO> pagination = new Pagination<ServiceDetailBO>(pageQuery.getPageSize(),
                    pageQuery.getCurrentPage(), serverOrderListVo.getTotal());
            pagination.setDataList(drawRecordDetailBOs);
            return ResponseUtil.success(pagination);
        } catch (Exception e) {
            logger.error("查询门店可提款异常，storeId=" + param.getStoreId() + ",categoryOne=" + param.getCategoryOne()
                    + ",catetoryTwo=" + param.getCategoryTwo() + ",start=" + pageQuery.getStart() + ",limit=" + pageQuery.getPageSize(), e);
            return ResponseUtil.exception(e.getMessage());
        }
    }


    @Override
    public Response<Integer> drawMoney(Integer storeId) {

        try {
            logger.info("提款,storeId={}", storeId);
            AskClearRsp askClearRsp = clearService.askForClear(storeId, true);
            logger.info("提款结束,storeId={},response={}", storeId, JSON.toJSONString(askClearRsp));
            if (askClearRsp == null || askClearRsp.getStateCode() == null) {
                return ResponseUtil.error("提款失败");
            } else if (!askClearRsp.getStateCode().equals(StateCode.SUCCESS)) {
                return ResponseUtil.error(askClearRsp.getStateCode().getDescription());
            } else {
                return ResponseUtil.success(askClearRsp.getClearId());
            }
        } catch (Exception e) {
            logger.error("提款异常,storeId=" + storeId, e);
            return ResponseUtil.exception(e.getMessage());
        }
    }

    @Override
    public Response<DrawMoneyRule> getDrawMoneyRule(Integer storeId) {
        try {
            logger.info("查询提款规则,storeId={}", storeId);
            StorePickup storePickup = storePickupService.queryByContingencyStoreId(storeId);
            logger.info("查询提款规则结束,storeId={},response={}", storeId, JSON.toJSONString(storePickup));
            if (storePickup == null) {
                return ResponseUtil.error();
            }
            DrawMoneyRule drawMoneyRule = new DrawMoneyRule();
            BeanUtils.copyProperties(storePickup, drawMoneyRule);
            return ResponseUtil.success(drawMoneyRule);
        } catch (Exception e) {
            logger.error("查询提款规则异常,storeId=" + storeId, e);
            return ResponseUtil.exception(e.getMessage());
        }
    }

    @Override
    public Response<List<ServerCatagoryBO>> getAllServerCatagory() {
        try {
            List<Dictornary> serverCatagoryList = serverService.queryServerAllByType(1);
            List<ServerCatagoryBO> boList = new ArrayList<ServerCatagoryBO>();
            for (Dictornary serverCatagory : serverCatagoryList) {
                ServerCatagoryBO bo = new ServerCatagoryBO();
                BeanUtils.copyProperties(serverCatagory, bo);
                boList.add(bo);
            }
            return ResponseUtil.success(boList);
        } catch (Exception e) {
            logger.error("查询服务类别异常", e);
            return ResponseUtil.exception(e.getMessage());
        }
    }


}
