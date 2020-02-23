package com.boot.zysf.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.zysf.api.mapper.EnterpriseDataMapper;
import com.boot.zysf.api.mapper.EnterpriseFinancingMapper;
import com.boot.zysf.api.po.EnterpriseData;
import com.boot.zysf.api.service.EnterpriseDataService;
import com.boot.zysf.api.util.EnterpriseDataRead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class EnterpriseDataServiceImpl extends ServiceImpl<EnterpriseDataMapper, EnterpriseData> implements EnterpriseDataService {
    @Autowired
    EnterpriseDataMapper enterpriseDataMapper;
    @Autowired
    EnterpriseDataRead enterpriseDataRead;
    @Override
    public List<EnterpriseData> test(MultipartFile file){
        List<EnterpriseData> enterpriseDataList = enterpriseDataRead.getExcelInfo(file);
        return  enterpriseDataList;
    }
    @Override
    public void addData(EnterpriseData enterpriseData){
        enterpriseDataMapper.addData(enterpriseData);
    }
}
