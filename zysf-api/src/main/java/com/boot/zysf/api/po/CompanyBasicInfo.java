package com.boot.zysf.api.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 *     企业基本信息
 * </p>
 * @author zh
 * @since 2019/10/6
 */
@Data
@Accessors(chain = true)
public class CompanyBasicInfo extends Model<CompanyBasicInfo> {

    /**
     * 注解
     */
    @TableId(value="id", type= IdType.AUTO)
    private Integer id;

    /**
     * 企业名称
     */
    private String name;

    /**
     * 经营状态
     */
    private String saleState;

    /**
     * 法定代表人
     */
    private  String legalRepe;

    /**
     * 注册资本
     * 单位 万元人民币
     */
    private  String regCapital;

    /**
     * 成立日期
     */
    private Date date;

    /**
     * 省份
     */
    private String sheng;

    /**
     * 城市
     */
    private String city;

    /**
     * 电话
     */
    private String phone;

    /**
     * 更多电话
     */
    private String morePhone;

    /**
     * 邮箱
     */
    private String email;
    //统一社会信用代码
       private String socialCode;
    //
    //    //纳税人识别号
      private String taxerId;
    //
    //    //注册号
      private String registId;
    //
    //    //组织机构代码
       private String ossicationCode;
    //
    //    //参保人数
       private Integer insuranceNum;
    //
    //    //企业类型
       private String empType;
    //
    //    //行业
       private String industory;
    //
    //    //网址
       private String url;
    //
    //    //地址
        private String address;
    //
    //    //经营范围
      private String businessScope;

    //主营业务及产品  企业采集
    private String businessAndProduct;

    //实际上缴税收总额(千元) 企业采集
    private String totalTaxPay;

    //从业人员期末数 企业采集
    private String employeeFinalNum;


    public CompanyBasicInfo() {
    }


}
