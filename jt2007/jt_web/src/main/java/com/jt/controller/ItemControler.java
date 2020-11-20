package com.jt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.service.DubboItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ItemControler {

    @Reference(check = false)
    private DubboItemService itemService;

    @RequestMapping("/items/{itemId}")
    public String findItemById(
            @PathVariable Long itemId, Model model){
        Item item =itemService.findItemById(itemId);
        ItemDesc desc=itemService.findItemDescById(itemId);
        model.addAttribute("item", item);
        model.addAttribute("itemDesc", desc);
        return "item";
    }
}
