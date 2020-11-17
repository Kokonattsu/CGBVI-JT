package com.jt.web;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class WebJOSNPController {

    @RequestMapping("/web/testJSONP")
    public JSONPObject testJSONP(String callback){
        Json json=new Json("001","mycat");
        return new JSONPObject(callback,json);
    }
}
