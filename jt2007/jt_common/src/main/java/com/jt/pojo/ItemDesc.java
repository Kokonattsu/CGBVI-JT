package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("tb_item_desc")
public class ItemDesc extends BasePojo{
    @TableId(type = IdType.INPUT)      //用户输入id值
    private Long itemId;
    private String itemDesc;
}
