package com.jt.redis;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

//@SpringBootTest
public class TestCluster {
    @Test
    void testCluster(){
        Set<HostAndPort> nodes=new HashSet<>();
        nodes.add(new HostAndPort("192.168.126.129",7000));
        nodes.add(new HostAndPort("192.168.126.129",7001));
        nodes.add(new HostAndPort("192.168.126.129",7002));
        nodes.add(new HostAndPort("192.168.126.129",7003));
        nodes.add(new HostAndPort("192.168.126.129",7004));
        nodes.add(new HostAndPort("192.168.126.129",7005));
        JedisCluster jedisCluster =new JedisCluster(nodes);
        jedisCluster.set("clusterone", "hhhhhhhhh");
        System.out.println(jedisCluster.get("clusterone"));
    }
}
