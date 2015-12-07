package com.caitu99.importdata.domain;

/**
 * Created by Lion on 2015/11/24 0024.
 */
public class Excel_item {
    private Long id;
    private String brand_name;
    private String pruduct_name;
    private String brand_code;
    private String pruduct_code;
    private String pruduct_type;
    private String pic;
    private Long month_sale;
    private Long sale_price;
    private Long market_price;
    private String range;
    private String content;
    private Long cost_price;


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
