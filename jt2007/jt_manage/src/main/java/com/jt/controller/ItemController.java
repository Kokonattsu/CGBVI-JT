package com.jt.controller;

import com.jt.vo.EasyUITable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.jt.service.ItemService;

@RestController
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/query")
	public EasyUITable doFindItemByPage(Integer page,Integer rows){
		return itemService.findItemByPage(page, rows);

	}


}
