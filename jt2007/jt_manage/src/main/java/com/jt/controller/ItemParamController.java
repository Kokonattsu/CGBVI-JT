package com.jt.controller;

import com.jt.service.ItemParamService;
import com.jt.vo.EasyUITable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item/param")
public class ItemParamController {


    @Autowired
    private ItemParamService itemParamService;
    /**
     * http://manage.jt.com/item/param/list?page=1&rows=30
     * get请求，
     * 参数page: 1
     * 	rows: 30
     *
     */
    @RequestMapping("/list")
    public EasyUITable findParams(Integer page,Integer rows){

        return itemParamService.findParams(page,rows);
    }
}
