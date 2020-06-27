package com.changgou.controller;

import com.changgou.goods.pojo.Brand;
import com.changgou.service.BrandService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/brand")
@CrossOrigin
/***
 * 跨域：A域名访问B域名的数据
 *      域名或者请求端口或者协议不一致的时候，就跨域了
 *
 */
public class BrandController {

    @Autowired
    private BrandService brandService;

    /**
     * 查询所有品牌
     */
    @GetMapping
    public Result<List<Brand>> findAll(){
        //查询所有品牌
        List<Brand> brands = brandService.findAll();
        //相应结果封装
        return new Result<List<Brand>> (true, StatusCode.OK,"查询品牌集合成功！",brands);
    }

    /**
     * 测试git提交流程
     */
}
