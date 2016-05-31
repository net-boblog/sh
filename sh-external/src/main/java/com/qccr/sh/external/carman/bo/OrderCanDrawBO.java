package com.qccr.sh.external.carman.bo;

import java.math.BigDecimal;

/**
 * Created by xianchao.yan on 2015/11/5.
 */
public class OrderCanDrawBO {

    private BigDecimal sums;//订单可提款金额

    private boolean canDrawMoney;//是否可提款

    private String reason;//不可提款的原因

    public BigDecimal getSums() {
        return sums;
    }

    public void setSums(BigDecimal sums) {
        this.sums = sums;
    }

    public boolean isCanDrawMoney() {
        return canDrawMoney;
    }

    public void setCanDrawMoney(boolean canDrawMoney) {
        this.canDrawMoney = canDrawMoney;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
