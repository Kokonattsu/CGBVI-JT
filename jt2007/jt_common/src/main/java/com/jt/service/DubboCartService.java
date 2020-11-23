package com.jt.service;

import com.jt.pojo.Cart;
import com.jt.pojo.Order;

import java.util.List;

public interface DubboCartService {

    List<Cart> findCartByUserId(Long userId);

    void updateNum(Cart cart);

    void doDeleteCart(Cart cart);

    void addCart(Cart cart);

    Integer deleteCartsByOrder(Order order);
}
