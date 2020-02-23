package com.boot.zysf.api.po.InduAccess;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(chain = true)
public class GongXian {

    private Map<String,Integer> employNum;

    private Map<String,Double> tax;

    public GongXian(Map<String, Integer> employNum, Map<String, Double> tax) {
        this.employNum = employNum;
        this.tax = tax;
    }
}
