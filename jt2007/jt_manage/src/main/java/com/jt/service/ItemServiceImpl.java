package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.ItemDescMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUITable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.mapper.ItemMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemDescMapper itemDescMapper;

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
	@Transactional
	public Integer doSaveObject(Item item, ItemDesc itemDesc) {

		item.setStatus(1);
	//-------使用mybatisPlus的方式----------
		Integer row = itemMapper.insert(item);
		QueryWrapper queryWrapper=new QueryWrapper();
		itemDesc.setItemId(item.getId());
		itemDescMapper.insert(itemDesc);

		//-----------原生MyBatis-------------
//		Integer row=itemMapper.insertObject(item);


		return row;
	}

	/*更改商品*/
	@Override
	public Integer doUpdateById(Item item) {
		return itemMapper.updateById(item);
	}

	/*根据主键批量删除商品*/
	@Override
	public Integer doDeleteById(Long[] ids) {
	//-------使用mybatisPlus的方式----------
//		List<Long> idList = Arrays.asList(ids);
//		Integer row=itemMapper.deleteBatchIds(idList);

	//-----------原生MyBatis-------------
		Integer row=itemMapper.doDeleteById(ids);

		return row;
	}

	/*更改上架状态*/
	@Override
	public Integer doInstockAndReshelf(Long[] ids,Integer status) {
		Integer row=0;
	//-----------MyBatisPlus-------------
//		Item item=new Item().setStatus(status);
//		QueryWrapper queryWrapper=new QueryWrapper();
//		queryWrapper.in("id", Arrays.asList(ids));
//		row = itemMapper.update(item, queryWrapper);

	//-----------原生MyBatis-------------
		row = itemMapper.updateStatus(ids, status);

		return row;
	}

	/*查询商品详情*/
	@Override
	public ItemDesc doFindItemDesc(Long itemId) {
		QueryWrapper queryWrapper=new QueryWrapper();
		queryWrapper.eq("item_id", itemId);
		List<ItemDesc> itemDescList = itemDescMapper.selectList(queryWrapper);
		ItemDesc itemDesc= itemDescList.get(0);
		return itemDesc;
	}


}
