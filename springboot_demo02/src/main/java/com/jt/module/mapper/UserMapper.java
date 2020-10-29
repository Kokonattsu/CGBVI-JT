package com.jt.module.mapper;

import com.jt.module.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select id,name,age,sex from user")
    List<User> findAllUser();
}
