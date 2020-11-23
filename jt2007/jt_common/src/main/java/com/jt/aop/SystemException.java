package com.jt.aop;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.vo.SysResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

@RestControllerAdvice
public class SystemException {

    @ExceptionHandler(RuntimeException.class)
    public Object fail(RuntimeException e, HttpServletRequest request){
        String callback = request.getParameter("callback");
        if (callback.isEmpty()){
            return SysResult.fail();
        }else {
            return new JSONPObject(callback,SysResult.fail());
        }

    }

    //处理类型转换异常
    @ExceptionHandler(ClassCastException.class)
    public Object HandleClassCastException(ClassCastException e,HttpServletRequest request){
        //获取异常对象的栈队列
        StackTraceElement[] stackTrace = e.getStackTrace();
        //获取每一个元素的类名和方法名
        for(StackTraceElement ste:stackTrace){
            //出现异常的时间
            //出现异常的类名
            ste.getClassName();
            //出现异常的方法名
            ste.getMethodName();
            //出现异常的行号
            ste.getLineNumber();
        }
        String callback = request.getParameter("callback");
        if (callback.isEmpty()){
            return SysResult.fail("类型转换异常");
        }else {
            return new JSONPObject(callback,SysResult.fail("类型转换异常"));
        }
    }
}
