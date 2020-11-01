package com.jt;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.module.mapper.UserMapper;
import com.jt.module.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class SpringbootDemo02ApplicationTests {

    @Autowired
    private UserMapper  userMapper;

    //条件查询
    @Test   //根据where条件查询
    void contextLoads() {
        QueryWrapper<User> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("age", 18 )
                .eq("sex", "女");
        List<User> userList = userMapper.selectList(queryWrapper);
        System.out.println(userList);

    }



    @Test      //根据where-><条件查询
    void testSelect() {
        boolean flag=false;
        QueryWrapper<User> queryWrapper =new QueryWrapper<>();
        queryWrapper.ge(flag,"age", 18)
                .le(flag, "age", 2000);
        List<User> userList = userMapper.selectList(queryWrapper);
        System.out.println(userList);

    }

    @Test   //根据id集合查询（where-in）
    void testSelect3() {
        boolean flag=true;
        Integer[] id={1,2,3,4,5};
        List<Integer> idList = Arrays.asList(id);
        List<User> userList = userMapper.selectBatchIds(idList);
        System.out.println(userList);

    }
    @Test   //根据主键（id）查询
    void testSelect4() {
        User user =userMapper.selectById(1);
        System.out.println(user);
    }

    //添加
    @Test
    void Save(){
        try {
            User user=new User().setName("陈子枢").setAge(101);
            userMapper.insert(user);
        }catch (Exception e){
            DuplicateKeyException exception =
                    new DuplicateKeyException("该用户已经存在");
            exception.printStackTrace();

        }

    }
    //修改
    @Test
    void update(){
        User user=new User().setId(55).setName("张jj").setAge(1200);
       // userMapper.updateById(user);
        UpdateWrapper updateWrapper=new UpdateWrapper();
        updateWrapper.eq("name", "陈子枢");
        userMapper.update(user,updateWrapper);
    }

    @Test
    void delete(){
        QueryWrapper queryWrapper=new QueryWrapper();
        String sqlSegment = queryWrapper.getCustomSqlSegment();
        System.out.println("11111"+sqlSegment);
         System.out.println(userMapper.selectList(queryWrapper));
    }


}
