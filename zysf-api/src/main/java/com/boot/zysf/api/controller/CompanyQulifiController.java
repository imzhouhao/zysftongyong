package com.boot.zysf.api.controller;

import com.boot.zysf.api.po.CompanyQualification;
import com.boot.zysf.api.service.CompanyQulifiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/CompanyQulifi")
public class CompanyQulifiController {
    @Autowired
    CompanyQulifiService companyQulifiService;
    @PostMapping(value = "/uploadCompanyQulifi", headers = "content-type=multipart/form-data")
    public List<CompanyQualification> test (MultipartFile file){
        return companyQulifiService.test(file);
    }
    @PostMapping(value = "/addInto", headers = "content-type=multipart/form-data")
    public void addInto(@RequestParam(value = "file", required = true) MultipartFile file){
        List<CompanyQualification>companyQualificationList=  test(file);;
        for(int i=0;i<companyQualificationList.size();i++){
            if(companyQualificationList.get(i)!=null) {
                companyQulifiService.addData(companyQualificationList.get(i));
            }
        }
    }
    @GetMapping(value = "/listAll")
    public List<CompanyQualification> listAll(){
        return companyQulifiService.listEmpQualifi();
    }
}
