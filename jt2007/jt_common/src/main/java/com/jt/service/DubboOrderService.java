package com.jt.service;

import com.jt.pojo.Order;
import org.springframework.transaction.annotation.Transactional;

public interface DubboOrderService {
    Order findOrderById(String id);
    @Transactional
    String saveOrder(Order order);
}
