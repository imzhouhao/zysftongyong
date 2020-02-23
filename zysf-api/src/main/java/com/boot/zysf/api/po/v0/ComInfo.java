package com.boot.zysf.api.po.v0;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ComInfo {

    //企业id
    private String empId;

    //企业名称
    private String empName;

    //lat
    private String lat;

    //lng
    private String lng;

    public ComInfo(String empId, String empName, String lat, String lng) {
        this.empId = empId;
        this.empName = empName;
        this.lat = lat;
        this.lng = lng;
    }
}
