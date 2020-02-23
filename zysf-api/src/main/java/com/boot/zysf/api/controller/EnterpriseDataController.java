package com.boot.zysf.api.controller;

import com.boot.zysf.api.po.EnterpriseCollect;
import com.boot.zysf.api.po.EnterpriseData;
import com.boot.zysf.api.service.EnterpriseDataService;
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
@RequestMapping("/EnterpriseData")
public class EnterpriseDataController {
    @Autowired
    EnterpriseDataService enterpriseDataService;
    @PostMapping(value = "/uploadEnterpriseData", headers = "content-type=multipart/form-data")
    public List<EnterpriseData> test (MultipartFile file){
        return enterpriseDataService.test(file);
    }
    @PostMapping(value = "/addInto", headers = "content-type=multipart/form-data")
    public void addInto(@RequestParam(value = "file", required = true) MultipartFile file){
        List<EnterpriseData>enterpriseDataList=  test(file);;
        for(int i=0;i<enterpriseDataList.size();i++){
            if(enterpriseDataList.get(i)!=null&&enterpriseDataList.get(i).getEmpName()!=null) {
                enterpriseDataService.addData(enterpriseDataList.get(i));
            }
        }
    }
}
