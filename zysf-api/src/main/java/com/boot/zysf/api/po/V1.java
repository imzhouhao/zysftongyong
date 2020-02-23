package com.boot.zysf.api.po;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class V1 {
    private Integer id;

    //企业名称
    private String empName;

    //经营状态
    private String state;

    //电话
    private String phone;

    //邮箱
    private String email;

    //guanwang
    private String url;

    //统一社会信用代码
    private String socialCode;

    //地址
    private String address;

    //法人代表
    private String represent;

    //成立日期
    private String date;

    //企业类型
    private String type;

    //注册资本
    private String regiMoney;

    //所属行业2
    private String industory2;

    //净利润
    private String netProfit;

    //主营业务收入
    private String income;

    //专利数
    private Integer zhuanLi;

    //就业人数
    private Integer employeeNum;

    //高新技术企业
    private String gaoXin;

    //科技型中小企业
    private String keJi;

    //上市企业代码
    private String code;

    //经营范围
    private String scope;
    public V1(CompanyDetail companyDetail){
        this.id = companyDetail.getId();
        this.address = companyDetail.getAddress() ;
        this.code = companyDetail.getCode();
        this.empName = companyDetail.getEmpName();
        this.state = companyDetail.getState();
        this.phone = companyDetail.getPhone();
        this.url = companyDetail.getUrl();
        this.socialCode = companyDetail.getSocialCode();
        this.regiMoney = companyDetail.getRegiMoney();
        this.represent = companyDetail.getRepresent();
        this.date = companyDetail.getDate();
        this.type = companyDetail.getType();
        this.industory2 =companyDetail.getIndustory2();
        this.netProfit = companyDetail.getNetProfit();
        this.income = companyDetail.getIncome();
        this.zhuanLi = companyDetail.getZhuanLi();
        this.employeeNum = companyDetail.getEmployeeNum();
        this.gaoXin = companyDetail.getGaoXin();
        this.keJi = companyDetail.getKeJi();
        this.email = companyDetail.getEmail();
        this.scope = companyDetail.getScope();
    }
}
