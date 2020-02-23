package com.boot.zysf.api;

import com.alibaba.fastjson.JSON;
import com.boot.zysf.api.dto.LocationResp;
import com.boot.zysf.api.dto.Result;
import com.boot.zysf.api.util.AddressUntils;
import com.boot.zysf.api.util.HttpUtil;
import com.boot.zysf.api.util.JsonUtil;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.http.HttpUtils;
import java.io.IOException;
import java.util.List;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LocationTest {
    @Autowired
    AddressUntils addressUntils;
    @Test
    public void test2(){

            DefaultHttpClient httpClient = new DefaultHttpClient();
            String url = "http://api.map.baidu.com/reverse_geocoding/v3/?ak=2LqELDS54hZq8lLHvqlC1WoBvaa5NFSB&output=json&coordtype=wgs84ll&address=天津滨海高新区滨海科技园创新大道346号";
            try {
                HttpUriRequest httpReq = new HttpGet(url);
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                String reponse = httpClient.execute(httpReq, responseHandler);
                System.out.println("+====================================");
                LocationResp resp = JsonUtil.string2Obj(reponse, LocationResp.class);
                System.out.println(reponse);
                System.out.println(resp);
                Result result = resp.getResult();
                System.out.println(result.getLocation());
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                httpClient.getConnectionManager().shutdown();
            }
        }
    @Test
    public void test() throws IOException {
        String address = "北京市海淀区学院路37号";
        List<String> location = addressUntils.getLocation(address);
        if(location.size()==0){
            System.out.println("can not find");
            return;
        }
        String lat = location.get(0);
        String lng = location.get(1);
        String add = addressUntils.getAdd(lng, lat);
        System.out.println(add);
    }
    @Test
    public  void  test3(){
        addressUntils.extract2("天津市滨海高新区滨海科技园创新大道346号");
    }

}
