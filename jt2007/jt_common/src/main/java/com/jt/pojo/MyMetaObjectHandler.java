package com.jt.pojo;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Date date=new Date();
        strictInsertFill(metaObject,"created",Date.class,date);
       // this.setFieldValByName("created",date,metaObject);
        this.setFieldValByName("updated", date, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Date date=new Date();
        this.setFieldValByName("updated", date, metaObject);
    }
}
