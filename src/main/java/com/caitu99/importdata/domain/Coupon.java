package com.caitu99.importdata.domain;

/**
 * Created by Lion on 2015/11/24 0024.
 */
public class Coupon {
    private String pruduct_code;
    private String code;
    private String password;
    private String effective_time;


    public String getPruduct_code() {
        return pruduct_code;
    }

    public void setPruduct_code(String pruduct_code) {
        this.pruduct_code = pruduct_code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEffective_time() {
        return effective_time;
    }

    public void setEffective_time(String effective_time) {
        this.effective_time = effective_time;
    }
}
