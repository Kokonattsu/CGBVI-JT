package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jt.mapper.ItemParamMapper;
import com.jt.pojo.ItemParam;
import com.jt.vo.EasyUITable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemParamServiceImpl implements ItemParamService{
    @Autowired
    private ItemParamMapper itemParamMapper;

    //查找并分页
    @Override
    public EasyUITable findParams(Integer page, Integer rows) {

        QueryWrapper<ItemParam> queryWrapper=new QueryWrapper<>();

        Page<ItemParam> IPpage=new Page<>(page,rows);

        Page<ItemParam> selectPage = itemParamMapper.selectPage(IPpage, queryWrapper);
        long total = selectPage.getTotal();
        List<ItemParam> paramList = selectPage.getRecords();

        return new EasyUITable(total,paramList);
    }
}
