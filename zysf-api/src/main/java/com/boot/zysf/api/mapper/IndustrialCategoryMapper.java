package com.boot.zysf.api.mapper;

import com.boot.zysf.api.po.*;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 产业分类信息表 Mapper 接口
 * </p>
 *
 * @author zzq
 * @since 2019-07-16
 */
public interface IndustrialCategoryMapper extends BaseMapper<IndustrialCategory> {
     //List<IndustrialCategory> addFile(MultipartFile file);
     void addInto(IndustrialCategory industrialCategory);
     List<YearData> getNianData(Integer id);
//获取专利数
     Integer getZhuan(Integer id);
//获取员工数
     Integer getEmploy(Integer id);
//通过行业表获取公司数
     Integer getCompany(Integer id);

     //通过产业表获取公司数
     Integer getCompany2(Integer id);

     //获取专利信息
     List<ZhuanLi> getZhuanInfo(Integer id);

     //获取人才信息
     List<RenCai>getRenInfo(Integer id);

      //根据产业id获取公司信息
     List<BusinessData> getCompanyInfo(Integer id);

     List<TreeNode> findByParentId(@Param("parentId") Integer parentId);

}
