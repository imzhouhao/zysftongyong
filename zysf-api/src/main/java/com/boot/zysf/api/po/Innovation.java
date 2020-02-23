package com.boot.zysf.api.po;

import lombok.Data;
import lombok.experimental.Accessors;
@Data
@Accessors(chain = true)
public class Innovation {
    private Integer id;
    //有效发明专利数（件
    private Integer inventionPatentNum;

    //拥有注册商标（件）
    private Integer regeistTrademarkNum;

    //拥有软件著作权（件）
    private Integer softwareCopyrightNum;

    public Innovation() {
    }
}
