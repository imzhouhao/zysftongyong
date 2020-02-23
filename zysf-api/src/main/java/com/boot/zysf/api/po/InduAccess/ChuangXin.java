package com.boot.zysf.api.po.InduAccess;


import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(chain = true)
public class ChuangXin {

    //研发强度
    private Map<String,Double> qiangDu;

    //专利密度
    private  Map<String, Double> miDu;


    public ChuangXin(Map<String, Double> qiangDu, Map<String, Double> miDu) {
        this.qiangDu = qiangDu;
        this.miDu = miDu;
    }
}
