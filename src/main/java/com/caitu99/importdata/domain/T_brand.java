package com.caitu99.importdata.domain;

import java.util.Date;

/**
 * Created by Lion on 2015/11/23 0023.
 */
public class T_brand {
    private Long brand_id;  //BRAND_ID;    //         bigint not null auto_increment comment '主键',
    private String brand_no;//BRAND_NO;//             varchar(64) not null comment '编号',
    private String name;//NAME;//                 varchar(64) not null comment '名称',
    private String memo;//MEMO;        //                 varchar(512) comment '说明',
    private Date create_time;//CREATE_TIME;     //          datetime not null comment '创建时间',
    private Date update_time;//UPDATE_TIME;     //          datetime not null comment '修改时间',


    public Long getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(Long brand_id) {
        this.brand_id = brand_id;
    }

    public String getBrand_no() {
        return brand_no;
    }

    public void setBrand_no(String brand_no) {
        this.brand_no = brand_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}
