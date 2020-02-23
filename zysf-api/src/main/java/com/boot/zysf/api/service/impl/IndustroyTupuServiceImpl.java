package com.boot.zysf.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.zysf.api.mapper.IndustrialCategoryMapper;
import com.boot.zysf.api.mapper.IndustroyTupuMapper;
import com.boot.zysf.api.po.IndustroyTupu;
import com.boot.zysf.api.service.IIndustrialCategoryService;
import com.boot.zysf.api.service.IndustroyTupuService;
import com.boot.zysf.api.util.IndustryTupuRead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndustroyTupuServiceImpl extends ServiceImpl<IndustroyTupuMapper, IndustroyTupu> implements IndustroyTupuService {
    @Autowired
    private IndustroyTupuMapper industroyTupuMapper;
    @Autowired
    private IndustryTupuRead industryTupuRead;

    /**
     *  公共查询条件
     * @param
     * @return
     */
    @Override
    public QueryWrapper<IndustroyTupu> getEntityWrapper(IndustroyTupu industroyTupu){
        return Wrappers.query(industroyTupu);
    }
}
