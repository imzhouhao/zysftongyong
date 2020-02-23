package com.boot.zysf.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.zysf.api.po.IndustroyTupu;

public interface IndustroyTupuMapper extends BaseMapper<IndustroyTupu> {
    void addData (IndustroyTupu industroyTupu);
}
