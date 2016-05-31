/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.sh.external.marketcenter.bo;

import com.qccr.marketcenter.facade.constant.promotion.PromotionStatusConstants;

/**
 * 我报名的活动状态枚举类
 * @author zhangzhonghua
 * @date 2016年2月29日 下午5:19
 */
public enum ActivityStatusEnum {
    ENROLLING(PromotionStatusConstants.ENROLLING, "报名中"),
    ENROLLED(PromotionStatusConstants.ENROLLED, "已报名"),
    ENROLL_CLOSED(PromotionStatusConstants.ENROLL_CLOSED,"报名截止"),
    STARTED(PromotionStatusConstants.STARTED, "已开始"),
    END(PromotionStatusConstants.END,"已结束");


    private int    value;
    private String name;

    ActivityStatusEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static String getNameByValue(int value){
        if(value == ENROLLING.getValue()){
            return ENROLLING.getName();
        }else if(value == ENROLLED.getValue()){
            return ENROLLED.getName();
        }else if(value == ENROLL_CLOSED.getValue()){
            return ENROLL_CLOSED.getName();
        }else if(value == STARTED.getValue()){
            return STARTED.getName();
        }else if(value == END.getValue()){
            return END.getName();
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
