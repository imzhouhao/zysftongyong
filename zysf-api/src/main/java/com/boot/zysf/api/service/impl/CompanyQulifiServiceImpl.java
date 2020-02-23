package com.boot.zysf.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.zysf.api.mapper.CompanyQualifiMapper;
import com.boot.zysf.api.po.CompanyQualification;
import com.boot.zysf.api.service.CompanyQulifiService;
import com.boot.zysf.api.util.CompanyQualifiRead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class CompanyQulifiServiceImpl extends ServiceImpl<CompanyQualifiMapper, CompanyQualification> implements CompanyQulifiService {
    @Autowired
    CompanyQualifiRead companyQualifiRead;
    @Autowired
    CompanyQualifiMapper companyQualifiMapper;
    @Override
    public List<CompanyQualification> test(MultipartFile file){
        List<CompanyQualification> companyQualificationList = companyQualifiRead.getExcelInfo(file);
        return  companyQualificationList;
    }
    @Override
    public void addData(CompanyQualification companyQualification){
        companyQualifiMapper.addData(companyQualification);
    }
    @Override
    public List<CompanyQualification>listEmpQualifi(){
       return companyQualifiMapper.listEmpQualifi();
    }
}
