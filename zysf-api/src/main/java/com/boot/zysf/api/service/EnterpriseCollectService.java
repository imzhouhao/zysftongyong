package com.boot.zysf.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.boot.zysf.api.po.EnterpriseCollect;
import com.boot.zysf.api.po.Innovation;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EnterpriseCollectService extends IService<EnterpriseCollect> {
    List< EnterpriseCollect> test(MultipartFile file);
    void addData( EnterpriseCollect enterpriseCollect);
    List<Innovation> listAll();
}