package com.jt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.Cart;
import com.jt.pojo.Order;
import com.jt.pojo.OrderItem;
import com.jt.service.DubboCartService;
import com.jt.service.DubboOrderService;
import com.jt.util.UserThreadLocal;
import com.jt.vo.SysResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Reference(check = false)
    private DubboCartService cartService;
    @Reference(check = false)
    private DubboOrderService orderService;


    /**
     * http://www.jt.com/order/create.html
     *  ${carts}
     */
    //创建订单（根据用户id获取购物车传给model）
    @RequestMapping("/create")
    public String createOrder(Model model){
        Long userId= UserThreadLocal.get().getId();
        List<Cart> cartList = cartService.findCartByUserId(userId);
        model.addAttribute("carts", cartList);
        return "order-cart";
    }

    /**
     * http://www.jt.com/order/submit
     * POST
     * paymentType: 1
     * orderItems[0].itemId: 562379
     * orderItems[0].num: 2
     * orderItems[0].price: 4299000
     * orderItems[0].totalFee: 8598000
     * orderItems[0].title: 三星 W999 黑色 电信3G手机 双卡双待双通
     * orderItems[0].picPath: http://image.taotao.com/jd/d2ac340e728d4c6181e763e772a9944a.jpg
     * payment: 85980.00
     * orderShipping.receiverName: 陈晨
     * orderShipping.receiverMobile: 13800807944
     * orderShipping.receiverState: 北京
     * orderShipping.receiverCity: 北京
     * orderShipping.receiverDistrict: 海淀区
     * orderShipping.receiverAddress: 清华大学
     */
    //提交订单（订单入库，返回订单号）
    @RequestMapping("/submit")
    @ResponseBody
    public SysResult diSubmit(Order order){
        Long userId=UserThreadLocal.get().getId();
        order.setUserId(userId);
        String orderId=orderService.saveOrder(order);
        //删除购物车中已经提交的商品
        Integer row=cartService.deleteCartsByOrder(order);
        return SysResult.success(orderId);
    }

    /**
     * http://www.jt.com/order/success.html?id=71518020863347
     */
    //成功页面（根据订单id查询订单信息传给model）
    @RequestMapping("/success")
    public String success(String id,Model model){
        Order order=orderService.findOrderById(id);
        model.addAttribute("order", order);
        //获取当前时间4天后的时间
        LocalDate date = LocalDate.now().plusDays(4L);
        model.addAttribute("date",date);
        return "success";
    }
    /**
     * http://www.jt.com/order/myOrder.html
     *my-order-comment.jsp
     */
    //查询用户订单
    @RequestMapping("/myOrder")
    public String myOrder(){

        return "my-orders";
    }

}
