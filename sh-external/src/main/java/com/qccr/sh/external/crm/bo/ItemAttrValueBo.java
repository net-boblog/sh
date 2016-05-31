package com.qccr.sh.external.crm.bo;

import java.io.Serializable;

/**
 * Created by hubihua on 2016/4/26.
 */
public class ItemAttrValueBo implements Serializable {
    private static final long serialVersionUID = -3244887226873887225L;

    /** 逻辑ID */
    private String itemAttrValueId;

    /** 属性ID */
    private String itemAttrId;

    /** 属性值 */
    private String itemAttrValue;


    public String getItemAttrValueId() {
        return itemAttrValueId;
    }

    public void setItemAttrValueId(String itemAttrValueId) {
        this.itemAttrValueId = itemAttrValueId;
    }

    public String getItemAttrId() {
        return itemAttrId;
    }

    public void setItemAttrId(String itemAttrId) {
        this.itemAttrId = itemAttrId;
    }

    public String getItemAttrValue() {
        return itemAttrValue;
    }

    public void setItemAttrValue(String itemAttrValue) {
        this.itemAttrValue = itemAttrValue;
    }
}