package com.boot.zysf.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.boot.zysf.api.po.BusinessData;
import com.boot.zysf.api.po.InduAccess.*;
import com.boot.zysf.api.po.v0.CompanyInfo;
import com.boot.zysf.api.po.v0.PK;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface BusinessDataService extends IService<BusinessData> {
    List<BusinessData> getBusinessData(MultipartFile file);
    void addData(BusinessData businessData);
    String getEmpId(BusinessData businessData);
    Integer getZhuanliNum(String empId);
    Integer getRencaiNum(String empId);
    void dealData(BusinessData businessData);
   // Integer getZhuanli(String empId);
    CompanyInfo getQiye(BusinessData businessData);

    //获取产业生存期
    Double getShengCun(String induId,String regionId);
    //获取实体个数
    Integer getShiTi(String induId,String regionId);

    //获取发育度
    FaYu getFaYu(String induId,String regionId);

    //平均就业人数
    Map<String,Integer> employNum(String induId, String regionId);

    //获取税收贡献率
    Map<String,Double> getTax(String induId, String regionId);

    //获取贡献度
    GongXian getGongXian(String induId,String regionId);

    //获取竞争力
    JingZheng getJingZheng(String induId, String regionId);

    //获取创新力
    ChuangXin getChuangXin(String induId, String regionId);

    //获取资本力
    ZiBen getZiBen(String induId, String regionId);

    //获取成长性
    ChengZhang getChengZhang(String induId, String regionId);

    //产业pk的z值
    Double getZ(String induId, String regionId);

    //产业的优劣缺
    PK getPK(String induId, String regionId, String regionId1);


}
