package com.jt.module.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${aaa.bbb}")
    private String value;

    @GetMapping("/index")
    public String getValue(){
        return value;
    }


}
