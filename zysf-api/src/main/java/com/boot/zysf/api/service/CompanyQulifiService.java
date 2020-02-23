package com.boot.zysf.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.boot.zysf.api.po.CompanyQualification;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CompanyQulifiService  extends IService<CompanyQualification> {
    List<CompanyQualification> test(MultipartFile file);
    void addData(CompanyQualification companyQualification);
    List<CompanyQualification>listEmpQualifi();
}
