package com.boot.zysf.api.controller;

import com.boot.zysf.api.po.GonvernmentData;
import com.boot.zysf.api.service.GonvernmentDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/GonveronmentData")
public class GonvernmentDataController {
    @Autowired
    GonvernmentDataService gonvernmentDataService;
    @PostMapping(value = "/uploadGonData", headers = "content-type=multipart/form-data")
    public List<GonvernmentData> test (MultipartFile file){
        return gonvernmentDataService.test(file);
    }
    @PostMapping(value = "/addInto", headers = "content-type=multipart/form-data")
    public void addInto(@RequestParam(value = "file", required = true) MultipartFile file){
        List<GonvernmentData>gonvernmentDataList=  test(file);;
        for(int i=0;i<gonvernmentDataList.size();i++){
            if(gonvernmentDataList.get(i)!=null) {
                gonvernmentDataService.addData(gonvernmentDataList.get(i));
            }
        }
    }
}
