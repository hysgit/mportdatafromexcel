package com.caitu99.importdata.domain;

import java.util.Date;

/**
 * Created by Lion on 2015/11/23 0023.
 */
public class T_sku {

    private Long sku_id;//SKU_ID;               //bigint not null auto_increment comment '主键',
    private Long item_id;//ITEM_ID;            //  bigint not null comment '商品主键',
    private Long sale_price;//SALE_PRICE;       //    bigint not null comment '兑换价',
    private Long cost_price;
    private String version;//VERSION;      //        varchar(64) not null comment '版本号',
    private String prop_code;                   //@属性集合
    private String prop_name;               //@属性集合名称
    private Long dis_price;                     //@折后积分
    private Long rmb_price;                 //@折后人民币
    private Date create_time;//CREATE_TIME;  //        datetime not null comment '创建时间',
    private Date update_time;//UPDATE_TIME;//          datetime not null comment '更新时间',


    public String getProp_code() {
        return prop_code;
    }

    public void setProp_code(String prop_code) {
        this.prop_code = prop_code;
    }

    public String getProp_name() {
        return prop_name;
    }

    public void setProp_name(String prop_name) {
        this.prop_name = prop_name;
    }

    public Long getDis_price() {
        return dis_price;
    }

    public void setDis_price(Long dis_price) {
        this.dis_price = dis_price;
    }

    public Long getRmb_price() {
        return rmb_price;
    }

    public void setRmb_price(Long rmb_price) {
        this.rmb_price = rmb_price;
    }

    public Long getCost_price() {
        return cost_price;
    }

    public void setCost_price(Long cost_price) {
        this.cost_price = cost_price;
    }

    public Long getSku_id() {
        return sku_id;
    }

    public void setSku_id(Long sku_id) {
        this.sku_id = sku_id;
    }

    public Long getItem_id() {
        return item_id;
    }

    public void setItem_id(Long item_id) {
        this.item_id = item_id;
    }

    public Long getSale_price() {
        return sale_price;
    }

    public void setSale_price(Long sale_price) {
        this.sale_price = sale_price;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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
