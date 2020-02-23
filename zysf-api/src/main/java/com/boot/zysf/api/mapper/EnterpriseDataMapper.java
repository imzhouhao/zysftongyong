package com.boot.zysf.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.zysf.api.po.EnterpriseData;

public interface EnterpriseDataMapper extends BaseMapper<EnterpriseData> {
    void addData(EnterpriseData enterpriseData);
}
