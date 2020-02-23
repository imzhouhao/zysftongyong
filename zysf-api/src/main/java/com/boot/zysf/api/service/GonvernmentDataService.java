package com.boot.zysf.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.boot.zysf.api.po.GonvernmentData;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GonvernmentDataService extends IService<GonvernmentData> {
    List<GonvernmentData> test(MultipartFile file);
    void addData(GonvernmentData gonvernmentData);
}
