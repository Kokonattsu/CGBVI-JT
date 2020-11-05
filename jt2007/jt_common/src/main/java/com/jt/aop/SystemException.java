package com.jt.aop;

import com.jt.vo.SysResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SystemException {

    @ExceptionHandler(RuntimeException.class)
    public Object fail(RuntimeException e){
        return SysResult.fail(e);
    }
}
