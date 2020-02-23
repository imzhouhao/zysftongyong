package com.boot.zysf.api.po;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
@TableName("01_zichanfuzhaibiao")
public class AssetLiability { //资产负债表
    @TableId(value = "id", type= IdType.AUTO)
    private Integer id;

    private Integer enumId;

    //股票类型a代表A股，h代表港股，xsb代表新三板
    private String aHXsb;

    //科目\年度
    private Date times;

    //
    private String dome;

    //公司名称
    private String name;

    //流动资产
    private String liquidAsset;

    //货币资金
    private String monetaryFund;

    //以公允价值计量且其变动计入当期损益的金融资产
    private String financialAsset;

    //应收票据及应收账款
    private String notesAndAccountReceive;

    //其中：应收票据
    private String noteReceive;

    //应收账款
    private String accountReceive;

    //预付款项
    private String advancePay;

    //存货
    private String storage;

    //持有至到期投资
    private String holdToMaturityInvest;

    //流动资产合计
    private String liquidAssetTotal;

    //非流动资产
    private String noliquidAsset;

    //交易性金融资产
    private String jiaoYiAsset;

    //可供出售金融资产
    private String saleAsset;

    //长期股权投资
    private String longtermEquaInvest;

    //投资性房地产
    private String investalEstate;

    //固定资产合计
    private String totalFixAsset;

    //其中：固定资产
    private String fixAsset;

    //在建工程合计
    private String buildingProjectTotal;

    //无形资产
    private String intangablAsset;

    //商誉
    private String goodWill;

    //一年内到期的非流动资产
    private String noLiquidAssetOneYear;

    //非流动资产合计
    private String noliquidAssetTotal;

    //资产合计
    private String assetTotal;

    //流动负债
    private String liquidLiability;

    //短期借款
    private String shorttermLoan;

    //应付票据及应付账款
    private String noteAndAccountPayable;

    //其中：应付票据
    private String notePayable;

    //应付账款
    private String accountPayable;

    //预收款项
    private String advanceReceive;

    //应付职工薪酬
    private String salaryPayable;

    //应交税费
    private String taxPayable;

    //其他应付款合计
    private String totalOtherPayable;

    //其中：应付利息
    private String interestPayable;

    //应付股利
    private String dividendPayable;

    //一年内到期的非流动负债
    private String noliquidLiabilityOneYear;

    //流动负债合计
    private String liquidLiabilityTotal;

    //非流动负债
    private String noliquidLiability;

    //长期借款
    private String longtermLoan;

    //应付债券
    private String bondPayable;

    //长期应付款合计
    private String longtermPayableTotal;

    //其中：长期应付款
    private String longtermPayable;

    //递延所得税负债
    private String deferTaxLiability;

    //非流动负债合计
    private String noliquidLiabilityTotal;

    //负债合计
    private String liabilityTotal;

    //所有者权益（或股东权益）
    private String ownerEquity;

    //实收资本（或股本）
    private String paidCaptial;

    //资本公积
    private String capitalSurplus;

    //盈余公积
    private String surplusReserve;

    //未分配利润
    private String undistributProfit;

    //归属于母公司所有者权益合计
    private String parentCompanyEquity;

    //所有者权益（或股东权益）合计
    private String shareholderEquityTotal;

    //负债和所有者权益（或股东权益）合计
    private String liabityAndEquityTotal;

    public AssetLiability() {
    }
}
