package com.jt.module.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@PropertySource(
        value = "classpath:/properties/redis.properties",
        encoding = "utf-8")
@RestController
public class RedisPorController {

//    @Value("${redis.proHost}")
//    private String proHost;
//    @Value("${redis.proPort}")
//    private String proPort;
//
//    @GetMapping("/getMsg")
//    public String getMsg(){
//        return proHost+":"+proPort;
//    }
}
