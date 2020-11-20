package com.jt.aop;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.vo.SysResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class SystemException {

    @ExceptionHandler(RuntimeException.class)
    public Object fail(RuntimeException e, HttpServletRequest request){
        String callback = request.getParameter("callback");
        if (callback.isEmpty()){
            return SysResult.fail();
        }else {
            return new JSONPObject(callback,SysResult.fail(e));
        }

    }
}
