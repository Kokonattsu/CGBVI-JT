package com.jt.service;

import com.jt.vo.ImgVO;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileServiceImpl implements FileService{
    @Override
    public ImgVO doUpload(MultipartFile uploadFile) {
        //校验图片类型，检验是否恶意文件（检验宽和高），分目录，重命名uuid

//  -------获取图片类型并校验----
        //获取图片名，转化成小写，截取后缀，跟set中的.jpg/.png/.git比较


//  -------校验是否恶意文件-------
        //ImageIO.read()

//  -------分目录储存----------
        //获取当前时间，格式化成/yyyy/MM/dd/，转化成字符串作为储存图片的地址
        //判断文件夹是否存在，没有则创建文件夹
//        String fileDir=new SimpleDateFormat("/yyyy/MM/dd/")
//                .format(new Date());

//  -------以UUID命名防止图片重复---------
        //

        return null;
    }
}
