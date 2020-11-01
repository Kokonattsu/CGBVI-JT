package com.jt.module.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {

    @GetMapping("/page")
   // @ResponseBody
    public String page(){
        System.out.println("111111111111");
        return "test";
    }
}
