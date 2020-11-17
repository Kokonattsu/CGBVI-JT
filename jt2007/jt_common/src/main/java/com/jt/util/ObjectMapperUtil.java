package com.jt.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.vo.SysResult;

public class ObjectMapperUtil {

    private static final ObjectMapper MAPPER=new ObjectMapper();

    public static String  toJSON(Object obj){
        try {
            return MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            SysResult.fail(e);
            throw new RuntimeException(e);
        }
    }

    public static <T> T toObject(String json,Class<T> object){
        try {
            return MAPPER.readValue(json, object);
        } catch (JsonProcessingException e) {
            SysResult.fail(e);
            throw new RuntimeException(e);
        }
    }
}
