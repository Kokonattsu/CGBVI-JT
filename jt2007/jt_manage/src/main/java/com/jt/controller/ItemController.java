package com.jt.controller;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUITable;
import com.jt.vo.SysResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jt.service.ItemService;

@Slf4j
@RestController
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/query")
	public EasyUITable doFindItemByPage(Integer page,Integer rows){
		return itemService.findItemByPage(page, rows);
	}

	@RequestMapping("/item/save")
	public SysResult doSaveObject(Item item, ItemDesc itemDesc) {

		Integer row = itemService.doSaveObject(item,itemDesc);
		return SysResult.success("成功添加" + row + "件商品", null);
	}

	@RequestMapping("/item/update")
	public SysResult doUpdateById(Item item) {

		Integer row = itemService.doUpdateById(item);
		return SysResult.success("成功添加" + row + "件商品", null);
	}

	@RequestMapping("/item/delete")
	public SysResult doDeleteById(Long[] ids){
		Integer row = itemService.doDeleteById(ids);
		return SysResult.success("成功删除" + row + "件商品", null);
	}

	@RequestMapping("/item/UpdateStatus/{status}")
	public SysResult doInstock(@PathVariable Integer status,Long... ids){
		Integer row = itemService.doInstockAndReshelf(ids,status);
		SysResult sysResult=new SysResult().setStatus(200);
		if (status==1){
			sysResult.setMsg("成功下架" + row + "件商品");
		}
		if (status==2){
			sysResult.setMsg("成功上架" + row + "件商品");
		}
		return sysResult;
	}

	@RequestMapping("/item/query/item/desc/{itemId}")
	public SysResult doFindItemDesc(@PathVariable Long itemId){
		ItemDesc itemDesc=itemService.doFindItemDesc(itemId);
		return SysResult.success(itemDesc);
	}


}
