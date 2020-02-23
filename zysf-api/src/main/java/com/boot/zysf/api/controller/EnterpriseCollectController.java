package com.boot.zysf.api.controller;

import com.boot.zysf.api.po.EnterpriseCollect;
import com.boot.zysf.api.po.Innovation;
import com.boot.zysf.api.service.EnterpriseCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/EnterpriseCollect")
public class EnterpriseCollectController {
    @Autowired
    EnterpriseCollectService enterpriseCollectService;
    @PostMapping(value = "/uploadEnterpriseCollect", headers = "content-type=multipart/form-data")
    public List<EnterpriseCollect> test (MultipartFile file){
        return enterpriseCollectService.test(file);
    }
    @PostMapping(value = "/addInto", headers = "content-type=multipart/form-data")
    public void addInto(@RequestParam(value = "file", required = true) MultipartFile file){
        List<EnterpriseCollect>enterpriseCollectList=  test(file);;
        for(int i=0;i<enterpriseCollectList.size();i++){
            if(enterpriseCollectList.get(i)!=null&&enterpriseCollectList.get(i).getEmpName()!=null) {
                enterpriseCollectService.addData(enterpriseCollectList.get(i));
            }
        }
    }
    @GetMapping(value = "listAllInvotation")
    public  List<Innovation> listAll(){
        return enterpriseCollectService.listAll();
    }
}
