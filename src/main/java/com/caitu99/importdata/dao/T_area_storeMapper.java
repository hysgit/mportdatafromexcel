package com.caitu99.importdata.dao;

import com.caitu99.importdata.domain.T_area_store;

/**
 * Created by Lion on 2015/11/24 0024.
 */
public interface T_area_storeMapper {
    int insert(T_area_store t_area_store);
    T_area_store findByBRAND_ID(Long brand_id);

    T_area_store findByArea_store_id(Long area_store_id);
}
