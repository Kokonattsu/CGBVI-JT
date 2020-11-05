package com.jt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jt.pojo.Item;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ItemMapper extends BaseMapper<Item>{

    List<Item> findItemByPage(Integer start,Integer rows);

    Integer doDeleteById(Long[] ids);

    @Select("select count(*) from tb_item")
    Long findCount();

    Integer insertObject();
}
