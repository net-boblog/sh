package com.qccr.sh.external.crm.bo;

import java.io.Serializable;

/**
 * Created by user on 2016/5/9.
 */
public class StoreSafetyBo implements Serializable {
    private static final long serialVersionUID = 8531173065515809565L;
    /** 收借人(中文名) */
    private String            receiver;
    /** 支付方式(code非描述) */
    private String            payWay;
    /** 支付账号 */
    private String            payAccount;
    /** 银行名称 */
    private String            bankName;
    /** 银行编码(code非描述) */
    private String            bankCode;

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public String getPayAccount() {
        return payAccount;
    }

    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }
}