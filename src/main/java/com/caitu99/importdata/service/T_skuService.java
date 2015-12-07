package com.caitu99.importdata.service;

import com.caitu99.importdata.domain.T_sku;

import java.util.List;

/**
 * Created by Lion on 2015/11/24 0024.
 */
public interface T_skuService {
    int insert(T_sku t_sku);

    T_sku findBySku_id(Long sku_id);
    List<T_sku> findByItem_id(Long item_id);
}
