package com.changgou.service;

import com.changgou.goods.pojo.Brand;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BrandService {

    /**
     * 分页+条件搜索
     * @param brand: 搜索条件
     * @param page:当前页
     * @param size：每页显示的条数
     */
    PageInfo<Brand> findPage(Brand brand, Integer page, Integer size);

    /**
     * 条件搜索
     * @param page:当前页
     * @param size：每页显示的条数
     */
    PageInfo<Brand> findPage(Integer page, Integer size);

    /**
     * 根据品牌信息多条件搜索
     */
    List<Brand> findList(Brand brand);

    /**
     * 根据id删除
     * @Param id
     */
    void delete(String id);

    /**
     * 根据id修改品牌数据
     */
    void update(Brand brand);

    /**
     * 增加品牌
     * @Param brand
     */
    void add(Brand brand);

    /**
     * 根据id查询
     */
    Brand findById(String id);

    /**
     * 查询所有
     */
    List<Brand> findAll();
}
