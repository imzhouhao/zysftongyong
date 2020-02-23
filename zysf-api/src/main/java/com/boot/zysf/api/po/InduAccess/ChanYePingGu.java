package com.boot.zysf.api.po.InduAccess;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ChanYePingGu {
    private FaYu faYu;
    private GongXian gongXian;
    private JingZheng jingZheng;
    private ChuangXin chuangXin;
    private ZiBen ziBen;
    private ChengZhang chengZhang;

    public ChanYePingGu(FaYu faYu, GongXian gongXian, JingZheng jingZheng, ChuangXin chuangXin, ZiBen ziBen, ChengZhang chengZhang) {
        this.faYu = faYu;
        this.gongXian = gongXian;
        this.jingZheng = jingZheng;
        this.chuangXin = chuangXin;
        this.ziBen = ziBen;
        this.chengZhang = chengZhang;
    }
}
