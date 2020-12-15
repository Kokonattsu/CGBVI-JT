package com.jt.aop;

import com.jt.annotation.CacheFind;
import com.jt.util.ObjectMapperUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.ShardedJedis;

import java.util.Arrays;

@Aspect
@Component
public class CacheAOP {

    @Autowired
    //使用redis集群
    private JedisCluster jedis;
    //使用redis哨兵
    //private ShardedJedis jedis;
    //使用单台redis
    //private Jedis jedis;

    //@Around("@annotation(com.jt.annotation.CacheFind)")
    //把注解对象传入切入点表达式
    @Around("@annotation(cacheFind)")
    public Object around(ProceedingJoinPoint joinPoint, CacheFind cacheFind){
        String proKey = cacheFind.proKey();
        int seconds = cacheFind.seconds();
        String key=proKey+ Arrays.toString(joinPoint.getArgs());
        Object result=null;
        //Signature的子类才能获取返回值
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        try {
            //--------从缓存中取数据----------
            if (jedis.exists(key)){
                String jsonResult = jedis.get(key);
                //从方法签名中获取返回值的类型
                result= ObjectMapperUtil.toObject(jsonResult, signature.getReturnType());
            }else {
            //------从数据库中取数据--------
                result = joinPoint.proceed();
                String json = ObjectMapperUtil.toJSON(result);
                if (seconds>0){
                    //如果设置了超时
                    jedis.setex(key,seconds,json);
                }else {
                    jedis.set(key, json );
                }
            }

            return result;
        } catch (Throwable throwable) {
            throw new RuntimeException(throwable);
        }


    }
}
