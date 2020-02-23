package com.boot.zysf.api.controller;

import com.boot.zysf.api.po.CompanyQualification;
import com.boot.zysf.api.po.EnterpriseFinancing;
import com.boot.zysf.api.service.EnterpriseFinancingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Controller
@ResponseBody
@RequestMapping("/EnterpriseFinanc")
public class EnterpriseFinancingController {
    @Autowired
    EnterpriseFinancingService enterpriseFinancingService;
    @PostMapping(value = "/uploadEnterpriseFinanc", headers = "content-type=multipart/form-data")
    public List<EnterpriseFinancing> test (MultipartFile file){
        return enterpriseFinancingService.test(file);
    }
    @PostMapping(value = "/addInto", headers = "content-type=multipart/form-data")
    public void addInto(@RequestParam(value = "file", required = true) MultipartFile file){
        List<EnterpriseFinancing>enterpriseFinancingList=  test(file);;
        for(int i=0;i<enterpriseFinancingList.size();i++){
            if(enterpriseFinancingList.get(i)!=null) {
                enterpriseFinancingService.addData(enterpriseFinancingList.get(i));
            }
        }
    }
    @GetMapping(value = "listAll")
    public  List<EnterpriseFinancing>listAll(){
        return enterpriseFinancingService.listAll();
    }
}
