package com.boot.zysf.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.zysf.api.po.CompanyQualification;

import java.util.List;

public interface CompanyQualifiMapper extends BaseMapper<CompanyQualification> {
    void addData(CompanyQualification companyQualification);
    List<CompanyQualification>listEmpQualifi();
}
