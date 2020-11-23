package com.jt.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.OrderItemMapper;
import com.jt.mapper.OrderMapper;
import com.jt.mapper.OrderShippingMapper;
import com.jt.pojo.Order;
import com.jt.pojo.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service(timeout = 3000)
public class OrderServiceImpl implements DubboOrderService{

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private OrderShippingMapper orderShippingMapper;

    //订单入库（添加订单）
    @Override
    public String saveOrder(Order order) {
        String nowTime=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        String orderId=""+order.getUserId()+ nowTime;
        //补充订单id
        order.setOrderId(orderId);
        orderMapper.insert(order);
        List<OrderItem> itemList = order.getOrderItems();
        for (OrderItem item:itemList){
            item.setOrderId(orderId);
            orderItemMapper.insert(item);
        }
        //创建物流单
        orderShippingMapper.insert(order.getOrderShipping());
        //删除已经入库的购物车商品



        //返回订单id
        return order.getOrderId();
    }

    //根据订单号查询订单
    @Override
    public Order findOrderById(String id) {
        Order order = orderMapper.selectById(id);
        orderShippingMapper.selectById(id);
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("order_id", id);
        List orderList = orderItemMapper.selectList(queryWrapper);
        order.setOrderItems(orderList);
        return  order;
    }
}
