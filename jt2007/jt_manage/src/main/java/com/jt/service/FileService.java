package com.jt.service;

import com.jt.vo.ImgVO;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    ImgVO doUpload(MultipartFile uploadFile);
}
