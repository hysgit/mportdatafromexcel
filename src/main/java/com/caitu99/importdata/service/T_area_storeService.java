package com.caitu99.importdata.service;

import com.caitu99.importdata.domain.T_area_store;

/**
 * Created by Lion on 2015/11/24 0024.
 */
public interface T_area_storeService {
    int insert(T_area_store t_area_store);
    T_area_store findByArea_store_id(Long area_store_id);
}
