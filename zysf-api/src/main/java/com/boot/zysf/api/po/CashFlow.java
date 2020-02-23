package com.boot.zysf.api.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
@TableName("01_xianjinliuliangbiao")
public class CashFlow {//现金流量表
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

    //一、经营活动产生的现金流量
    private String sellCashFlow;

    //销售商品、提供劳务收到的现金
    private String sellAndServiceCash;

    //收到的税费与返还
    private String taxReturn;

    //收到其他与经营活动有关的现金
    private String otherCash;

    //购买商品、接受劳务支付的现金
    private String purchaseCash;


    //支付给职工以及为职工支付的现金
    private String employeeCashPay;

    //支付的各项税费
    private String taxPay;

    //支付其他与经营活动有关的现金
    private String otherCashPay;

    //经营活动现金流出小计
    private String operateCashOut;

    //经营活动产生的现金流量净额
    private String operateCashNetCash;

    //二、投资活动产生的现金流量
    private String investCashFlow;

    //收回投资收到的现金
    private String receiveInvestCash;

    //取得投资收益收到的现金
    private String getInvestCash;

    //处置固定资产、无形资产和其他长期资产收回的现金净额
    private String netCash;

//    //经营活动现金流入小计
//    private Double operateCash;

    //处置子公司及其他营业单位收到的现金净额
    private String disposeSoncompanyNetCash;

    //收到其他与投资活动有关的现金
    private String otherInvestCash;

    //投资活动现金流入小计
    private String investCashIn;

    //购建固定资产、无形资产和其他长期资产支付的现金
    private String longTermPayCash;

    //投资支付的现金
    private String investPayCash;

    //取得子公司及其他营业单位支付的现金净额
    private String getSoncompanyPayNetCash;

    //支付其他与投资活动有关的现金
    private String payOtherInvestCash;

    //投资活动现金流出小计
    private String investCashOut;

    //投资活动产生的现金流量净额
    private String investNetCash;

    //三筹资活动产生的现金流量、
    private String financeCashFlow;

    //吸收投资收到的现金
    private String investCashTakeIn;

    //其中：子公司吸收少数股东投资收到的现金
    private String sonCompanyFromShareholder;

    //取得借款收到的现金
    private String cashByBorrow;

//    //收到其他与筹资活动有关的现金
//    private Double getOtherFinanceCash;

    //筹资活动现金流入小计
    private String financeCashIn;

    //偿还债务支付的现金
    private String repayDebtCash;

    //分配股利、利润或偿付利息支付的现金
    private String interestPayCash;

    //其中：子公司支付给少数股东的股利、利润
    private String sonCompanyPayToShareholder;

    //支付其他与筹资活动有关的现金
    private Double otherFinancePayCash;

    //筹资活动现金流出小计
    private Double financeCashOut;

    //筹资活动产生的现金流量净额
    private Double netCashByFinance;
    public CashFlow() {
    }
}
