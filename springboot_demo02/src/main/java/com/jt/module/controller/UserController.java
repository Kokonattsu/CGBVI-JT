package com.jt.module.controller;

import com.jt.module.pojo.User;
import com.jt.module.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //查询所有用户
    @GetMapping("/findAll")
    public List<User> dofindAll(){
        System.out.println("aaaaaa");
        return userService.findAllUser();

    }
}
