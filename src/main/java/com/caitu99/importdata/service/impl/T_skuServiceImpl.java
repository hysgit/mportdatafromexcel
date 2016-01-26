package com.caitu99.importdata.service.impl;


import com.caitu99.importdata.dao.T_skuMapper;
import com.caitu99.importdata.domain.T_sku;
import com.caitu99.importdata.service.T_skuService;

import java.util.List;

/**
 * Created by Lion on 2015/11/24 0024.
 */

public class T_skuServiceImpl implements T_skuService {


    private T_skuMapper t_skuMapper;


    @Override
    public int insert(T_sku t_sku) {
        return 0;
    }

    @Override
    public T_sku findBySku_id(Long sku_id) {
        return t_skuMapper.findBySku_id(sku_id);
    }

    @Override
    public List<T_sku> findByItem_id(Long item_id) {
        return t_skuMapper.findByItem_id(item_id);
    }


}
