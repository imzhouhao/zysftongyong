package com.boot.zysf.api.util;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.boot.zysf.api.dto.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AddressUntils {

    public AddressUntils() {
    }

    /**
     *根据经纬度获取省市区
     * @param lng
     * @param lat
     * @return
     */
    public  String getAdd(String lng, String lat ){
        //lat 小  log  大
        //参数解释: 纬度,经度 type 001 (100代表道路，010代表POI，001代表门址，111可以同时显示前三项)
        String urlString = "http://api.map.baidu.com/reverse_geocoding/v3/?ak=2LqELDS54hZq8lLHvqlC1WoBvaa5NFSB&output=json&coordtype=wgs84ll&location="+lat+","+lng;
        String res = "";
        try {
            URL url = new URL(urlString);
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                res += line+"\n";
            }
            in.close();
            //解析结果
            JSONObject jsonObject = JSONObject.parseObject(res);
            JSONObject result = jsonObject.getJSONObject("result");
            JSONObject addressComponent = result.getJSONObject("addressComponent");
            String district = addressComponent.getString("district");
            res = district;

        } catch (Exception e) {
            return null;
        }
        return res;
    }


    /**
     * 根据地址获取经纬度
     * @param address
     * @return List<String> 0-lat  1-lng
     */
    public List<String> getLocation(String address){
        List<String> list =new ArrayList<>();
        Location response = HttpUtil.getResponse(address);
        if(response!=null){
            list.add(response.getLat().toString());
            list.add(response.getLng().toString());
        }
        return  list;
    }

    public void extract2(String s){
        String regex="([^省]+自治区|.*?省|.*?行政区|.*?市)" +
                "([^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|市中区|.*?市|.*?县|.*?区)" +
                "([^县]+县|.+区|.+市|.+旗|.+海域|.+岛)" +
                "?([^区]+区|.+镇)" +
                "?([^村]+村|.+街|.+街道)" +
                "?(.*)";
        Matcher m= Pattern.compile(regex).matcher(s);
        while(m.find()){
            int c = m.groupCount();
            for(int i =0;i < c;i++){
                String group = m.group(i);
                System.out.print(group);
                System.out.print("  ");
            }
        }
        System.out.println();
    }

}