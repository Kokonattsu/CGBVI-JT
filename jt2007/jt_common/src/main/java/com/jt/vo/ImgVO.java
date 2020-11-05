package com.jt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ImgVO {
    //错误信息，图片网址，宽，高
    private Integer error;
    private String url;
    private Integer width;
    private Integer height;

    public static ImgVO fail(){
        return new ImgVO(1,null,null,null);
    }
    public static ImgVO success(String url,Integer width,Integer height){
        return new ImgVO(0,url,width,height);
    }
}
