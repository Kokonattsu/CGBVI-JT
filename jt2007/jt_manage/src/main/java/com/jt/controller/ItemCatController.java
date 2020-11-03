package com.jt.controller;

import com.jt.service.ItemCatService;
import com.jt.vo.EasyUITree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/item/cat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    @GetMapping("/queryItemName")
    public String doFindItemName(Long itemCatId){
        return itemCatService.findItemName(itemCatId);
    }

    @RequestMapping("/list")
    public List<EasyUITree> findItemCatList(Long id){
        id=id==null?0L:id;
        return itemCatService.findItemCatList(id);
    }
}
