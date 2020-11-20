package com.jt.service;

import com.jt.pojo.User;


public interface DubboUserService {
    Integer saveUser(User user);

    String doLogin(User user);
}
