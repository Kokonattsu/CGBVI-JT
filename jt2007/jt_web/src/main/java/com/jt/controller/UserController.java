package com.jt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @RequestMapping("/user/{moduleName}")
    public String getPage(@PathVariable String moduleName){
        return moduleName;
    }
}
