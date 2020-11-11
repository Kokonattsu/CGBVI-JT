package com.jt.service;

import com.jt.vo.ImgVO;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@PropertySource("classpath:/properties/image.properties")
public class FileServiceImpl implements FileService{

    @Value("${image.uploadFileDir}")
    String uploadFileDir;       //="e:/dir/";//根目录
    @Value("${image.urlPath}")
    String urlPath;             //http://image.jt.com
    private static Set<String> imgType=new HashSet<>();

    static {
        imgType.add(".jpg");
        imgType.add(".png");
        imgType.add(".gif");
    }
    @Override
    public ImgVO doUpload(MultipartFile uploadFile) {
        //校验图片类型，检验是否恶意文件（检验宽和高），分目录，重命名uuid

//  -------获取图片类型并校验----
        //获取图片名，转化成小写，截取后缀，跟set中的.jpg/.png/.git比较
        String filename = uploadFile.getOriginalFilename();
        filename= filename.toLowerCase();
        int index = filename.lastIndexOf(".");
        String fileType = filename.substring(index);
        if (!imgType.contains(fileType)){
            return ImgVO.fail();
        }

//  -------校验是否恶意文件-------
        try {
            BufferedImage image = ImageIO.read(uploadFile.getInputStream());//将文件转成图片流
            int height = image.getHeight();
            int width = image.getWidth();
            if(width==0||height==0){
                return ImgVO.fail();
            }


//  -------分目录储存----------
        //获取当前时间，格式化成/yyyy/MM/dd/，转化成字符串作为储存图片的地址
        //判断文件夹是否存在，没有则创建文件夹
            String dateDir=new SimpleDateFormat("/yyyy/MM/dd/")
                .format(new Date());
        //具体储存图片的地址
            String fileDirPath=uploadFileDir+dateDir;
            File fileDir=new File(fileDirPath);
            if (!fileDir.exists()){
                fileDir.mkdirs();
            }


//  -------以UUID命名防止图片重复---------
        //用空串把UUID的横杠替换掉
            String newFileName = UUID.randomUUID().toString().replace("-", "");
        //图片文件地址:图片具体地址+图片名+后缀
            File imgFilePath=new File(fileDirPath+newFileName+fileType);
        //把图片数据注入图片文件
            uploadFile.transferTo(imgFilePath);

        //网络地址：域名+磁盘根目录+时间地址+图片类型
            String imgUrl=urlPath+dateDir+newFileName+fileType;
            ImgVO vo = ImgVO.success(imgUrl, width, height);
            return vo;

        } catch (IOException e) {
            e.printStackTrace();
            return ImgVO.fail();
        }


    }
}
