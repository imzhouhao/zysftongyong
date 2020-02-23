package com.boot.zysf.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.zysf.api.po.BusinessData;
import com.boot.zysf.api.po.InduAccess.JingZhengV0;
import com.boot.zysf.api.po.RenCai;
import com.boot.zysf.api.po.ZhuanLi;
import com.boot.zysf.api.po.v0.ComInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface BusinessDataMapper extends BaseMapper<BusinessData> {
    void addDate(BusinessData businessData);

    //获取省的企业
    List<ComInfo> getSheng(@Param("induId") Integer induId, @Param("regionId") Integer regionId);

    //获取省的专利
    List<ZhuanLi> getShengZhuan(@Param("induId") Integer induId, @Param("regionId") Integer regionId);

    //获取省的人才
    List<RenCai> getShengRen(@Param("induId") Integer induId, @Param("regionId") Integer regionId);

    //获取市的企业
    List<ComInfo> getShi(@Param("induId") Integer induId, @Param("regionId") Integer regionId);

    //获取市的专利
    List<ZhuanLi> getShiZhuan(@Param("induId") Integer induId, @Param("regionId") Integer regionId);

    //获取市的人才
    List<RenCai> getShiRen(@Param("induId") Integer induId, @Param("regionId") Integer regionId);

    //获取区的企业
    List<ComInfo> getQu(@Param("induId") Integer induId, @Param("regionId") Integer regionId);

    //获取区的专利
    List<ZhuanLi> getQuZhuan(@Param("induId") Integer induId, @Param("regionId") Integer regionId);

    //获取区的人才
    List<RenCai> getQuRen(@Param("induId") Integer induId, @Param("regionId") Integer regionId);

    //获取中国的企业
    List<ComInfo> getChina(@Param("induId") Integer induId);

    // //获取某区域某产业的公司数
    Integer getCompanyNum(@Param("induId") Integer induId, @Param("regionId") Integer regionId, @Param("level") Integer level);

    // //获取某区域某产业的时间
    List<Date> getDate(@Param("induId") Integer induId, @Param("regionId") Integer regionId, @Param("level") Integer level);

    //获取某区域的实体数
    Integer getShiTi(@Param("induId") Integer induId, @Param("regionId") Integer regionId, @Param("level") Integer level);

    //获取某区域某产业的员工数
    Integer getEmploNum(@Param("induId") Integer induId, @Param("regionId") Integer regionId, @Param("level") Integer level);

    //获取所得税贡献率
    Double getTax(@Param("induId") Integer induId, @Param("regionId") Integer regionId, @Param("level") Integer level);

    //获取人均营业收入和利润率和平均资产收益率
   JingZhengV0 getJingZheng(@Param("induId") Integer induId, @Param("regionId") Integer regionId, @Param("level") Integer level);

   //获取某区域某产业的专业数
    Integer getZhuanli(@Param("induId") Integer induId, @Param("regionId") Integer regionId, @Param("level") Integer level);

    //获取某区域某专业的员工数
    Integer getEmployNum(@Param("induId") Integer induId, @Param("regionId") Integer regionId, @Param("level") Integer level);

    //获取某区域某产业的负债率
    Double getFuZhan(@Param("induId") Integer induId, @Param("regionId") Integer regionId, @Param("level") Integer level);

    //获取某区域某产业的筹资小计
    Double getChouZi(@Param("induId") Integer induId, @Param("regionId") Integer regionId, @Param("level") Integer level);

    //获取某区域某产业的投资小计
    Double getTouZi(@Param("induId") Integer induId, @Param("regionId") Integer regionId, @Param("level") Integer level);

    //获取某区域某产业的收入同比增速
    List<Double>getIncomeSpeed(@Param("induId") Integer induId, @Param("regionId") Integer regionId, @Param("level") Integer level);

    //获取某区域某产业的利润同比增速
    List<Double>getProfitSpeed(@Param("induId") Integer induId, @Param("regionId") Integer regionId, @Param("level") Integer level);

    List<BusinessData>findLatNotNull();

}