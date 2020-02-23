package com.boot.zysf.api.mapper;

import com.boot.zysf.api.po.Company;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 企业信息 Mapper 接口
 * </p>
 *
 * @author zzq
 * @since 2019-07-10
 */
@Mapper
public interface CompanyMapper extends BaseMapper<Company> {
}
