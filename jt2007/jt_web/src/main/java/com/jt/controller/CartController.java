package com.jt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.Cart;
import com.jt.service.DubboCartService;
import com.jt.util.UserThreadLocal;
import com.jt.vo.SysResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Reference(check = false)
    private DubboCartService cartService;

    //购物车页面展现
    @RequestMapping("/show")
    public String show(HttpServletRequest request,Model model){
        Long userId=UserThreadLocal.get().getId();
        List<Cart> cartList=cartService.findCartByUserId(userId);
        model.addAttribute("cartList", cartList);
        return "cart";
    }

    //购物车数量修改
    //http://www.jt.com/cart/update/num/562379/7
    @ResponseBody   //如果返回视图为空，ajax就会收不到返回结果
    @RequestMapping("/update/num/{itemId}/{num}")
    public SysResult updateNum(Cart cart){
        Long userId=UserThreadLocal.get().getId();
        cart.setUserId(userId);
        cartService.updateNum(cart);
        return SysResult.success(null);
    }

    /**
     *http://www.jt.com/cart/delete/562379.html
     *
     * @param cart
     * @return
     */
    //删除购物车
    @RequestMapping("/delete/{itemId}")
    public String doDeleteByUserId(Cart cart){
        Long userId=UserThreadLocal.get().getId();
        cart.setUserId(userId);
        cartService.doDeleteCart(cart);
        return "redirect:/cart/show";
    }

    /**
     * http://www.jt.com/cart/add/562379.html
     * num: 1
     * itemTitle: 三星 W999 黑色 电信3G手机 双卡双待双通
     * itemImage: http://image.taotao.com/jd/d2ac340e728d4c6181e763e772a9944a.jpg
     * itemPrice: 4299000
     * 重定向到cart/show
     */
    //添加购物车
    @RequestMapping("/add/{itemId}")
    public String doAdd(Cart cart){
        Long userId=UserThreadLocal.get().getId();
        cart.setUserId(userId);
        cartService.addCart(cart);
        return "redirect:/cart/show";
    }

}
