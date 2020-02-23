package com.boot.zysf.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.boot.zysf.api.po.CompanyBasicInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 *
 * 企业基本信息
 */
public interface ICompanyBasicInfoService extends IService<CompanyBasicInfo> {
    /**
     * 读取exl的数据，生成list
     */
    /*List<CompanyBasicInfo> readExcelFile(MultipartFile file);
    void addDate(CompanyBasicInfo info);*/
}
