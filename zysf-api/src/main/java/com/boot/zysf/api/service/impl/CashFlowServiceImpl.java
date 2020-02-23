package com.boot.zysf.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.zysf.api.mapper.CashFlowMapper;
import com.boot.zysf.api.po.CashFlow;
import com.boot.zysf.api.service.CashFlowService;
import org.springframework.stereotype.Service;

@Service
public class CashFlowServiceImpl extends ServiceImpl<CashFlowMapper, CashFlow> implements CashFlowService {
}
