package com.jt.service;

import com.jt.pojo.Cart;

import java.util.List;

public interface DubboCartService {

    List<Cart> findCart();
}
