package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import sun.awt.image.IntegerInterleavedRaster;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@TableName
public class User implements Serializable {

    private static final long serialVersionUID = -7608936516759666971L;
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer age;
    private String sex;
}
