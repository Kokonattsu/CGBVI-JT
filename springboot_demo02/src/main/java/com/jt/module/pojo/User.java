package com.jt.module.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)    //开启链式加载 user.setId().setName();
public class User implements Serializable {
    private static final long serialVersionUID = 5661265851270667923L;
    private Integer id;
    private String name;
    private Integer age;
    private String sex;
}
