package com.caitu99.importdata.dao;

import com.caitu99.importdata.domain.T_item;

/**
 * Created by Lion on 2015/11/24 0024.
 */
public interface T_itemMapper {
    T_item findByItem_id(Long item_id);
    T_item findByItem_no(String item_no);
}
