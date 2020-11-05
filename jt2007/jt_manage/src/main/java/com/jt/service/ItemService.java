package com.jt.service;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUITable;

public interface ItemService {
    EasyUITable findItemByPage(Integer page,Integer rows);

    Integer doSaveObject(Item item, ItemDesc itemDesc);

    Integer doUpdateById(Item item);

    Integer doDeleteById(Long[]  ids);

    Integer doInstockAndReshelf(Long[] ids,Integer status);

    ItemDesc doFindItemDesc(Long itemId);
}
