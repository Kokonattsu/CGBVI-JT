package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
@TableName("tb_order_item")
@Data
@Accessors(chain = true)
public class OrderItem extends BasePojo {

    private String itemId;

    @TableId
    private String orderId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_order_item.num
     *
     * @mbg.generated Sat Nov 21 15:41:50 CST 2020
     */
    private Integer num;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_order_item.title
     *
     * @mbg.generated Sat Nov 21 15:41:50 CST 2020
     */
    private String title;


    private Long price;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_order_item.total_fee
     *
     * @mbg.generated Sat Nov 21 15:41:50 CST 2020
     */
    private Long totalFee;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_order_item.pic_path
     *
     * @mbg.generated Sat Nov 21 15:41:50 CST 2020
     */
    private String picPath;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_order_item.created
     *
     * @mbg.generated Sat Nov 21 15:41:50 CST 2020
     */


}
