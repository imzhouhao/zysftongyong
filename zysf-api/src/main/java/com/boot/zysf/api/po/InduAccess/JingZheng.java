package com.boot.zysf.api.po.InduAccess;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(chain = true)
public class JingZheng {

    //人均营业收入
    private Map<String,Double> meanIncome;

    //平均净利润率
    private Map<String,Double> netProfit;

    //平均资产收益率
    private Map<String,Double> aevergeReturn;

    public JingZheng(Map<String, Double> meanIncome, Map<String, Double> netProfit, Map<String, Double> aevergeReturn) {
        this.meanIncome = meanIncome;
        this.netProfit = netProfit;
        this.aevergeReturn = aevergeReturn;
    }
}
