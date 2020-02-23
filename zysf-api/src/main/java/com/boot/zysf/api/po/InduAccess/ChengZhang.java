package com.boot.zysf.api.po.InduAccess;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(chain = true)
public class ChengZhang {

    //研发强度
    private Map<String,Double> incomeSpeed;

    //专利密度
    private  Map<String, Double> profitSpeed;

    public ChengZhang(Map<String, Double> incomeSpeed, Map<String, Double> profitSpeed) {
        this.incomeSpeed = incomeSpeed;
        this.profitSpeed = profitSpeed;
    }
}
