package com.changgou.service;

import com.changgou.goods.pojo.Brand;

import java.util.List;

public interface BrandService {

    /**
     * 根据id查询
     */
    Brand findById(String id);

    /**
     * 查询所有
     */
    List<Brand> findAll();
}
