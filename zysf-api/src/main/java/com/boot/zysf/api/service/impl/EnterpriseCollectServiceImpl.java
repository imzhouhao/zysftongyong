package com.boot.zysf.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.zysf.api.mapper.EnterpriseCollectMapper;
import com.boot.zysf.api.po.EnterpriseCollect;
import com.boot.zysf.api.po.Innovation;
import com.boot.zysf.api.service.EnterpriseCollectService;
import com.boot.zysf.api.util.EnterpriseCollectRead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class EnterpriseCollectServiceImpl  extends ServiceImpl<EnterpriseCollectMapper, EnterpriseCollect> implements EnterpriseCollectService {
    @Autowired
    EnterpriseCollectMapper enterpriseCollectMapper;
    @Autowired
    EnterpriseCollectRead enterpriseCollectRead;
    @Override
    public List< EnterpriseCollect> test(MultipartFile file){
        List< EnterpriseCollect> enterpriseCollectList = enterpriseCollectRead.getExcelInfo(file);
        return  enterpriseCollectList;
    }
    @Override
    public void addData( EnterpriseCollect enterpriseCollect){
        enterpriseCollectMapper.addData(enterpriseCollect);
    }
    @Override
    public List<Innovation> listAll(){
        return enterpriseCollectMapper.listAll();
    }
}
