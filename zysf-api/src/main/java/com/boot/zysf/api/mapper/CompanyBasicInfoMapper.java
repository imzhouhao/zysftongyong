package com.boot.zysf.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.zysf.api.po.CompanyBasicInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompanyBasicInfoMapper extends BaseMapper<CompanyBasicInfo> {
    List<CompanyBasicInfo> getEntityByName(CompanyBasicInfo info);
}
