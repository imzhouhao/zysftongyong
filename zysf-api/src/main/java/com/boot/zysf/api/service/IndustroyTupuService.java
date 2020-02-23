package com.boot.zysf.api.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.boot.zysf.api.po.IndustroyTupu;

public interface IndustroyTupuService extends IService<IndustroyTupu> {
    QueryWrapper<IndustroyTupu> getEntityWrapper(IndustroyTupu industroyTupu);
}
