package com.boot.zysf.api.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class GonvernmentData {
    @TableId(value = "id", type= IdType.AUTO)
    private Integer id;
    //年份
    private String year;

    //组织机构代码
    private String organizationalCode;

    //单位详细名称
    @TableId(value = "emp_name")
    private String empName;

    //统一社会信用代码
    private String socialCode;

    //从业人员期末人数
    private String employeeFinalNum;

    //年末资产总计
    private String totalFinalAsset;

    //其中：流动资产合计
    private String liquidAsset;

    //其中：固定资产合计
    private String fixedAsset;

    //其中：累计折旧
    private String accumulatedDepreciation;

    //本年完成固定资产投资额
    private String fixedAssetInvest;

    //无形资产
    private String intangibleAsset;

    //非流动资产合计
    private String unLiquidAsset;

    //年末负债合计
    private String totalYearendliabilities;

    //银行贷款额
    private String bankloanAmount;

    //本年应付职工薪酬
    private String employeeSalary;

    //年末所有者权益（股东权益）
    private String shareholderSalary;

    //实收资本（股本
    private String stockValue;

    //企业上市融资股本
    private String listedFinanEquality;

    //工业总产值
    private String industryOutput;

    //营业收入
    private String operatingIncome;

    //主营业务收入
    private String mainOperatingIncome;

    //产品销售收入
    private String productSaleIncome;

    //商品销售收入
    private String commoditySaleIncome;

    //营业成本
    private String businessCost;

    //销售费用
    private String sellingExpense;

    //管理费用
    private String manageExpense;

    //财务费用
    private String  financeExpense;

    //研发、试验检验费  采集表 企业 R&D经费内部支出(千元)
    private String  internalEnterpriseFunds;

    //营业利润
    private String  saleProfit;

    //营业外收入
    private String  outBusinessIncome;

    //营业外支出
    private String  outBusinessCost;

    //利润总额
    private String  totalProfit;

    //净利润
    private String  netProfit;

    //所得税
    private String  tax;

    public GonvernmentData() {
    }
}
