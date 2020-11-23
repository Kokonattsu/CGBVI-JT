package com.jt.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    private static Map<Integer,String> paramMap=new HashMap<>();
    static {
        paramMap.put(1, "username");
        paramMap.put(2, "phone");
        paramMap.put(3, "email");
    }

    //测试查询
    @Override
    public List<User> findObjects() {
        return userMapper.selectList(null);
    }

    //注册验证
    @Override
    public boolean checkUser(String param, Integer type) {
        QueryWrapper<User> queryWrapper=new QueryWrapper();
        String columu=paramMap.get(type);
        queryWrapper.eq(columu, param);
        Integer row = userMapper.selectCount(queryWrapper);
        //当用户存在时返回值就为true
        return row>0;
    }
}
