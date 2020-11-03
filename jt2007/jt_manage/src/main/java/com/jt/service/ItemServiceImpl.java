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

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMapper itemMapper;



	@Override
	public EasyUITable findItemByPage(Integer page, Integer rows) {
		Integer count = itemMapper.selectCount(null);
		Integer start=(page-1)*rows;
		List<Item> itemList = itemMapper.findItemByPage(start, rows);
		IPage<Item> iPage=new Page<>(page,rows);
		QueryWrapper queryWrapper=new QueryWrapper();
		queryWrapper.orderByDesc("updated");
		iPage = itemMapper.selectPage(iPage, queryWrapper);

		//return new EasyUITable(count,itemList);
		return new EasyUITable( iPage.getTotal(),iPage.getRecords());
	}


}
