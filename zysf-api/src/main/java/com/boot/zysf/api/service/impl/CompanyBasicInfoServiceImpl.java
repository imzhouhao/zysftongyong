package com.boot.zysf.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.zysf.api.po.CompanyBasicInfo;
import com.boot.zysf.api.service.ICompanyBasicInfoService;
import com.boot.zysf.api.util.ReadExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
 import com.boot.zysf.api.mapper.CompanyBasicInfoMapper;
import java.util.List;

@Service
public class CompanyBasicInfoServiceImpl  extends ServiceImpl<CompanyBasicInfoMapper, CompanyBasicInfo> implements ICompanyBasicInfoService {

   /* @Autowired
    CompanyBasicInfoMapper companyBasicInfoMapper;
    @Override
    public List<CompanyBasicInfo> readExcelFile(MultipartFile file){
        ReadExcel readExcel = new ReadExcel();
        List<CompanyBasicInfo>  companyBasicInfoList = readExcel.getExcelInfo(file);
        return companyBasicInfoList;
    }
  */
}

