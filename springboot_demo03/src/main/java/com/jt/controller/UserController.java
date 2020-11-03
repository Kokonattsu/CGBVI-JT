package com.jt.controller;


import com.jt.pojo.User;
import com.jt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping("/doDeleteById")
    public Integer doDeleteById(Integer userId){
        return userService.doDeleteById(userId);
    }

    //添加
    @PostMapping("")
    public Integer doSaveObject(User user){
        return userService.doSaveObject(user);
    }

    //根据id修改
    @PutMapping("")
    public Integer doUpdateById(User user){
        return userService.doUpdateById(user);
    }
}
