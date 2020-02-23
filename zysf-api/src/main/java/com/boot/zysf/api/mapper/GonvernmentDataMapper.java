package com.boot.zysf.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.zysf.api.po.GonvernmentData;

public interface GonvernmentDataMapper extends BaseMapper<GonvernmentData> {
    void addData(GonvernmentData gonvernmentData);
}
