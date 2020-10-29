package com.jt.modele.pojo;

import com.jt.module.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LombokTest {

    User user=new User();

    @Test
    void testSetAndGet(){
        user.setId(1).setName("齐雷").setSex("superman");
        System.out.println(user.toString());
    }
}
