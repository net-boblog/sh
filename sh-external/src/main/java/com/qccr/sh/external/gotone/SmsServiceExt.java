/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.sh.external.gotone;

import com.qccr.knife.result.Result;

import java.util.Map;

/**
 * 发送短信服务接口
 * @author dongxuechai
 * @date   2016年3月01日 下午5:42
 */
public interface SmsServiceExt {
    /**
     * 商户自建活动发布使用的发短信接口
     * @param phone  电话号码
     * @param data   发短信内容
     * @return
     */
    public Result<Boolean> sendMerchantActivitySMS(String phone,Map<String,String> data);
}
