package com.jt.httpClient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

//@SpringBootTest
public class TestHttpclient {

    @Test
    void tesGet() throws IOException {
        String nu="YT5038614965476";
        String findComUrl="https://route.showapi.com/64-21?nu="+nu+"&showapi_appid=431740.0&showapi_timestamp=20201117024252&showapi_sign=627ebc5efdd730b6ed2dee8cd4f4b855";
        String findWaybillUrl="https://route.showapi.com/64-19?com=yuantong&nu="+nu+"&phone=&showapi_appid=431740.0&showapi_timestamp=20201117025825&showapi_sign=0673b484a29500d9614302f20bb5ef9a";
        String city="合肥";
        String findtqURL="https://route.showapi.com/9-2?area="+city+"&areaCode=&areaid=&need3HourForcast=0&needAlarm=0&needHourData=&needIndex=0&needMoreDay=0&showapi_appid=431740.0&showapi_timestamp=20201118100425&showapi_sign=088dec4bdea4cba9c3ed2263dc0a52af";
        String findHF="http://www.weather.com.cn/data/sk/101220101.html";
        HttpClient httpClient= HttpClients.createDefault();

        HttpGet httpGet=new HttpGet(findHF);

        HttpResponse GetResponse=httpClient.execute(httpGet);
        //获取状态链.获取状态码
        int statusCode = GetResponse.getStatusLine().getStatusCode();
        if (statusCode==200){
            //获取响应体
            HttpEntity entity = GetResponse.getEntity();

            String enstring = EntityUtils.toString(entity, "UTF-8");
            System.out.println(enstring);

        }
    }
}
