package com.boot.zysf.api.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 *     企业详细信息
 * </p>
 * @author zh
 * @since 2019/10/28
 */
@Data
@Accessors(chain = true)
public class CompanyDetail {
    @TableId(value="id", type= IdType.AUTO)
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

    //所属行业1
    private String industory1;

    //所属行业2
    private String industory2;

    //所属行业3
    private String industory3;

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

    public CompanyDetail() {
    }

}
