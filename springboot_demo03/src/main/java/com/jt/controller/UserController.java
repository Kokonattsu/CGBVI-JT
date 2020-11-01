package com.jt.controller;


import com.jt.pojo.User;
import com.jt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/findAll")
    public String findAll(Model model){
        model.addAttribute("userList",userService.findAll() );
        return "userList";
    }

    //返回页面
    @GetMapping("ajax")
    public String ajax(){
        return "ajax";
    }

    //查询所有对象（无条件）
    @ResponseBody
    @GetMapping("findAjax")
    public List<User> findAjax(){
        List<User> userList = userService.findAll();
        return userList;
    }

    //根据条件查询所有对象
    @ResponseBody
    @PostMapping("/doFindObjectByConditions")
    public List<User> doFindObjectByConditions(String condition){
        List<User> userList = userService.doFindObjectByConditions(condition);
        return userList;
    }

    //根据id删除对象

}
