package com.caitu99.importdata.service.impl;


import com.caitu99.importdata.dao.T_area_storeMapper;
import com.caitu99.importdata.domain.T_area_store;
import com.caitu99.importdata.service.T_area_storeService;

/**
 * Created by Lion on 2015/11/24 0024.
 */

public class T_area_storeServiceImpl implements T_area_storeService {

    private T_area_storeMapper t_area_storeMapper;
    @Override
    public int insert(T_area_store t_area_store) {
       return t_area_storeMapper.insert(t_area_store);
    }

    @Override
    public T_area_store findByArea_store_id(Long area_store_id) {
        return t_area_storeMapper.findByArea_store_id(area_store_id);
    }
}
