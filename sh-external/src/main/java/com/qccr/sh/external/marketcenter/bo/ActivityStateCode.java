/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.sh.external.marketcenter.bo;

import com.qccr.marketcenter.facade.statecode.MarketcenterStateCode;

/**
 * 我报名的活动状态码
 * @author zhangzhonghua
 * @date 2016年2月29日 下午5:19
 */
public enum ActivityStateCode {
    REPEAT_REGISTER(MarketcenterStateCode.REPEAT_REGISTER.getCode(), "相同服务活动不能重复报名!"),
    MERCHANT_HAS_BEEN_OFFLINED(MarketcenterStateCode.MERCHANT_HAS_BEEN_OFFLINED.getCode(),"商户门店已下线，不能报名");


    private int    value;
    private String name;

    ActivityStateCode(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static String getNameByValue(int value){
        if(value == REPEAT_REGISTER.getValue()){
            return REPEAT_REGISTER.getName();
        }else{
            return "未知的状态";
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
