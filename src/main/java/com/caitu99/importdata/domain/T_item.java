package com.caitu99.importdata.domain;

import java.util.Date;

/**
 * Created by Lion on 2015/11/23 0023.
 */
public class T_item {
    private Long item_id;//ITEM_ID ;          //   bigint not null auto_increment comment '主键',
    private String title;//TITLE;          //      varchar(256) not null comment '标题',
    private String sub_title;//SUB_TITLE;        //    varchar(128) comment '推广标题',
    private String item_no;//ITEM_NO;         //     varchar(32) not null comment '编号',
    private Long brand_id;//BRAND_ID;          //   bigint not null comment '所属品牌',
    private Long sale_price;//SALE_PRICE;        //   bigint not null comment '售价(单位：财分) 细项最低价',
    private Long market_price;//MARKET_PRICE;      //   bigint comment '市场价（单位：分，RMB）',
    private Long sale_volume;//SALE_VOLUME ;     //    bigint default 0 comment '月销量(仅显示)',
    private Long cost_price;
    private String content;//CONTENT   ;     ///      longtext comment '内容',
    private String version;//VERSION  ;     //       varchar(64) not null comment '版本',
    private Integer status;//STATUS  ;     //        int not null comment '商品状态  -1 删除 0 下架 1上架',
    private Date list_time;//LIST_TIME  ;     //     datetime comment '上架时间',
    private Date delist_time;//DELIST_TIME;     //     datetime comment '下架时间',
    private String wap_url;//WAP_URL  ;      //      varchar(256) comment 'WAP地址',
    private Long sort;//SORT      ; //          bigint comment '排序',
    private String pic_url;         //          商品头像
    private Date create_time;//CREATE_TIME ;      //   datetime not null comment '创建时间',
    private Date update_time;//UPDATE_TIME  ;    //    datetime not null comment '修改时间',

    public Long getCost_price() {
        return cost_price;
    }

    public void setCost_price(Long cost_price) {
        this.cost_price = cost_price;
    }

    public Long getItem_id() {
        return item_id;
    }

    public void setItem_id(Long item_id) {
        this.item_id = item_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSub_title() {
        return sub_title;
    }

    public void setSub_title(String sub_title) {
        this.sub_title = sub_title;
    }

    public String getItem_no() {
        return item_no;
    }

    public void setItem_no(String item_no) {
        this.item_no = item_no;
    }

    public Long getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(Long brand_id) {
        this.brand_id = brand_id;
    }

    public Long getSale_price() {
        return sale_price;
    }

    public void setSale_price(Long sale_price) {
        this.sale_price = sale_price;
    }

    public Long getMarket_price() {
        return market_price;
    }

    public void setMarket_price(Long market_price) {
        this.market_price = market_price;
    }

    public Long getSale_volume() {
        return sale_volume;
    }

    public void setSale_volume(Long sale_volume) {
        this.sale_volume = sale_volume;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getList_time() {
        return list_time;
    }

    public void setList_time(Date list_time) {
        this.list_time = list_time;
    }

    public Date getDelist_time() {
        return delist_time;
    }

    public void setDelist_time(Date delist_time) {
        this.delist_time = delist_time;
    }

    public String getWap_url() {
        return wap_url;
    }

    public void setWap_url(String wap_url) {
        this.wap_url = wap_url;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
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

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }
}
