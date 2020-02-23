package com.boot.zysf.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.zysf.api.po.CashFlow;

public interface CashFlowMapper extends BaseMapper<CashFlow> {
    void addData (CashFlow cashFlow);
}
