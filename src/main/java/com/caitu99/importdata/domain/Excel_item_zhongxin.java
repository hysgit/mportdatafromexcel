package com.caitu99.importdata.domain;

/**
 * Created by Lion on 2015/11/24 0024.
 */
public class Excel_item_zhongxin {
    private Long id;
    private String brand_name;
    private String pruduct_name;
    private String brand_code;
    private String pruduct_code;
    private String pruduct_type;
    private String pic;
    private Long month_sale;        //
    private Long sale_price;        //
    private Long market_price;      //
    private String range;
    private String content;
    private Long cost_price;
    private String properties;
    private String link;
    private Integer limitedNum;
    //以下数据在产生cookie的时候用,要写入到数据表中
    private String warepic;      //图片url
    private String payType;     //支付类型
    private String warename;        //商品名称 第24条数据开始，后面添加（立即生效）
    private Long base_value;        //需要的分值
    private String goods_payway_id;
    private String vendor_id;
    private String vendor_nm;
    private String type_id;
    private String goods_nm;

    public String getGoods_nm() {
        return goods_nm;
    }

    public void setGoods_nm(String goods_nm) {
        this.goods_nm = goods_nm;
    }

    public String getGoods_payway_id() {
        return goods_payway_id;
    }

    public void setGoods_payway_id(String goods_payway_id) {
        this.goods_payway_id = goods_payway_id;
    }

    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }

    public String getVendor_nm() {
        return vendor_nm;
    }

    public void setVendor_nm(String vendor_nm) {
        this.vendor_nm = vendor_nm;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getWarepic() {
        return warepic;
    }

    public void setWarepic(String warepic) {
        this.warepic = warepic;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getWarename() {
        return warename;
    }

    public void setWarename(String warename) {
        this.warename = warename;
    }

    public Long getBase_value() {
        return base_value;
    }

    public void setBase_value(Long base_value) {
        this.base_value = base_value;
    }

    public Integer getLimitedNum() {
        return limitedNum;
    }

    public void setLimitedNum(Integer limitedNum) {
        this.limitedNum = limitedNum;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Long getCost_price() {
        return cost_price;
    }

    public void setCost_price(Long cost_price) {
        this.cost_price = cost_price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getPruduct_name() {
        return pruduct_name;
    }

    public void setPruduct_name(String pruduct_name) {
        this.pruduct_name = pruduct_name;
    }

    public String getBrand_code() {
        return brand_code;
    }

    public void setBrand_code(String brand_code) {
        this.brand_code = brand_code;
    }

    public String getPruduct_code() {
        return pruduct_code;
    }

    public void setPruduct_code(String pruduct_code) {
        this.pruduct_code = pruduct_code;
    }

    public String getPruduct_type() {
        return pruduct_type;
    }

    public void setPruduct_type(String pruduct_type) {
        this.pruduct_type = pruduct_type;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Long getMonth_sale() {
        return month_sale;
    }

    public void setMonth_sale(Long month_sale) {
        this.month_sale = month_sale;
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

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
