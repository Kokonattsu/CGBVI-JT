package com.jt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.*;
import redis.clients.jedis.util.ShardInfo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration //配置类注解
@PropertySource("classpath:/redis.properties")
public class RedisConfig {

    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private Integer port;
    @Value("${redis.nodes}")
    private String nodes;

    @Value("${redis.nodes.host1}")
    private String host1;
    @Value("${redis.nodes.host1.ports}")
    private String host1Ports;
    //redis集群
    @Bean
    public JedisCluster jedisCluster(){
        Set<HostAndPort> nodes =new HashSet<>();
        String[] ports = host1Ports.split(",");
        for (String port:ports){
            nodes.add(new HostAndPort(host1, Integer.parseInt(port)));
        }

        return new JedisCluster(nodes);
    }




    //单台redis
    @Bean
    public Jedis jedis(){
        return new Jedis(host,port);
    }
    //分片redis
    @Bean
    public ShardedJedis shardedJedis(){

        List<JedisShardInfo> shards=new ArrayList();
        String[] nodeArray = nodes.split(",");
        for (String node:nodeArray){
            String host=node.split(":")[0];
            int port=Integer.parseInt(node.split(":")[1]);
            shards.add(new JedisShardInfo(host,port));
        }
        return new ShardedJedis(shards);
    }



}
