package com.boot.zysf.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.boot.zysf.api.po.CompanyDetail;
import com.boot.zysf.api.po.V0;
import com.boot.zysf.api.po.V1;
import com.boot.zysf.api.po.V2;
import com.boot.zysf.api.service.CompanyDetailService;
import com.boot.zysf.api.util.DetailRead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companyDetail")
public class CompanyDetailController {
    @Autowired
    DetailRead detailRead;
    @Autowired
    CompanyDetailService companyDetailService;
    @PostMapping(value = "/uploadCompanyDetail", headers = "content-type=multipart/form-data")
    public R upload(@RequestParam(value = "file", required = true) MultipartFile file){
        return R.ok(detailRead.getExcelInfo(file));
    }
    @PostMapping(value = "/storge", headers = "content-type=multipart/form-data")
    public void storge(@RequestParam(value = "file", required = true) MultipartFile file){
        List<CompanyDetail> excelInfo = detailRead.getExcelInfo(file);
        for(int i=0;i<excelInfo.size();i++){
            if(excelInfo.get(i)!=null){
                companyDetailService.save(excelInfo.get(i));
            }
        }
    }
    @GetMapping("/One")
    public R getOne(@RequestParam(value = "Yiji") String industory){
        QueryWrapper<CompanyDetail> query = new QueryWrapper<>();
        query.eq("industory1",industory);
        List<CompanyDetail> list = companyDetailService.list(query);
        return R.ok(list.stream().map(V0::new).collect(Collectors.toList()));
    }

    @GetMapping("/Two")
    public R getTwo(@RequestParam(value = "erji") String industory){
        QueryWrapper<CompanyDetail> query = new QueryWrapper<>();
        query.eq("industory2",industory);
        List<CompanyDetail> list = companyDetailService.list(query);
        return R.ok(list.stream().map(V2::new).collect(Collectors.toList()));
    }

    @GetMapping("/Three")
    public R getThree(@RequestParam(value = "sanji") String industory){
        QueryWrapper<CompanyDetail> query = new QueryWrapper<>();
        query.eq("industory3",industory);
        List<CompanyDetail> list = companyDetailService.list(query);
        return R.ok(list.stream().map(V2::new).collect(Collectors.toList()));
    }


}
