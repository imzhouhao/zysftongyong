package com.boot.zysf.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.zysf.api.mapper.GonvernmentDataMapper;
import com.boot.zysf.api.po.GonvernmentData;
import com.boot.zysf.api.service.GonvernmentDataService;
import com.boot.zysf.api.util.GovernmentDataRead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;
@Service
public class GonvernmentDataServiceImpl extends ServiceImpl<GonvernmentDataMapper, GonvernmentData> implements GonvernmentDataService{
    @Autowired
    GonvernmentDataMapper governmentDataMapper;
    @Autowired
    GovernmentDataRead governmentDataRead;
    @Override
    public List<GonvernmentData> test(MultipartFile file){
        List<GonvernmentData> gonvernmentDataList = governmentDataRead.getExcelInfo(file);
        return gonvernmentDataList;
    }
    @Override
    public void addData(GonvernmentData gonvernmentData){
        governmentDataMapper.addData(gonvernmentData);
    }
}
