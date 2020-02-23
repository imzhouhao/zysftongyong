package com.boot.zysf.api.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.boot.zysf.api.po.CompanyBasicInfo;
import com.boot.zysf.api.service.ICompanyBasicInfoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@ResponseBody
@RequestMapping("/CompanyBasicInfo")
public class CompanyBasicInfoController {
   /* @Autowired
    private ICompanyBasicInfoService iCompanyBasicInfoService;
    @ApiOperation(value = "上传视频接口",notes = "上传视频接口")
    @PostMapping(value = "/video/upload", headers = "content-type=multipart/form-data")
    public List<CompanyBasicInfo> uplode(@RequestParam(value = "file", required = true) MultipartFile file) {
        return iCompanyBasicInfoService.readExcelFile(file);
    }

    @ApiOperation(value = "传到数据库")
    @PostMapping("/intoDatebase")
    public void addData(@RequestBody  CompanyBasicInfo info){
        iCompanyBasicInfoService.addDate(info);
    }*/

}
