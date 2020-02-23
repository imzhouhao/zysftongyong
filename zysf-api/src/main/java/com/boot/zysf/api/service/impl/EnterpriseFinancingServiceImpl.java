package com.boot.zysf.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.zysf.api.mapper.EnterpriseFinancingMapper;
import com.boot.zysf.api.po.EnterpriseFinancing;
import com.boot.zysf.api.service.EnterpriseFinancingService;
import com.boot.zysf.api.util.EnterpriseFinancingRead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class EnterpriseFinancingServiceImpl extends ServiceImpl<EnterpriseFinancingMapper, EnterpriseFinancing> implements EnterpriseFinancingService {
    @Autowired
    EnterpriseFinancingMapper enterpriseFinancingMapper;
    @Autowired
    EnterpriseFinancingRead enterpriseFinancingRead;
    @Override
    public List<EnterpriseFinancing> test(MultipartFile file){
        List<EnterpriseFinancing> enterpriseFinancingList = enterpriseFinancingRead.getExcelInfo(file);
        return  enterpriseFinancingList;
    }
    @Override
    public void addData(EnterpriseFinancing companyQualification){
        enterpriseFinancingMapper.addData(companyQualification);
    }
    @Override
    public  List<EnterpriseFinancing>listAll(){
       return enterpriseFinancingMapper.listAll();
    }
}
