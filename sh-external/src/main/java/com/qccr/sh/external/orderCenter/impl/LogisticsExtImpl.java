package com.qccr.sh.external.orderCenter.impl;

import com.qccr.knife.result.Result;
import com.qccr.ordercenter.facade.entity.order.LogisticsDetailRo;
import com.qccr.ordercenter.facade.entity.order.LogisticsKD100VORo;
import com.qccr.ordercenter.facade.entity.order.LogisticsRo;
import com.qccr.ordercenter.facade.service.order.LogisticsFacade;
import com.qccr.sh.external.orderCenter.LogisticsExt;
import com.qccr.sh.external.orderCenter.bo.LogisticsBO;
import com.qccr.sh.external.orderCenter.bo.LogisticsDetailBO;
import com.qccr.sh.external.orderCenter.bo.LogisticsKD100VOBO;
import com.qccr.sh.response.Response;
import com.qccr.sh.response.ResponseUtil;
import com.qccr.sh.util.BeanCopierUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by dongxc on 2015/12/7.
 */
@Service("logisticsExt")
public class LogisticsExtImpl implements LogisticsExt {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LogisticsFacade logisticsFacade;

    @Override
    public Response<LogisticsKD100VOBO> queryKuaiDi100Data(Long orderId){
        if(orderId==null){
            return ResponseUtil.error("订单ID不能为空!");
        }
        try {
            Result<LogisticsKD100VORo> logisticsKD100VORoResult =
                    logisticsFacade.queryKuaiDi100Data(orderId);
            if(logisticsKD100VORoResult!=null && logisticsKD100VORoResult.isSuccess() && logisticsKD100VORoResult.getData()!=null){
                LogisticsKD100VORo logisticsKD100VORo = logisticsKD100VORoResult.getData();
                LogisticsKD100VOBO logisticsKD100VOBO = new LogisticsKD100VOBO();
                BeanCopierUtils.copy(logisticsKD100VORo,logisticsKD100VOBO);
                if(logisticsKD100VORo.getData()!=null){
                    List<LogisticsDetailBO> logisticsDetailBOList = new LinkedList<LogisticsDetailBO>();
                    for(LogisticsDetailRo logisticsDetailRo : logisticsKD100VORo.getData()){
                        LogisticsDetailBO logisticsDetailBO = new LogisticsDetailBO();
                        BeanCopierUtils.copy(logisticsDetailRo,logisticsDetailBO);
                        logisticsDetailBOList.add(logisticsDetailBO);
                    }
                    logisticsKD100VOBO.setData(logisticsDetailBOList);
                }
                return ResponseUtil.success(logisticsKD100VOBO);
            }else if(logisticsKD100VORoResult!=null){
                logger.error("根据订单ID查询物流信息接口失败", logisticsKD100VORoResult.getStatusText());
                return ResponseUtil.error(logisticsKD100VORoResult.getStatusText());
            }else{
                logger.error("根据订单ID查询物流信息接口失败返回为空");
                return ResponseUtil.error("接口查询无数据");
            }
        } catch (Exception ex) {
            logger.error("根据订单ID查询物流信息接口异常了", ex);
            return ResponseUtil.exception(ex.getMessage());
        }
    }

    @Override
    public Response<LogisticsBO> queryByOrderId(long orderId){
        if(orderId==0){
            return ResponseUtil.error("订单ID不能为空!");
        }
        try {
            Result<LogisticsRo> logisticsRoResult = logisticsFacade.queryByOrderId(orderId);
            if(logisticsRoResult!=null && logisticsRoResult.isSuccess() && logisticsRoResult.getData()!=null){
                LogisticsRo logisticsRo = logisticsRoResult.getData();
                LogisticsBO logisticsBO = new LogisticsBO();
                BeanCopierUtils.copy(logisticsRo,logisticsBO);
                return ResponseUtil.success(logisticsBO);
            }else if(logisticsRoResult!=null){
                logger.error("根据订单ID查询订单物流信息接口失败", logisticsRoResult.getStatusText());
                return ResponseUtil.error(logisticsRoResult.getStatusText());
            }else{
                logger.error("根据订单ID查询订单物流信息接口失败返回为空");
                return ResponseUtil.error("接口查询无数据");
            }
        } catch (Exception ex) {
            logger.error("根据订单ID查询订单物流信息接口异常了", ex);
            return ResponseUtil.exception(ex.getMessage());
        }
    }
}
