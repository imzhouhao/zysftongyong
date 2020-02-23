package com.boot.zysf.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.zysf.api.po.AssetLiability;

public interface AssetLiabilityMapper extends BaseMapper<AssetLiability> {
    void addData (AssetLiability assetliability);
}
