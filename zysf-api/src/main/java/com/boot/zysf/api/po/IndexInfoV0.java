package com.boot.zysf.api.po;

import com.boot.zysf.api.po.v0.BussinessDataV0;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/**
 * 首页地图信息
 */
@Data
@Accessors(chain = true)
public class IndexInfoV0 {
    //企业列表
    private List<BussinessDataV0> businessDataList;

    //企业数量
    private Integer CompanyNum;

    //专利数量
    private Integer zhuanliNum;

    //人才数量
    private Integer rencaiNum;

    //图谱数量
    private Integer tupuNum;

    //行业数量
    private Integer hangyeNum;

    //行业百分比
    private Map<String,Double> bili;

    public IndexInfoV0() {
    }
}
