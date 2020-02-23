package com.boot.zysf.api.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
@Data
@Accessors(chain = true)
public class EnterpriseFinancing {
    @TableId(value = "id", type= IdType.AUTO)
    private Integer id;

    //企业名称
    private String empName;

    //统一社会信用代码
    private String socialCode;

    //上市时间
    private Date marketDate;

    //上市板块
    private String marketModel;

    //股票代码
    private String stockCode;

    //融资时间
    private Date financDate;

    //融资轮次
    private String financTurn;

    //融资金额
    private String financMoney;

    //投资方
    private String investor;

    //企业价值
    private String companyValue;

    //是否为独角兽企业
    private String isUnicorn;

    //本投资对象
    private  String investTo;

    //出资比例
    private String investRatio;

    //出资金额
    private String investMoney;

    //出资方式
    private String investMethod;


    public EnterpriseFinancing() {
    }

    public EnterpriseFinancing(String empName, String socialCode, Date marketDate, String marketModel, String stockCode,
                               Date financDate, String financTurn, String financMoney, String investor,
                               String companyValue, String isUnicorn, String investTo, String investRatio, String investMoney, String investMethod) {
        this.empName = empName;
        this.socialCode = socialCode;
        this.marketDate = marketDate;
        this.marketModel = marketModel;
        this.stockCode = stockCode;
        this.financDate = financDate;
        this.financTurn = financTurn;
        this.financMoney = financMoney;
        this.investor = investor;
        this.companyValue = companyValue;
        this.isUnicorn = isUnicorn;
        this.investTo = investTo;
        this.investRatio = investRatio;
        this.investMoney = investMoney;
        this.investMethod = investMethod;
    }
}

