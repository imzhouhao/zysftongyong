package com.boot.zysf.api.po.InduAccess;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(chain = true)
public class ZiBen {
    //资产负债率
    private Map<String,Double> fuZhai;

    //融资额
    private  Map<String, Double> rongZi;

    //对外投资额
    private  Map<String, Double> touZi;

    public ZiBen(Map<String, Double> fuZhai, Map<String, Double> rongZi, Map<String, Double> touZi) {
        this.fuZhai = fuZhai;
        this.rongZi = rongZi;
        this.touZi = touZi;
    }
}
