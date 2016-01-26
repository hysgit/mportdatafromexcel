package com.caitu99.importdata.dao;

import com.caitu99.importdata.domain.T_brand;

import java.util.List;

/**
 * Created by Lion on 2015/11/24 0024.
 */
public interface T_brandMapper {

    T_brand findByBrand_id(Long brand_id);
    List<T_brand> findAll();

    T_brand findByBrand_no(String brand_no);

}
