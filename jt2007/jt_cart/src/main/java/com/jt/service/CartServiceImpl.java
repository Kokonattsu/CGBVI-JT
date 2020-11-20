package com.jt.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jt.mapper.CartMapper;
import com.jt.pojo.Cart;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(timeout = 3000)
public class CartServiceImpl implements DubboCartService{

    @Autowired
    private CartMapper cartMapper;
    @Override
    public List<Cart> findCart() {

        return null;
    }
}
