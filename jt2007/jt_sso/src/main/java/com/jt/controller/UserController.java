package com.jt.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.Service.UserService;
import com.jt.pojo.User;
import com.jt.util.ObjectMapperUtil;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/findAll")
    public SysResult findAll(){
        List<User> userList=userService.findObjects();

        return SysResult.success(userList);
    }

    @RequestMapping("/user/check/{param}/{type}")
    public JSONPObject checkUser(@PathVariable String param,
                               @PathVariable Integer type,
                               String callback){
        boolean flag=userService.checkUser(param,type);
        SysResult result = SysResult.success(flag);
        //int i=1/0;
        return new JSONPObject(callback, result);
    }

    @RequestMapping("/user/testhttpClient")
    public String testhttpClient(){
        List<User> userList=userService.findObjects();

        return ObjectMapperUtil.toJSON(userList);
    }
}
