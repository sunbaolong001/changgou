package com.changgou.controller;

import com.changgou.goods.pojo.Brand;
import com.changgou.service.BrandService;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
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
     *
     */
    @PostMapping(value = "/search/{page}/{size}")
    public Result<PageInfo<Brand>>findPage(@RequestBody Brand brand,
                                           @PathVariable(value = "page")Integer page,//当前页
                                           @PathVariable(value = "size")Integer size){//每页显示的条数
        int q =10/0;
        //调用service实现查询
        PageInfo<Brand> pageInfo = brandService.findPage(brand,page,size);
        return new Result<PageInfo<Brand>>(true,StatusCode.OK,"分页查询成功",pageInfo);
    }

    /**
     * 分页查询实现
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageInfo<Brand>>findPage(@PathVariable(value = "page")Integer page,//当前页
                                   @PathVariable(value = "size")Integer size){//每页显示的条数
        //调用service实现查询
        PageInfo<Brand> pageInfo = brandService.findPage(page,size);
        return new Result<PageInfo<Brand>>(true,StatusCode.OK,"分页查询成功",pageInfo);
    }

    /**
     * 条件查询
     */
    @PostMapping(value = "/search")
    public Result<List<Brand>>findList(@RequestBody Brand brand){
        List<Brand> brands = brandService.findList(brand);
        return new Result<List<Brand>>(true,StatusCode.OK,"条件搜索",brands);
    }

    /**
     * 根据id删除
     */
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable(value = "id") String id){
        brandService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /**
     * 品牌修改实现
     */
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable(value = "id") String id ,@RequestBody Brand brand){
        brand.setId(id);
        brandService.update(brand);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /**
     * 增加品牌
     */
    @PostMapping
    public Result add (@RequestBody Brand brand){
        //调用service实现增加操作
        brandService.add(brand);
        return new Result(true,StatusCode.OK,"增加品牌成功！");
    }

    /**
     * 根据主键id查询
     */
    @GetMapping(value = "/{id}")
    public Result<Brand> findById(@PathVariable(value = "id") String id){
        //调用service实现查询
        Brand brand = brandService.findById(id);
        return new Result<Brand>(true,StatusCode.OK,"根据ID查询成功",brand);
    }

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
}
