package com.caitu99.importdata.domain;

import java.util.Date;

/**
 * Created by Lion on 2015/11/23 0023.
 */
public class T_stock {
    private Long stock_id;//STOCK_ID;           //  bigint not null auto_increment comment '主键',
    private Long item_id;//ITEM_ID;              //bigint not null comment '商品编号',
    private Long sku_id;    //SKU_ID;             //  bigint comment 'SKU编号',
    private String code;    //CODE;              //   varchar(64) not null comment '兑换券',
    private String password;    //PASSWORD;           //  varchar(64) comment '密码',
    private Integer status;     //STATUS;             //  int not null comment '-1.过期, 0.下架, 1.上架, 2售出',
    private String version; //VERSION;             // varchar(64) comment '版本',
    private Date effective_time;    //EFFECTIVE_TIME;      // datetime comment '有效期',
    private Date sale_time; //SALE_TIME;           // datetime comment '销售时间',
    private Date craete_time;   //CREATE_TIME;         // datetime not null comment '创建时间',

    public Long getStock_id() {
        return stock_id;
    }

    public void setStock_id(Long stock_id) {
        this.stock_id = stock_id;
    }

    public Long getItem_id() {
        return item_id;
    }

    public void setItem_id(Long item_id) {
        this.item_id = item_id;
    }

    public Long getSku_id() {
        return sku_id;
    }

    public void setSku_id(Long sku_id) {
        this.sku_id = sku_id;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getEffective_time() {
        return effective_time;
    }

    public void setEffective_time(Date effective_time) {
        this.effective_time = effective_time;
    }

    public Date getSale_time() {
        return sale_time;
    }

    public void setSale_time(Date sale_time) {
        this.sale_time = sale_time;
    }

    public Date getCraete_time() {
        return craete_time;
    }

    public void setCraete_time(Date craete_time) {
        this.craete_time = craete_time;
    }
}
