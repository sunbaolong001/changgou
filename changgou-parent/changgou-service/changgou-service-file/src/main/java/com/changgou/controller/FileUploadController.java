package com.changgou.controller;

import com.changgou.file.FastDFSFile;
import com.changgou.util.FastDFSUtil;
import entity.Result;
import entity.StatusCode;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/upload")
@CrossOrigin
public class FileUploadController {

    /**
     * 文件上传
     */
    @PostMapping
    public Result upload (@RequestParam(value = "file")MultipartFile file) throws Exception{
        //调用FastDFSUtil工具类将文件传入到FastDFS中
        FastDFSFile fastDFSFile = new FastDFSFile(
                file.getOriginalFilename(),//1、文件名字
                file.getBytes(),//文件字节数组
                StringUtils.getFilenameExtension(file.getOriginalFilename())  //获取文件扩展名
        );
        String[] uploads = FastDFSUtil.upload(fastDFSFile);
        /**
         * 拼接访问地址url： http://192.168.0.107:8080/group1/M00/00/00/111.jpg
         * 1、8080端口为nginx的端口
         */
        String url = "http://192.168.0.107:8080/"+uploads[0]+"/"+uploads[1];
        return new Result(true, StatusCode.OK, "上传成功",url);
    }

}
