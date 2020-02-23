package com.boot.zysf.api.service.impl;

import com.boot.zysf.api.mapper.CompanyMapper;
import com.boot.zysf.api.po.Company;
import com.boot.zysf.api.service.ICompanyService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 企业信息 服务实现类
 * </p>
 *
 * @author zzq
 * @since 2019-07-10
 */
@Service
@Transactional
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements ICompanyService {


    @Autowired
    private CompanyMapper companyMapper;

    /**
     *  公共查询条件
     * @param company
     * @return
     */
    public QueryWrapper<Company> getEntityWrapper(Company company){
        return Wrappers.query(company);
    }
}
