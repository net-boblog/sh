package com.qccr.sh.external.orderCenter.bo;

/**
 * @author chenguowan
 * @date 2016-05-16
 */
public enum PromotionSourceEnum {

    CU(1, "平台活动"),
    SHANG(2, "商家活动"),
    MEITUAN(3, "美团")

    ;

    private int value;
    private String name;

    PromotionSourceEnum(int value, String name) {
        this.value = value;
        this.name = name;
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
