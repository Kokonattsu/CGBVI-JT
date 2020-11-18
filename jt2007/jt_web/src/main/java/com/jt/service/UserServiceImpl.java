package com.jt.service;

import com.jt.util.ObjectMapperUtil;
import com.jt.pojo.User;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Override
    public List<User> testHttpClient() {
        List<LinkedHashMap> linkedHashMap=new ArrayList<>();
        List<User> userList=new ArrayList<>();
        //LinkedHashMap linkedHashMap=new LinkedHashMap();
        String findURL="http://sso.jt.com/user/testhttpClient";
        String updateURL="http://sso.jt.com/user/updateUser";
        HttpClient httpClient = HttpClients.createDefault();
        try {

            HttpResponse response = httpClient.execute(new HttpGet(findURL));
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode==200){
                HttpEntity entity = response.getEntity();
                String json = EntityUtils.toString(entity, "utf-8");
                linkedHashMap = ObjectMapperUtil.toObject(json, linkedHashMap.getClass());
                for (LinkedHashMap u:linkedHashMap){
                    User user=new User()
                            .setId(Long.parseLong(u.get("id").toString()))
                            .setUsername((String) u.get("username"))
                            .setPassword((String) u.get("password"))
                            .setPhone((String) u.get("email"))
                            .setEmail((String) u.get("email"));
                    userList.add(user);
                }

                System.out.println(json);
            }
            return userList;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
