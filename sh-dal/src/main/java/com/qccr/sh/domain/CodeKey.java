package com.qccr.sh.domain;

public class CodeKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sh_code.codetype
     *
     * @mbggenerated
     */
    private String codetype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sh_code.code
     *
     * @mbggenerated
     */
    private String code;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sh_code.codetype
     *
     * @return the value of sh_code.codetype
     *
     * @mbggenerated
     */
    public String getCodetype() {
        return codetype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sh_code.codetype
     *
     * @param codetype the value for sh_code.codetype
     *
     * @mbggenerated
     */
    public void setCodetype(String codetype) {
        this.codetype = codetype == null ? null : codetype.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sh_code.code
     *
     * @return the value of sh_code.code
     *
     * @mbggenerated
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sh_code.code
     *
     * @param code the value for sh_code.code
     *
     * @mbggenerated
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }
}