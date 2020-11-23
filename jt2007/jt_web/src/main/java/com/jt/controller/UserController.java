package com.jt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.pojo.User;
import com.jt.service.DubboUserService;
import com.jt.service.UserService;
import com.jt.util.ObjectMapperUtil;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Reference(check = false)//(loadbalance = "roundrobin")//轮询策略
    private DubboUserService dubboUserService;

    @Autowired
    private JedisCluster jedisCluster;

    //注册
    @RequestMapping("/doRegister")
    @ResponseBody
    public SysResult doRegister(User user){

        Integer integer = dubboUserService.saveUser(user);
        return SysResult.success(integer);
    }
    //登录
    @RequestMapping("/doLogin")
    @ResponseBody
    public SysResult doLogin(User user, HttpServletResponse response){

        String uuid=dubboUserService.doLogin(user);
        if (StringUtils.isEmpty(uuid)){
            return SysResult.fail();
        }

            Cookie cookie=new Cookie("JT_TICKET", uuid);
            cookie.setMaxAge(30*24*60*60);
            cookie.setPath("/");
            cookie.setDomain("jt.com");
            response.addCookie(cookie);
            return SysResult.success(uuid);
    }
    //登出
    @RequestMapping("/logout")
    public String doLogout(HttpServletResponse response,
                         HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies!=null && cookies.length!=0){
            for (Cookie cookie:cookies){
                if (cookie.getName().equals("JT_TICKET")){
                    jedisCluster.del(cookie.getValue());
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    cookie.setDomain("jt.com");
                    response.addCookie(cookie);

                }
            }
        }
        return "redirect:/";
    }

    //显示登录页面
    @RequestMapping("/{moduleName}")
    public String getPage(@PathVariable String moduleName){
        return moduleName;
    }

    //测试
    @RequestMapping("/testHttpClient")
    @ResponseBody
    public String testHttpClient(){
        return ObjectMapperUtil.toJSON(userService.testHttpClient());
    }



}
