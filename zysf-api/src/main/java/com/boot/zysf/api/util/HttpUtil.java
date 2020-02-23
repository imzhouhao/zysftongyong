package com.boot.zysf.api.util;

import com.boot.zysf.api.dto.Location;
import com.boot.zysf.api.dto.LocationResp;
import com.boot.zysf.api.dto.Result;
import lombok.experimental.UtilityClass;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

@UtilityClass
public class HttpUtil {
    public Location getResponse(String name){
        DefaultHttpClient httpClient = new DefaultHttpClient();
        //String url = "http://api.map.baidu.com/geocoding/v3/?address=%E5%8C%97%E4%BA%AC%E5%B8%82%E6%B5%B7%E6%B7%80%E5%8C%BA%E4%B8%8A%E5%9C%B0%E5%8D%81%E8%A1%9710%E5%8F%B7&output=json&ak=2LqELDS54hZq8lLHvqlC1WoBvaa5NFSB";
        try {
            String url = "http://api.map.baidu.com/geocoding/v3/?address="+name+"&output=json&ak=2LqELDS54hZq8lLHvqlC1WoBvaa5NFSB";
            HttpUriRequest httpReq = new HttpGet(url);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            String reponse = httpClient.execute(httpReq, responseHandler);
            LocationResp resp = JsonUtil.string2Obj(reponse, LocationResp.class);
            Result result = resp.getResult();
            return result.getLocation();
            //System.out.println(result.getLocation());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return null;
    }

}


