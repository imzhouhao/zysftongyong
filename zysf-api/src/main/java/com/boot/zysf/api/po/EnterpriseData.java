package com.boot.zysf.api.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class EnterpriseData {
    @TableId(value = "id", type= IdType.AUTO)
    private Integer id;
    // 上市板块
    private String  marketModel;

    //股票代码
    private String stockCode;

    //股票简称
    private String stockAbbreviation;

    //公司名称
    private String empName;

    //城市
    private String city;

    //行政区
    private String region;

    //主营业务收入
    private String mainOperatingIncome;

    //主营业务收入（千元）
    private String mainOperatingIncomeThounsand;

    //净利润
    private  String netProfit;
    //净利润（千元）
    private String netProfitThousand;

    //员工人数
    private String employeeNum;

    //上市日期
    private Date marketDate;

    //成立日期
    private Date  establishDate;

    //招股书
    private String prospectus;

    //行业分类
    private String industory;

    //产品类型
    private String pruductType;

    //主营业务
    private String mainBusiness;

    //资产
    private String assetTotal;

    public EnterpriseData() {
    }
}
