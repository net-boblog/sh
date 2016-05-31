/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.sh.external.marketcenter.bo;

import com.qccr.marketcenter.facade.constant.promotion.EnrollPromotionStatus;

/**
 * 我报名的活动报名状态枚举类
 * @author zhangzhonghua
 * @date 2016年2月29日 下午5:19
 */
public enum ActivityResultStatusEnum {
    APPROVIND(EnrollPromotionStatus.APPROVIND, "审核中"),
    WAIT_START(EnrollPromotionStatus.WAIT_START, "等待开始"),
    STARTED(EnrollPromotionStatus.STARTED,"已开始"),
    END(EnrollPromotionStatus.END, "已结束"),
    APPROVE_FAILED(EnrollPromotionStatus.APPROVE_FAILED,"审核失败"),
    QUIT_PROMOTION(EnrollPromotionStatus.QUIT_PROMOTION,"已退出");

    private int    value;
    private String name;

    ActivityResultStatusEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static String getNameByValue(int value){
        if(value == APPROVIND.getValue()){
            return APPROVIND.getName();
        }else if(value == WAIT_START.getValue()){
            return WAIT_START.getName();
        }else if(value == STARTED.getValue()){
            return STARTED.getName();
        }else if(value == END.getValue()){
            return END.getName();
        }else if(value == APPROVE_FAILED.getValue()){
            return APPROVE_FAILED.getName();
        }else if(value == QUIT_PROMOTION.getValue()){
            return QUIT_PROMOTION.getName();
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
