package com.jt.Service;

import com.jt.pojo.User;

import java.util.List;

public interface UserService {

    List<User> findObjects();

    boolean checkUser(String param, Integer type);
}
