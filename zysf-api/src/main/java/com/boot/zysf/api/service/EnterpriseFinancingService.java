package com.boot.zysf.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.boot.zysf.api.po.EnterpriseFinancing;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EnterpriseFinancingService extends IService<EnterpriseFinancing> {
    List<EnterpriseFinancing> test(MultipartFile file);
    void addData(EnterpriseFinancing enterpriseFinancing);
    List<EnterpriseFinancing>listAll();
}
