package com.changgou.service.impl;

import com.changgou.dao.BrandMapper;
import com.changgou.goods.pojo.Brand;
import com.changgou.service.BrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;


    /**
     * 分页+条件搜索
     * @param brand: 搜索条件
     * @param page:当前页
     * @param size：每页显示的条数
     */
    @Override
    public PageInfo<Brand> findPage(Brand brand, Integer page, Integer size) {
        //分页
        PageHelper.startPage(page,size);
        //搜索数据 name不为空，则根据名字模糊搜索，letter不为空，则根据letter搜索
        Example example = createExample(brand);
        List<Brand> brands =brandMapper.selectByExample(example);
        //封装PageInfo<Brand>
        return new PageInfo<Brand>(brands);
    }

    /**
     * 分页查询
     * @param page:当前页
     * @param size：每页显示的条数
     * @return
     */
    @Override
    public PageInfo<Brand> findPage(Integer page, Integer size) {
        /**
         * 分页实现  PageHelper.startPage(page,size);分页实现，后面的查询紧跟集合查询
         * 1：当前页
         * 2:每页显示多少条
         */
        PageHelper.startPage(page,size);
        //查询集合
        List<Brand> brands = brandMapper.selectAll();

        //封装PageInfo-list
        return new PageInfo<Brand>(brands);
    }

    /**
     * 多条件搜索
     * @param brand
     * @return
     */
    @Override
    public List<Brand> findList(Brand brand) {
        Example example = createExample(brand);
        return brandMapper.selectByExample(example);
    }

    /**
     * 条件构建
     * @param brand
     * @return
     */
    public Example createExample(Brand brand){
        //自定义条件搜索对象 Example
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();//条件构造器
        if(brand != null){
            //brand.name!=null根据名字模糊搜索 where name like '%华%'
            if(!StringUtils.isEmpty(brand.getName())){
                /**
                 * 1：Brand的属性名
                 * 2: 占位符参数，搜索的条件
                 */
                criteria.andLike("name","%"+brand.getName()+"%");
            }
            //brand.letter!=null根据首字母搜索
            if(!StringUtils.isEmpty(brand.getLetter())){
                criteria.andEqualTo("letter",brand.getLetter());
            }
        }
        return example;
    }

    /**
     * 根据id删除
     * @param id
     */
    @Override
    public void delete(String id) {
        brandMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据id修改品牌
     * @param brand
     */
    @Override
    public void update(Brand brand) {
        /**
         * 使用通用Mapper.updateByPrimaryKeySelective(brand) 组装的sql语句会忽略空值
         */
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    /**
     * 增加品牌
     * @param brand
     */
    @Override
    public void add(Brand brand) {
        /**
         *使用通用Mapper.insertSelectvie(T)实现增加
         * 方法中但凡带有selective，会忽略空值
         * brand:name有值
         * letter有值
         * Mapper.insertSelective(brand)->Sql语句：insert into tb_brand(name,letter) value (?,?)
         * 不忽略空值
         * Mapper.insert(brand) ->sql语句：insert into tb_brand (id,name,image,letter,seq) values(?,?,?,?,?)
         */

        brandMapper.insertSelective(brand);
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    public Brand findById(String id) {
        //根据id查询-》通用maper
        return brandMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<Brand> findAll() {
        //查询所有-》通用Mapper.selectAll()
        return brandMapper.selectAll();
    }
}
