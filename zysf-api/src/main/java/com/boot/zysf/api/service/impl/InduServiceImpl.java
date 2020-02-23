package com.boot.zysf.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.zysf.api.mapper.InduMapper;
import com.boot.zysf.api.po.Indu;
import com.boot.zysf.api.service.InduService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InduServiceImpl extends ServiceImpl<InduMapper, Indu> implements InduService{
    @Autowired
    InduMapper induMapper;
    @Override
    public Indu findByLabel(String label){
        return induMapper.findByLabel(label);
    }

}
