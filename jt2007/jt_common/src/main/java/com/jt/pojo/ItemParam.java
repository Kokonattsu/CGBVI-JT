package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("tb_item_param")
public class ItemParam extends BasePojo{
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long itemCatId;
    private String paramData;

}
