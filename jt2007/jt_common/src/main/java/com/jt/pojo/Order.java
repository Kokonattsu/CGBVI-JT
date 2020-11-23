package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
@TableName("tb_order")
public class Order extends BasePojo{
    @TableId//(type = IdType.AUTO)  需要有意义的主键所有不添加自增主键
    private String orderId;
    private String payment;
    //支付类型：1.在线支付，2.货到付款
    private Integer paymentType;
    private String postFee;
    //订单状态：1.未付款，2已付款，3未发货，4已发货，5.交易成功，6.交易关闭
    private Integer status;
    //付款时间
    private Date paymentTime;
    //发货时间
    private Date consignTime;
    //物流名称
    private String shippingName;
    //物流单号
    private String shippingCode;
    private Long userId;
    //买家留言
    private String buyerMessage;
    //买家昵称
    private String buyerNick;
    //买家是否评价
    private Integer buyerRate;

    //用来映射与此表关联的表的对象
    @TableField(exist = false)
    private List<OrderItem> orderItems;

    @TableField(exist = false)
    private OrderShipping orderShipping;
}
