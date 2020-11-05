package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemCat;
import com.jt.vo.EasyUITable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.mapper.ItemMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMapper itemMapper;

	/**查询页面对象*/
	@Override
	public EasyUITable findItemByPage(Integer page, Integer rows) {

		Long count=0L;
		List<Item> itemList=null;
	//-----------MyBatis-plus------------
//		IPage<Item> iPage=new Page<>(page,rows);
//		QueryWrapper queryWrapper=new QueryWrapper();
//		queryWrapper.orderByDesc("updated");
//		iPage = itemMapper.selectPage(iPage, queryWrapper);
//		count=iPage.getTotal();
//		itemList=iPage.getRecords();

	//-----------原生mybatis--------------
		count= itemMapper.findCount();
		Integer start=(page-1)*rows;
		itemList= itemMapper.findItemByPage(start, rows);

		return new EasyUITable(count ,itemList);
	}

	/**添加商品*/
	@Override
	public Integer doSaveObject(Item item) {

		item.setStatus(1);
		//Integer row = itemMapper.insert(item);
		Integer row=itemMapper.insertObject();
		return row;
	}

	@Override
	public Integer doUpdateById(Item item) {
		return itemMapper.updateById(item);
	}

	@Override
	public Integer doDeleteById(Long[] ids) {
	//-------使用mybatisPlus的方式----------
//		List<Long> idList = Arrays.asList(ids);
//		Integer row=itemMapper.deleteBatchIds(idList);

		Integer row=itemMapper.doDeleteById(ids);
		return row;
	}

	@Override
	public Integer doInstockAndReshelf(Long[] ids,Integer status) {
		Integer row=0;
//		for (Long id:ids){
//			Item item=new Item().setId(id).setStatus(status);
//			row=row+itemMapper.updateById(item);
//		}
		Item item=new Item().setStatus(status);
		QueryWrapper queryWrapper=new QueryWrapper();
		queryWrapper.in("id", Arrays.asList(ids));
		row = itemMapper.update(item, queryWrapper);

		return row;
	}




}
