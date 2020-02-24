package com.boot.zysf.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.boot.zysf.api.po.BusinessData;
import com.boot.zysf.api.service.BusinessDataService;
import com.boot.zysf.api.service.IIndustrialCategoryService;
import com.boot.zysf.api.service.IndustroyTupuService;
import com.boot.zysf.api.util.IndustryTupuRead;
//import com.boot.zysf.api.util.Tagging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/industrial-tupu")
public class IndustroyTupuController {
    @Autowired
    IndustryTupuRead industryTupuRead;
    @Autowired
    IndustroyTupuService industroyTupuService;
    @Autowired
    BusinessDataService businessDataService;
//    @Autowired
//    Tagging tagging;
    @PostMapping(value = "/addIndustroy", headers = "content-type=multipart/form-data")
    public R addInto2(@RequestParam(value = "file", required = true) MultipartFile file){
        Integer excelInfo = industryTupuRead.getExcelInfo(file);
        Map<String,Integer> map = new HashMap<>();
        map.put("新增",excelInfo);
        return R.ok(map);
    }

//    @GetMapping("/tag")
//    public void getTag(){
//        List<BusinessData> list = businessDataService.list();
//        for(int i=0;i<list.size();i++){
//            BusinessData businessData = list.get(i);
//            List<String> tag = tagging.getTags(businessData);
//            String tags = "";
//            if(tag!=null) {
//                for (int j = 0; j < tag.size(); j++) {
//                    if(tag.get(j)!=null) {
//                        tags = tags.concat(tag.get(j) + ",");
//                    }
//                }
//                businessData.setTags2(tags);
//            }
//            else
//            {
//                businessData.setTags2(tags);
//            }
//            businessDataService.updateById(businessData);
//        }
//    }
//
////    public List<String> getTag(@RequestParam(value = "name",required = true) String name){
////        BusinessData businessData = new BusinessData().setEmpName(name);
////        QueryWrapper<BusinessData> query = new QueryWrapper<>(businessData);
////        BusinessData one = businessDataService.getOne(query);
//////        String tags = "";
////        List<String> tag = tagging.getTags(businessData);
////        tag = tagging.getTags(one);
////        return tag;
////    }
}
