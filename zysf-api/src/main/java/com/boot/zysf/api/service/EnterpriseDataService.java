package com.boot.zysf.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.boot.zysf.api.po.EnterpriseData;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EnterpriseDataService extends IService<EnterpriseData> {
    List< EnterpriseData> test(MultipartFile file);
    void addData( EnterpriseData enterpriseData);
}
