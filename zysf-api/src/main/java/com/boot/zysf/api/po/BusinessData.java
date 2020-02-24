package com.boot.zysf.api.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Accessors(chain = true)
@Component
public class BusinessData  extends Model<BusinessData> {
    @TableId(value = "id", type= IdType.AUTO)
    private Integer id;
    //企业名称
    private String empName;

    //经营状态
    private String saleState;

    //法定代表人
    private String legalRepe;

    //注册资本
    private  String regCapital;

    //成立日期
    private Date date;

    //省份
    private String sheng;

    //城市
    private String city;

    //电话
    private String phone;

    //更多电话
    private String morePhone;

    //邮箱
    private String email;

    //统一社会信用代码
    private String socialCode;

    //纳税人识别号
    private String taxerId;

    //注册号
    private String registId;

    //组织机构代码
    private String ossicationCode;

    //参保人数
    private Integer insuranceNum;

    //企业类型
    private String empType;

    //行业
    private String industory;

    //网址
    private String url;

    //地址
    private String address;

    //经营范围
    private String businessScope;

    //数据处理得到行业
    private String tags;


    //数据处理得到产业
    private String tags2;

    //数据处理得到的县或镇或区
    private String add3;

    //经度
    private String lng;

    //维度
    private String lat;

    public BusinessData() {
    }
    public static final String Industory = "industory";
    public static final String BusinessScope = "business_scope";
    public static final String ID = "id";
    public static final String NAME = "empName";
    public static final String CODE = "code";
    public static final String ADDRESS = "address";
    public static final String LNG = "lng";
    public static final String LAT = "lat";
    public static final String CTIME = "ctime";
    public static final String UTIME = "utime";
}
