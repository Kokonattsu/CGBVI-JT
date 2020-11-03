package com.jt.controller;

import com.jt.vo.EasyUITable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jt.service.ItemService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/query")
	public EasyUITable doFindItemByPage(Integer page,Integer rows){
		return itemService.findItemByPage(page, rows);

	}

	@PostMapping("/item/cat/queryItemName")
	public String doFindItemName(Long itemCatId){
		return itemService.findItemName(itemCatId);
	}
}
