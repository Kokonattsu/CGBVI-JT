package com.jt.redis;

import com.baomidou.mybatisplus.extension.api.R;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.pojo.Item;
import com.jt.service.ItemService;
import com.jt.service.ItemServiceImpl;
import com.jt.vo.EasyUITable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.JRE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Primary;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.Map;
import java.util.Set;

@SpringBootTest
public class TestRedis {
    //Jedis jedis =new Jedis("192.168.126.129",6379);

    @Autowired
    private Jedis jedis;

    @Autowired
    private ItemService itemService;

    private static final ObjectMapper MAPPER=new ObjectMapper();



    @Test
    void printTest(){
        System.out.println(jedis.get("test")+"  剩余生命"
                +jedis.ttl("test")+"  秒");
    }
    @Test
    public void testSetAndGet(){
        jedis.set("test", "第一条数据");
        printTest();

    }

    @Test
    void testExists(){
        jedis.setnx("test", "如果没有则添加的数据");
        printTest();
    }



    @Test
    void testExpire(){
        jedis.set("r", "超时会被删除的数据");
        jedis.expire("r", 100);

        System.out.println("剩余生命"+jedis.ttl("r")+"秒");
    }

    /*SetParams属性：
    // XX：如果没有则不执行。
       NX: 如果没有则执行。
       EX：添加超时时间。秒
       PX：添加超时时间。毫秒
    */
    @Test
    void testNXAndEX(){
        SetParams setParams=new SetParams();
        setParams.ex(10).nx();
        jedis.set("test", "如果没有则会储存的数据",setParams);
        printTest();
    }

    @Test
    void testXXAndPX() throws InterruptedException {
        SetParams setParams=new SetParams();
        setParams.px(100000).xx();
        jedis.set("test", "如果有则会储存的数据",setParams);
        printTest();

    }

    @Test
    void testTtl() throws InterruptedException {
       // printTest();
        SetParams setParams=new SetParams();
        //获取未超时数据的剩余时间并设置为下一条数据超时时间
        Long testLife = jedis.ttl("test");
        setParams.px(testLife*1000).xx();
        jedis.set("test", "继承超时时间的数据",setParams);
        printTest();
    }

    @Test
    void testToJSON() throws JsonProcessingException {
        EasyUITable itemByPage =
                itemService.findItemByPage(1, 1);
        String json = MAPPER.writeValueAsString(itemByPage);
        System.out.println(json);
        System.out.println("---------------------------");
        EasyUITable easyUITable = MAPPER.readValue(json, EasyUITable.class);
        System.out.println(easyUITable.toString());
    }


    //------------------Hash----------------------------
    @Test
    void testHash(){
        Object[] keys = jedis.hkeys("hash1").toArray();
        Map<String, String> hash1 = jedis.hgetAll("hash1");
        for (int i=1;i<hash1.size()+1;i++){

            String value = hash1.get((String)keys[i-1] + i);
            System.out.println("\"code"+i+"\":\""+value+"\"");
        }
    }


    //------------------List--------------------------------



}
