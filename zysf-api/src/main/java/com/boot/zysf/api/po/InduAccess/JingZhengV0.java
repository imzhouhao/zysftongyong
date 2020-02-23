package com.boot.zysf.api.po.InduAccess;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class JingZhengV0 {
    private Double meanIncome;
    private Double netProfit;
    private Double aevergeReturn;

    public JingZhengV0() {
    }

    public JingZhengV0(Double meanIncome, Double netProfit, Double aevergeReturn) {

        this.meanIncome = meanIncome;
        this.netProfit = netProfit;
        this.aevergeReturn = aevergeReturn;
    }
}
