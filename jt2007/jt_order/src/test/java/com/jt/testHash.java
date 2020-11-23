package com.jt;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.SmartDataSource;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

//@SpringBootTest
public class testHash {
    @Test
    void testHash(){

        System.out.println(new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()));
    }
}
