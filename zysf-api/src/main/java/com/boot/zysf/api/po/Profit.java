package com.boot.zysf.api.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
@TableName("01_lirunbiao")
public class Profit {//利润表
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

    //营业总收入
    private String businessIncomeTotal;

    //其中：营业收入
    private String businessIncome;

    //二、营业总成本
    private String businessCostTotal;

    //其中：营业成本
    private String businessCost;

    //营业税金及附加
    private String taxAndAddendum;

    //销售成本
    private String chengBen;

    //销售费用
    private String sellingExpense;

    //管理费用
    private String manageCost;

    //研发费用
    private String rdExpense;

    //财务费用
    private String financialExpense;

//    //其中：利息费用
//    private Double interestExpense;

    //利息收入
    private String interestIncome;

//    //资产减值损失
//    private Double assetLoss;
//
//    //信用减值损失
//    private Double creditLoss;
//
//    //加：公允价值变动收益
//    private Double fairValueChangeIncome;

    //投资收益
    private String investIncome;

//    //其中：联营企业和合营企业的投资收益
//    private Double jointVentureIncome;

    //资产处置收益
    private String assetDisposalIncome;

    //其他收益
    private  String otherProfit;

    //三、营业利润
    private String operateProfit;

    //加：营业外收入
    private String outBusinessIncome;

    //其中：非流动资产处置利得
    private String noliquidAssetDisposal;

    //减：营业外支出
    private String outBusinessExpense;

    //其中：非流动资产处置损失
    private String noliquidAssetDisposalLoss;

    //四、利润总额
    private String profitTotal;

    //减：所得税费用
    private String incomeTaxExpense;

    //五、净利润
    private String netProfit;

    //（一）持续经营净利润
    private String continuingNetProfit;

//    //归属于母公司所有者的净利润
//    private Double parentCompanyNetProfit;
//
//    //少数股东损益
//    private Double someShareholderLoss;
//
//    //扣除非经常性损益后的净利润
//    private Double deductNocontinueNetProfit;

    //六每股收益、
    private String incomePerStock;

    //（一）基本每股收益
    private String basicIncomePerStock;

    //（二）稀释每股收益
    private String dilutionIncomePerStock;

//    //七、其他综合收益
//    private Double otherIncome;
//
//    //归属母公司所有者的其他综合收益
//    private Double parentCompanyOtherIncome;

    //八、综合收益总额
    private String comperhensiveIncomeTotal;

//    //归属于母公司股东的综合收益总额
//    private Double parentCompanyComperhensiveIncomeTotal;
//
//    //归属母公司所有者的其他综合收益
//    private Double parentCompanyOwnerOtherIncome;
    //其中：利息费用
    private  String profitCost;


}
