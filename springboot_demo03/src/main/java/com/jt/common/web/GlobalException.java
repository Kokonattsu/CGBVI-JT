package com.jt.common.web;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ResponseBody
    @ExceptionHandler(value = RuntimeException.class)
    public String doHandlerRuntimeException(){
        return null;
    }
}
