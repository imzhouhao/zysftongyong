package com.boot.zysf.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.zysf.api.mapper.CompanyDetailMapper;
import com.boot.zysf.api.po.CompanyDetail;
import com.boot.zysf.api.service.CompanyDetailService;
import org.springframework.stereotype.Service;

@Service
public class CompanyDetailServiceImpl extends ServiceImpl<CompanyDetailMapper ,CompanyDetail> implements CompanyDetailService {
}
