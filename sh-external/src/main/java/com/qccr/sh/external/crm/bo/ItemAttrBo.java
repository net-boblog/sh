package com.qccr.sh.external.crm.bo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hubihua on 2016/4/26.
 */
public class ItemAttrBo implements Serializable {

    private static final long serialVersionUID = 5409907747714180718L;

    /** 对应属性值 */
    private List<ItemAttrValueBo> itemAttrValues;

    public List<ItemAttrValueBo> getItemAttrValues() {
        return itemAttrValues;
    }

    public void setItemAttrValues(List<ItemAttrValueBo> itemAttrValues) {
        this.itemAttrValues = itemAttrValues;
    }
}
