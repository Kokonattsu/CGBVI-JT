package com.jt.controller;

import com.jt.service.FileService;
import com.jt.vo.ImgVO;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    @RequestMapping("/pic/upload")
    public ImgVO doUpload(MultipartFile uploadFile){
        return fileService.doUpload(uploadFile);

    }

    @RequestMapping("/file")
    public SysResult doUploadDemo(MultipartFile ImgFile)throws IOException {
        //获取图片名称，准备文件上传目录，利用对象封装路径，如果没有则创建目录
        //实现文件上传
        String imgName = ImgFile.getOriginalFilename();
        String dir="E:/dir";
        File dirFile=new File(dir);
        if (!dirFile.exists()){
            dirFile.mkdirs();//创建文件夹
        }
        File uploadFile=new File(dirFile+"/"+imgName);
        ImgFile.transferTo(uploadFile); //用流把文件数据传入文件
        return SysResult.success("文件上传成功！");
    }


}
