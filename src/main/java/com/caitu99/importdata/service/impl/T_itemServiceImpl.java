package com.caitu99.importdata.service.impl;


import com.caitu99.importdata.dao.T_itemMapper;
import com.caitu99.importdata.domain.T_item;
import com.caitu99.importdata.service.T_itemService;

/**
 * Created by Lion on 2015/11/24 0024.
 */

public class T_itemServiceImpl implements T_itemService {


    private T_itemMapper t_itemMapper;

    @Override
    public int insert(T_item t_item) {
        return 0;
    }

    @Override
    public T_item findByItem_id(Long item_id) {
        return t_itemMapper.findByItem_id(item_id);
    }

    @Override
    public T_item findByItem_no(String item_no) {
        return t_itemMapper.findByItem_no(item_no);
    }
}
