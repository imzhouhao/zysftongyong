package com.boot.zysf.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.zysf.api.po.EnterpriseCollect;
import com.boot.zysf.api.po.Innovation;

import java.util.List;

public interface EnterpriseCollectMapper extends BaseMapper<EnterpriseCollect> {
    void addData(EnterpriseCollect enterpriseCollect);
    List<Innovation> listAll();
}
