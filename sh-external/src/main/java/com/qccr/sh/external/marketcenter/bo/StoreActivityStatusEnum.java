/**
 * qccr.com Inc.
 * Copyright (c) 2014-2016 All Rights Reserved.
 */
package com.qccr.sh.external.marketcenter.bo;

import com.qccr.marketcenter.facade.constant.promotion.BuildPromotionStatusConstants;

/**
 * 我发布的活动状态枚举类
 * @author dongxuechai
 * @date 2016年2月29日 下午5:19
 */
public enum StoreActivityStatusEnum {
    WAIT_START(BuildPromotionStatusConstants.WAIT_START,"等待开始"),
    STARTED(BuildPromotionStatusConstants.STARTED, "已开始"),
    END(BuildPromotionStatusConstants.END,"已结束");

    private int    value;
    private String name;

    StoreActivityStatusEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static String getNameByValue(int value){
        for(StoreActivityStatusEnum statusEnum : StoreActivityStatusEnum.values()){
            if(statusEnum.getValue()==value){
                return statusEnum.getName();
            }
        }
        return "未知的状态";
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
