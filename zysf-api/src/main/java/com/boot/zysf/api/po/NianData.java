package com.boot.zysf.api.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

//产业的年度数据
@Data
@Accessors(chain = true)
//@TableName("nian_data")
public class NianData {

//    //产业id
//    private Integer id;
//
//    //产业名称
//    private String name;

    //企业数量 businessdata
    private Integer companyNum;

    //营业收入 profit
    private String inCome;

    //净利润 profit
    private String netProfit;

    //就业人数enterprisedata
    private Integer employNum;

    //授权专利 zhuanli
    private Integer zhuanNum;

    //新增注册
    private String newRegist;

    //年份  profit
    private Integer year;

    public NianData(Integer companyNum, String inCome, String netProfit, Integer employNum, Integer zhuanNum, String newRegist, Integer year) {
        this.companyNum = companyNum;
        this.inCome = inCome;
        this.netProfit = netProfit;
        this.employNum = employNum;
        this.zhuanNum = zhuanNum;
        this.newRegist = newRegist;
        this.year = year;
    }
}
