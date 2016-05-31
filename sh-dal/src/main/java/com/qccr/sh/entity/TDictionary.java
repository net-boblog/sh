package com.qccr.sh.entity;

import java.io.Serializable;

/**
 * Created by dongxc on 2015/10/29.
 * 产品类别--服务类目等
 */
public class TDictionary implements Serializable {

    private static final long serialVersionUID = -2155110868937696128L;

    private Integer pkid;    //节点ID
    private String name;    //节点名称
    private Integer superId;  //父节点ID
    private Integer level;  //层级

    public Integer getPkid() {
        return pkid;
    }

    public void setPkid(Integer pkid) {
        this.pkid = pkid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSuperId() {
        return superId;
    }

    public void setSuperId(Integer superId) {
        this.superId = superId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
