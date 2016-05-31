/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.sh.external.gotone.impl;

import com.qccr.gotone.facade.SmsServiceFacade;
import com.qccr.gotone.facade.define.RemoteProject;
import com.qccr.gotone.facade.define.RemoteSmsSendResultRo;
import com.qccr.gotone.facade.define.RemoteSmsSendRo;
import com.qccr.knife.result.CommonStateCode;
import com.qccr.knife.result.Result;
import com.qccr.knife.result.Results;
import com.qccr.knife.result.StateCode;
import com.qccr.sh.external.gotone.SmsServiceExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 发送短信服务接口
 * @author dongxuechai
 * @date   2016年3月01日 下午5:42
 */
@Service("smsServiceExt")
public class SmsServiceExtImpl implements SmsServiceExt{
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SmsServiceFacade smsServiceFacade;

    /**
     * 商户自建活动发布使用的发短信接口
     * @param phone  电话号码
     * @param data   发短信内容
     * @return
     */
    @Override
    public Result<Boolean> sendMerchantActivitySMS(String phone,Map<String,String> data){
        if(phone==null||phone.equals("")||data==null
                ||!data.containsKey("activity")||!data.containsKey("subsidy")
                ||!data.containsKey("settlement")||!data.containsKey("sale")){
            return Results.newFailedResult(CommonStateCode.ILLEGAL_PARAMETER, "电话号码与各个参数均不能为空");
        }
        String operator = "store_activity_create_success";
        RemoteProject shProject = new RemoteProject("SH","商户系统");
        try{
            RemoteSmsSendRo remoteSmsSendRo =
                    RemoteSmsSendRo.getBuilder(shProject, phone,operator).setData(data).build();
            Result<RemoteSmsSendResultRo> result = smsServiceFacade.send(remoteSmsSendRo);
            if(result!=null){
                if(result.isSuccess()){
                    return Results.newSuccessResult(true,result.getStatusText());
                }
                return Results.newFailedResult(result.getStateCode(),result.getStatusText());
            }else{
                return Results.newFailedResult(new StateCode(-1,"失败"),"发送失败");
            }
        }catch (Exception ex){
            logger.error("调用SmsServiceFacade.send接口异常,ex={}", ex);
            return Results.newFailedResult(CommonStateCode.FAILED,ex.getMessage());
        }
    }
}
