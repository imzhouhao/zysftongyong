package com.boot.zysf.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.zysf.api.po.EnterpriseFinancing;

import java.util.List;

public interface EnterpriseFinancingMapper  extends BaseMapper<EnterpriseFinancing> {
    void addData(EnterpriseFinancing enterpriseFinancing);
    List<EnterpriseFinancing> listAll();
}
