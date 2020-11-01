package com.jt.module.service.Impl;

import com.jt.module.mapper.UserMapper;
import com.jt.module.pojo.User;
import com.jt.module.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper  userMapper;


    @Override
    public List<User> findAllUser() {
//        List<User> list = userMapper.findAllUser();
//        return list;
        //mybatis-plus查询
        return userMapper.selectList(null);

    }
}
