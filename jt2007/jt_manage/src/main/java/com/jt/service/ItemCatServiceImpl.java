package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.annotation.CacheFind;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import com.jt.vo.EasyUITree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService{

    @Autowired
    private ItemCatMapper itemCatMapper;

    @Autowired
    private Jedis jedis;


    @Override
    public String findItemName(Long itemCatId) {
        ItemCat itemCat = itemCatMapper.selectById(itemCatId);

        return itemCat.getName();
    }

    @CacheFind(proKey = "ITEMCAT_PARENTID")
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

    @Override
    public List<EasyUITree> findItemCatCache(Long parentId) {

        List<EasyUITree> treelist=new ArrayList<>();
        String key="ITEMCAT_PARENTID::"+parentId;
        if (jedis.exists(key)){
            String json = jedis.get(key);

        }else {

        }

        return null;
    }

}
