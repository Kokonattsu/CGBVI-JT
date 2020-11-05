package com.jt.service;

import com.jt.pojo.Item;
import com.jt.vo.EasyUITable;

import java.util.List;

public interface ItemService {
    EasyUITable findItemByPage(Integer page,Integer rows);

    Integer doSaveObject(Item item);

    Integer doUpdateById(Item item);

    Integer doDeleteById(Long[]  ids);

    Integer doInstockAndReshelf(Long[] ids,Integer status);

}
