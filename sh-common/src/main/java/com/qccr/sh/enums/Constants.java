package com.qccr.sh.enums;

/**
 * 枚举常量
 * 2016/4/7.
 */
public enum Constants {
    CREATE_USER("sh_admin","sh_admin"),
    //钣金油漆一级服务code
    FIRST_PAINT_CODE("firstCategoryCode","ACAF"),
    FIRST_ORG_CATEGORY_CODE("firstOrgCategoryCode","700"),
    //钣金油漆二级服务code
    SECOND_PAINT_CODE("secondCategoryCode","ACAFAB"),
    SECOND_ORG_CATEGORY_CODE("secondOrgCategoryCode","701"),

    CATEGORY_CODE("categoryCode","ACAFAB");

    private String name;
    private String code;

    Constants(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getCode(){
        return  code;
    }
}
