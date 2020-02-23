package com.boot.zysf.api.po.Model;

import com.boot.zysf.api.po.EmpInfo;

public class EmpInfoModel extends CommonModel<EmpInfo> {
    public  EmpInfoModel(Integer code,String message,EmpInfo data){
        super(code, message, data);
    }

    public EmpInfoModel() {
    }

    public EmpInfoModel(Integer code, String message) {
        super(code, message);
    }

}
