package com.caitu99.importdata.domain;

/**
 * Created by Lion on 2015/11/23 0023.
 */
public class T_area_store {

    private Long area_store_id; //AREA_STORE_ID;//        bigint not null auto_increment comment '主键',
    private Long brand_id;//BRAND_ID;      //             bigint not null comment '品牌主键',
    private String province;//PROVINCE;         //             varchar(64) not null comment '省',
    private String city;//CITY;       //                varchar(64) not null comment '市',
    private String shop_name;//SHOP_NAME;     //       varchar(64) not null comment '店铺名称',
    private String shop_address;//SHOP_ADDRESS;//         varchar(512) not null comment '店铺地址',
    private String shop_phone;//SHOP_PHONE;          //           varchar(16) comment '店铺联系方式',
    private String create_time;//CREATE_TIME;     //          datetime not null comment '创建时间',
    private String update_time;//UPDATE_TIME;     //          datetime not null comment '修改时间',

    public Long getArea_store_id() {
        return area_store_id;
    }

    public void setArea_store_id(Long area_store_id) {
        this.area_store_id = area_store_id;
    }

    public Long getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(Long brand_id) {
        this.brand_id = brand_id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_address() {
        return shop_address;
    }

    public void setShop_address(String shop_address) {
        this.shop_address = shop_address;
    }

    public String getShop_phone() {
        return shop_phone;
    }

    public void setShop_phone(String shop_phone) {
        this.shop_phone = shop_phone;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }
}
