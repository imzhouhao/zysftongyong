package com.boot.zysf.api.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
@Data
@Accessors(chain = true)
public class EnterpriseCollect {
    @TableId(value = "id", type= IdType.AUTO)
    private Integer id;

    //年份
    private String year;

    //企业名称
    private String empName;

    //联系电话
    private String phone;

    //统一社会信用代码
    private String socialCode;

    //主营业务及产品
    private String businessAndProduct;

    //实际上缴税收总额(千元)
    private String totalTaxPay;

    //从业人员期末数
    private String employeeFinalNum;

    //期末资产总计(千元)
    private String totalFinalAsset;

    //新增固定资产(千元)
    private Double newFixedAsset;

    //年末负债合计(千元)
    private Double totalYearendliabilities;

    //银行贷款额(千元)
    private Double bankloanAmount;

    //实收资本(千元)
    private Double paidInCaptial;

    //其中，企业上市融资股本(千元)
    private  Double listedFinanEquality;

    //工业总产值(千元)
    private Double industryOutput;

    //营业收入(千元)
    private Double operatingIncome;

    //营业成本(千元)
    private Double businessCost;

    //企业 R&D经费内部支出(千元)
    private Double internalEnterpriseFunds;

    //净利润(千元)
    private Double netProfit;

    //有效发明专利数（件
    private Integer inventionPatentNum;

    //拥有注册商标（件）
    private Integer regeistTrademarkNum;

    //拥有软件著作权（件）
    private Integer softwareCopyrightNum;

    //企业当期用电量（千瓦时）
    private Double electricityCost;

    //企业当期天然气用量（立方米）
    private Double naturalGasCost ;

    //企业当期用水量（吨）
    private Double waterCost;

    public EnterpriseCollect() {
    }


}
