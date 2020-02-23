package com.boot.zysf.api.service;

import com.boot.zysf.api.po.*;
import com.baomidou.mybatisplus.extension.service.IService;
import com.boot.zysf.api.po.v0.ChanYeTuPu;
import com.boot.zysf.api.po.v0.CompanyInfo;
import com.boot.zysf.api.po.v0.CompinfoV0;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.Cacheable;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 产业分类信息表 服务类
 * </p>
 *
 * @author zh
 * @since 2019-10-06
 */

public interface IIndustrialCategoryService extends IService<IndustrialCategory> {
   /* List<IndustrialCategory> addFile(MultipartFile file);
    void addInto(IndustrialCategory industrialCategory);*/
    @Cacheable(value = "tree")
    List<TreeNode> getTree(String id);//行业树
    @Cacheable(value = "niandata")
    List<NianData> getNianData(String id);//年度数据

    List<YearData> test(Integer id);//利润和收入

    ChanYeTuPu getChanTu(String id);//获取产业图谱产业信息

    List<TreeNode1> getTree2(String id);//产业树

 CompinfoV0 getQiYe(BusinessData businessData);//获取企业信息数组,不需要企业图谱


}
