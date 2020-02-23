package com.boot.zysf.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boot.zysf.api.po.BusinessData;
import com.boot.zysf.api.po.IndustrialCategory;
import com.boot.zysf.api.po.Region;
import com.boot.zysf.api.service.BusinessDataService;
import com.boot.zysf.api.service.HandlerService;
import com.boot.zysf.api.service.IIndustrialCategoryService;
import com.boot.zysf.api.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class HandlerServiceImpl implements HandlerService {
    @Autowired
    BusinessDataService businessDataService;
    @Autowired
    IIndustrialCategoryService iIndustrialCategoryService;
    @Autowired
    RegionService regionService;

    @Override
    public Map<Integer, String> listMatch() {
        Map<Integer, String> map = new HashMap<Integer, String>();
        List<IndustrialCategory> industrialCategoryList = iIndustrialCategoryService.list();
        for (IndustrialCategory industrialCategory : industrialCategoryList) {
            String name = industrialCategory.getName();
            QueryWrapper<BusinessData> query = new QueryWrapper<>();
            query.select("id");
            //query.like("industory",name);
            query.like("business_scope", name).or().like("industory",name);
            List<BusinessData> list = businessDataService.list(query);
            if (list != null && "last".equals(industrialCategory.getCode())) {
                Integer id = industrialCategory.getParentId();
                IndustrialCategory industrialCategory1 = new IndustrialCategory().setId(id);
                QueryWrapper<IndustrialCategory> query1 = new QueryWrapper<>(industrialCategory1);
                IndustrialCategory one = iIndustrialCategoryService.getOne(query1);
                name = one.getName();
            }// System.out.println(list);
            for (BusinessData businessData : list) {
                Integer id = businessData.getId();
                if (map.containsKey(id)) {
                    String s = map.get(id);
                    map.put(id, s + "," + name);
                } else {
                    map.put(businessData.getId(), name);
                }
            }
        }
        //System.out.println(map);
        // System.out.println(map.size());
        return map;

    }
    @Override
    //打标签
    public void addTags ( Map<Integer, String> map){
        if(map==null){
            return;
        }
        Set<Integer> keys = map.keySet();
        for (Integer key : keys) {
            BusinessData businessData = new BusinessData().setId(key);
            QueryWrapper<BusinessData> query = new QueryWrapper<>(businessData);
            BusinessData one =businessDataService.getOne(query);
            one.setTags(map.get(key));
            businessDataService.updateById(one);
        }
    }

    @Override
    public String getadd3(String s){
        String address ="广东省惠州市仲恺高新技术开发区十九号小区";
        QueryWrapper<Region> query = new QueryWrapper<>();
        List<String> list = Arrays.asList(s.split("省|市|县|镇|区"));
        String name = list.get(0);
        query.eq("sname",name);
        Region region = regionService.getOne(query);
        for(int i =0;i<list.size();i++){
            if(region.getLevel()==3){
                return "succeed"+region.getSname();
            }
            Integer pid = region.getId();
            QueryWrapper<Region> query1 = new QueryWrapper<>();
            query1.eq("sname",name);
            query1.eq("pid",pid);
            region = regionService.getOne(query1);
        }
        return "";
    }
}
