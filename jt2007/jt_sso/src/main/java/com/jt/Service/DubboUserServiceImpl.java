package com.jt.Service;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import com.jt.service.DubboUserService;
import com.jt.util.ObjectMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisCluster;

import java.util.UUID;

@Service(timeout = 3000)
public class DubboUserServiceImpl implements DubboUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JedisCluster jedisCluster;

    //用户注册
    @Override
    public Integer saveUser(User user) {
        String password = user.getPassword();
        String newPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        user.setEmail(user.getPhone()).setPassword(newPassword);
        return userMapper.insert(user);
    }

    //用户登录
    @Override
    public String doLogin(User user) {

        String md5password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5password);
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>(user);
        User dbUser = userMapper.selectOne(userQueryWrapper);
        if (dbUser==null){
            return null;
        }
        String uuid = UUID.randomUUID().toString().replace("-", "");
        dbUser.setPassword("123456");
        //缓存查询到的用户不然没有id
        jedisCluster.set(uuid, ObjectMapperUtil.toJSON(dbUser));
        return uuid;


    }
}
