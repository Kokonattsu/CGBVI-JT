package com.jt.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import com.jt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;

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
        query.like(flag, "name", condition).or();
        query.eq(flag, "sex", condition);

        query.getSqlSegment();
        List list = usermapper.selectList(query);


        return list;
    }

    //根据id删除
    @Override
    public Integer doDeleteById(Integer userId) {
        if(userId==0){
            throw new IllegalArgumentException("删除需要一个指定的id");
        }
        int row = usermapper.deleteById(userId);
        if (row==0){
            throw new IllegalArgumentException("需要删除的数据可能已经不再了");
        }
        return row;
    }

    //添加
    @Override
    public Integer doSaveObject(User user) {
        if (user.getName()==null){
            throw new IllegalArgumentException("用户名不能为空");
        }
        int row = usermapper.insert(user);
        return row;
    }

    //根据id修改
    @Override
    public Integer doUpdateById(User user) {
        if (user==null||user.getId()==null||user.getName()==null){
            throw new IllegalArgumentException("请给出正确的修改参数");
        }
        int row = usermapper.updateById(user);
        if (row==0){
            throw new IllegalArgumentException("需要修改的数据可能已经不在了");
        }
        return row;
    }




}
