package com.caitu99.importdata.service.impl;



import com.caitu99.importdata.dao.T_brandMapper;
import com.caitu99.importdata.domain.T_brand;
import com.caitu99.importdata.service.T_brandService;

import java.util.List;

/**
 * Created by Lion on 2015/11/24 0024.
 */
public class T_brandServiceImpl implements T_brandService {


    private T_brandMapper t_brandMapper;

    @Override
    public T_brand findByBrand_id(Long brand_id) {
        return t_brandMapper.findByBrand_id(brand_id);
    }

    @Override
    public List<T_brand> findAll() {
        return  t_brandMapper.findAll();
    }

    @Override
    public T_brand findByBrand_no(String brand_no) {
        return t_brandMapper.findByBrand_no(brand_no);
    }
}
