package com.boot.zysf.api.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boot.zysf.api.po.BusinessData;
import com.boot.zysf.api.po.Region;
import com.boot.zysf.api.service.BusinessDataService;
import com.boot.zysf.api.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class Address {
    @Autowired
    RegionService regionService;
    @Autowired
    BusinessDataService businessDataService;
    public Address() {
    }
    public List <String> getaddress(String s){
        List<String> list=new ArrayList<>();
        int index = s.indexOf("省");
        if(index != -1){
            String sheng = s.substring(0,index+1);
            s = s.replace(sheng,"");
            list.add(sheng);
        }
        else{
            if(s.indexOf("区")<s.indexOf("市")){
                String sheng = s.substring(0,s.indexOf("区")+1);
                s = s.replace(sheng,"");
                list.add(sheng);
            }
        }
        index = s.indexOf("市");
        if(index!=-1){
            String shi = s.substring(0,index+1);
            s=s.replace(shi,"");
            list.add(shi);
        }
        if(s.contains("市")){
            index = s.indexOf("市");
            if(index!=-1){
                String shi = s.substring(0,index+1);
                s=s.replace(shi,"");
                list.add(shi);
                return list;
            }
        }
        else if(s.contains("县")){
            index = s.indexOf("县");
            if(index!=-1){
                String xian = s.substring(0,index+1);
                s=s.replace(xian,"");
                list.add(xian);
                return list;
            }

        }
        else if(s.contains("镇")){
            index = s.indexOf("镇");
            if(index!=-1){
                String zhen = s.substring(0,index+1);
                s=s.replace(zhen,"");
                list.add(zhen);
                return list;
            }
        }
        else if(s.contains("区")){
            index = s.indexOf("区");
            if(index!=-1){
                String qu = s.substring(0,index+1);
                s=s.replace(qu,"");
               list.add(qu);
               return list;
            }
        }
        return list;
    }
    public String getadd3(BusinessData businessData){
        String s = businessData.getAddress();
        String shi = businessData.getCity();
        List<String> list = getaddress(s);
        QueryWrapper query = new QueryWrapper();
        Integer len = list.size();
        if(len<1){
            return  null;
        }
        String xian = list.get(len-1);
        query.eq("name",shi);
        Region region = regionService.getOne(query);
        if(region==null){
            return null;
        }
        Integer pid = region.getId();
        QueryWrapper query1 = new QueryWrapper();
        query1.eq("pid",pid);
        query1.eq("name",xian);
        Region region1 = regionService.getOne(query1);
        if(region1!=null){
            return region1.getName();
        }
        return  null;
    }
}
