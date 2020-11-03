package com.jt.common.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@Slf4j
@RestControllerAdvice
public class GlobalException {

    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public String doHandlerRuntimeException(RuntimeException e){
        String message = e.getMessage();
        log.error("Exception msg=>{}"+message);
        return message;
    }
}
