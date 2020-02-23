package com.boot.zysf.api.po.v0;

import com.boot.zysf.api.po.BusinessData;
import com.boot.zysf.api.po.EnterpriseData;
import com.boot.zysf.api.po.ZhuanAndRen;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class CompanyInfo {

    private Integer id;

    //企业名称
    private String empName;

    //企业状态
    private String saleState;

    //电话
    private String phone;

    //邮箱
    private String email;

    //官网
    private String url;

    //统一社会信用代码
    private String socialCode;

    //地址
    private String address;

    //法定代表人
    private String legalRepe;

    //成立日期
    private Date date;

    //企业类型
    private String empType;

    //注册资本
    private  String regCapital;

    //行业
    private String industory;

    //净利润 enterprisedata
    private String netProfit;

    //主营业务收入enterprisedata
    private String mainOperatingIncome;

    //专利数
    private Integer zhuanNum;

    //就业人数
    private Integer renNum;

    //高新技术企业
    private String gaoXin;

    //科技型中小企业
    private String keJi;

    //上市企业代码
    private String code;

    //经营范围
    private String scope;

    //内容
    private String content;

    //企业关系图谱数据
    private QiYeTuPu qiYeTuPu;

    public CompanyInfo(BusinessData businessData,String netProfit ,String mainOperatingIncome, QiYeTuPu qiYeTuPu,Integer zhuanNum,Integer renNum ) {
        this.id = businessData.getId();
        this.empName = businessData.getEmpName();
        this.saleState = businessData.getSaleState();
        this.phone = businessData.getPhone();
        this.email = businessData.getEmail();
        this.url = businessData.getUrl();
        this.socialCode = businessData.getSocialCode();
        this.address = businessData.getAddress();
        this.legalRepe = businessData.getLegalRepe();
        this.date = businessData.getDate();
        this.empType = businessData.getEmpType();
        this.regCapital = businessData.getRegCapital();
        this.industory = businessData.getIndustory();
        this.netProfit = netProfit;
        this.mainOperatingIncome =mainOperatingIncome;
        this.zhuanNum = zhuanNum;
        this.renNum = renNum;
        this.gaoXin = "0";
        this.keJi = "0";
        this.code = "0";
        this.scope = businessData.getBusinessScope();
        this.content = "0";
        this.qiYeTuPu = qiYeTuPu;
    }
}
