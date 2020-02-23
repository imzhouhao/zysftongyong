package com.boot.zysf.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.zysf.api.po.Indu;

public interface InduMapper extends BaseMapper<Indu> {
    Indu findByLabel(String name);
    void addInto(Indu indu);
    Indu listAll();
}
