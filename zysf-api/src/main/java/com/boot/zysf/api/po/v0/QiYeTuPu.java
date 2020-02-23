package com.boot.zysf.api.po.v0;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class QiYeTuPu {
    //当前产业id
    private Integer curInduId;

    //当前产业名称
    private String curInduName;

    //上游产业id
    private Integer upInduId;

    //上游产业名称
    private String upInduName;

    //下游产业id
    private Integer lowerInduId;

    //下游产业名称
    private String lowerInduName;

    public QiYeTuPu() {
    }
}
