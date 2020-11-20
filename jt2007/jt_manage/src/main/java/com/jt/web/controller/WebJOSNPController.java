package com.jt.web.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.vo.Json;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebJOSNPController {

    @RequestMapping("/web/testJSONP")
    public JSONPObject testJSONP(String callback){
        Json json=new Json("001","mycat");
        return new JSONPObject(callback,json);
    }
}
