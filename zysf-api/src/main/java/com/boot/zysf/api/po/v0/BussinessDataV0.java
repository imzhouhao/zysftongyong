package com.boot.zysf.api.po.v0;

import com.boot.zysf.api.po.BusinessData;
import lombok.Data;
import lombok.experimental.Accessors;
@Data
@Accessors(chain = true)
public class BussinessDataV0 {

    private Integer id;

    //企业名称
    private String empName;

    //经度
    private String lng;

    //维度
    private String lat;
//
//    //省份
//    private String sheng;
//
//    //城市
//    private String city;
//
//    //数据处理得到行业
//    private String tags;

    public BussinessDataV0(BusinessData businessData){
        this.id=businessData.getId();
        this.empName = businessData.getEmpName();
        this.lng=businessData.getLng();
        this.lat = businessData.getLat();
//        this.sheng = businessData.getSheng();
//        this.city = businessData.getCity();
//        this.tags = businessData.getTags();
    }
}
