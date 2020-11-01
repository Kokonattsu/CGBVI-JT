package com.jt.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import com.jt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper usermapper;
    @Override
    public List<User> findAll() {

        return usermapper.selectList(null);
    }

    @Override
    public List<User> doFindObjectByConditions(String condition) {
        boolean flag=condition.getBytes().length!=0;
        QueryWrapper query=new QueryWrapper();

        query.eq(flag, "id",condition).or();
        query.eq(flag,"age",condition).or();
        query.eq(flag, "name", condition).or();
        query.eq(flag, "sex", condition);

        query.getSqlSegment();
        List list = usermapper.selectList(query);


        return list;
    }
}
