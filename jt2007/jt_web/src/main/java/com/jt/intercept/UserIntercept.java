package com.jt.intercept;

import com.jt.pojo.User;
import com.jt.util.ObjectMapperUtil;
import com.jt.util.UserThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import redis.clients.jedis.JedisCluster;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserIntercept implements HandlerInterceptor {

    @Autowired
    private JedisCluster jedisCluster;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取cookie ，获取 ticket
        String ticket="";
        Cookie[] cookies = request.getCookies();
        if (cookies!=null && cookies.length>0){
            for(Cookie cookie:cookies){
                if ("JT_TICKET".equals(cookie.getName())){
                    ticket=cookie.getValue();
                    break;
                }
            }
        }
        //如果redis中存在这个ticket，则继续，并把user数据传到处理器
        if (jedisCluster.exists(ticket)){
            String UserJSON = jedisCluster.get(ticket);
            User user = ObjectMapperUtil.toObject(UserJSON, User.class);
            //request.setAttribute("user",user);
            UserThreadLocal.set(user);
            return true;
        }

        //如果cookie和redis中都获取不到ticket,则拦截并重定向到登录界面
        response.sendRedirect("/user/login");
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //删除存入线程的数据
        //request.removeAttribute("user");
        UserThreadLocal.remove();
    }
}
