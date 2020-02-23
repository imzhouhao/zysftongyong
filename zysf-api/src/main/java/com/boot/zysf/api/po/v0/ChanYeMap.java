package com.boot.zysf.api.po.v0;

import com.boot.zysf.api.po.RenCai;
import com.boot.zysf.api.po.ZhuanLi;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ChanYeMap {

    //区县id
    private  String regionId;

    //区县名称
    private String regionName;


    //企业信息列表
    private List<ComInfo> comInfos;

    //专利列表 inventor-name title
    private List<ZhuanLi> zhuanli;

    //人才列表 name--field
    private List<RenCai> rencai;

    public ChanYeMap(String regionId, String regionName, List<ComInfo> comInfos, List<ZhuanLi> zhuanli, List<RenCai> rencai) {
        this.regionId = regionId;
        this.regionName = regionName;
        this.comInfos = comInfos;
        this.zhuanli = zhuanli;
        this.rencai = rencai;
    }
}
