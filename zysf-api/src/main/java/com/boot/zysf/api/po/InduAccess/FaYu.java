package com.boot.zysf.api.po.InduAccess;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(chain = true)
public class FaYu {

    //生存期 全国 -本地
    private Map<String,Double> shengCunQi;

    //实体环节数
    private  Map<String,Integer> shiTi;

    //企业个数
    private Map<String,Integer> qiYe;

    public FaYu(Map<String, Double> shengCunQi, Map<String, Integer> shiTi, Map<String, Integer> qiYe) {
        this.shengCunQi = shengCunQi;
        this.shiTi = shiTi;
        this.qiYe = qiYe;
    }
}
