package com.jt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.print.attribute.standard.PrinterURI;
import java.sql.ResultSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class SysResult {
    private Integer status; //200为成功，201失败
    private String msg;
    private Object data;



    public static SysResult fail(Exception e){
        return new SysResult(201    ,"请求失败，错误信息"+e.getMessage(),null);
    }

    public static SysResult success(Object data){
        return new SysResult(200,"请求成功",data);
    }
    public static SysResult success(String msg,Object data){
        return new SysResult(200,msg,data);
    }

}
