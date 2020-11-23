package com.jt.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.CartMapper;
import com.jt.pojo.Cart;
import com.jt.pojo.Order;
import com.jt.pojo.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Service(timeout = 3000)
public class DubboCartServiceImpl implements DubboCartService{

    @Autowired
    private CartMapper cartMapper;
    //查询用户的购物车
    @Override
    public List<Cart> findCartByUserId(Long userId) {
        QueryWrapper<Cart> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        return cartMapper.selectList(queryWrapper);
    }
    //修改购物车商品数量
    @Override
    public void updateNum(Cart cart) {
        Cart upCart = new Cart().setNum(cart.getNum());
        QueryWrapper queryWrapper =new QueryWrapper();
        queryWrapper.eq("item_id", cart.getItemId());
        queryWrapper.eq("user_id", cart.getUserId());
        cartMapper.update(upCart, queryWrapper);
    }

    //删除购物车
    @Override
    public void doDeleteCart(Cart cart) {
        QueryWrapper<Cart> queryWrapper=new QueryWrapper<>(cart);
        cartMapper.delete(queryWrapper);
    }

    //添加购物车，如果已存在则添加数量
    @Override
    public void addCart(Cart cart) {
        QueryWrapper<Cart> queryWrapper =new QueryWrapper();
        queryWrapper.eq("item_id", cart.getItemId());
        queryWrapper.eq("user_id", cart.getUserId());
        Cart DBcart = cartMapper.selectOne(queryWrapper);

        if (DBcart==null){
            //不存在时
            cartMapper.insert(cart);
        }else {
            //存在时
            Integer num=cart.getNum()+DBcart.getNum();
            cartMapper.updateById(new Cart().setNum(num).setId(DBcart.getId()));
        }

    }

    @Override
    public Integer deleteCartsByOrder(Order order) {
        List<OrderItem> itemList = order.getOrderItems();
        List<Long> itemIds=new ArrayList();
        for (OrderItem item:itemList){
            long itemId = Long.parseLong(item.getItemId());
            itemIds.add(itemId);
        }
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("user_id", order.getUserId());
        queryWrapper.in("item_id", itemIds);
        int row = cartMapper.delete(queryWrapper);
        return row;
    }
}
