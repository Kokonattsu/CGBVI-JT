package com.jt.controller;

import com.jt.pojo.User;
import com.jt.service.UserService;
import com.jt.util.ObjectMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/{moduleName}")
    public String getPage(@PathVariable String moduleName){
        return moduleName;
    }

    @RequestMapping("/testHttpClient")
    @ResponseBody
    public String testHttpClient(){

        return ObjectMapperUtil.toJSON(userService.testHttpClient());
    }
}
