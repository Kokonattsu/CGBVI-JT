package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import com.jt.vo.EasyUITree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService{

    @Autowired
    private ItemCatMapper itemCatMapper;

    @Override
    public String findItemName(Long itemCatId) {
        ItemCat itemCat = itemCatMapper.selectById(itemCatId);

        return itemCat.getName();
    }

    @Override
    public List<EasyUITree> findItemCatList(Long parentId) {

        List<EasyUITree> treeList=new ArrayList();

        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("parent_id", parentId);
        List<ItemCat> itemCatList = itemCatMapper.selectList(queryWrapper);

        for (ItemCat itemCat:itemCatList){
            Long id =itemCat.getId();
            String text=itemCat.getName();
            String state=itemCat.getIsParent()?"closed":"open";
            EasyUITree easyUITree=new EasyUITree(id,text,state);
            treeList.add(easyUITree);
        }

        return treeList;
    }

}
