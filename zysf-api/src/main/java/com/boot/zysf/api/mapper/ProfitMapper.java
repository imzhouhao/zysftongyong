package com.boot.zysf.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.zysf.api.po.Profit;

public interface ProfitMapper extends BaseMapper<Profit> {
    void addData (Profit profit);
}
