package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@TableName("tb_order_shipping")
public class OrderShipping extends BasePojo{
    @TableId
    private String orderId;

    private String receiverName;


    private String receiverPhone;


    private String receiverMobile;

    private String receiverState;


    private String receiverCity;


    private String receiverDistrict;

    private String receiverAddress;

    private String receiverZip;
}
