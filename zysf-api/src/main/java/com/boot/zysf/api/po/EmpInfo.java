package com.boot.zysf.api.po;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 通过企业名字获企业id类
 */
@Data
@Accessors(chain = true)
public class EmpInfo {
    private Integer id;
    private  String avatarUrl;
    private String name;
    private String ownName;
    private Integer fieldId;
    private double innovationIndex;
    private Object rank;
    private Object increase;
    private Object increasePercentage;
    private Object fieldName;
    private Object level;
    private Object verify;
    private Object me;

    public EmpInfo() {
    }
}
