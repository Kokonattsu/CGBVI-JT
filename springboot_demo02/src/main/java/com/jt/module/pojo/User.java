package com.jt.module.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)    //开启链式加载 user.setId().setName();
@TableName("user")  //"user"如果与类名一致可以省略
public class User implements Serializable {
    private static final long serialVersionUID = 5661265851270667923L;
    /**代表主键,IdType.AUTO表示自增字段*/
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer age;
    private String sex;

    public User(Integer id) {
        this.id = id;
    }
}
