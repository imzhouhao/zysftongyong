package com.boot.zysf.api.service.impl;

import com.boot.zysf.api.mapper.*;
import com.boot.zysf.api.po.*;
import com.boot.zysf.api.po.v0.ChanYeTuPu;
import com.boot.zysf.api.po.v0.CompanyInfo;
import com.boot.zysf.api.po.v0.CompinfoV0;
import com.boot.zysf.api.po.v0.QiYeTuPu;
import com.boot.zysf.api.service.IIndustrialCategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.zysf.api.util.IndustryRead;
import com.boot.zysf.api.util.TreeUtil;
import com.boot.zysf.api.util.TreeUtil1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.Cacheable;

import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 * 产业分类信息表 服务实现类
 * </p>
 *
 * @author zzq
 * @since 2019-07-16
 */
@Service
@Transactional
public class IndustrialCategoryServiceImpl extends ServiceImpl<IndustrialCategoryMapper, IndustrialCategory> implements IIndustrialCategoryService {


    @Autowired
    private IndustrialCategoryMapper industrialCategoryMapper;
    @Autowired
    private IndustryRead industryRead;
    @Autowired
    BusinessDataMapper businessDataMapper;
    @Autowired
    ProfitMapper profitMapper;

    @Autowired
    ZhuanAndRenMapper zhuanAndRenMapper;

    @Autowired
    EnterpriseDataMapper enterpriseDataMapper;

    @Autowired
    IndustroyTupuMapper industroyTupuMapper;
    /**
     *  公共查询条件
     * @param industrialCategory
     * @return
     */
    public QueryWrapper<IndustrialCategory> getEntityWrapper(IndustrialCategory industrialCategory){
        return Wrappers.query(industrialCategory);
    }
   /* @Override
    public List<IndustrialCategory> addFile(MultipartFile file){
        return  industryRead.getExcelInfo(file);
    }*/
    /*@Override
    public void addInto (IndustrialCategory industrialCategory){
        industrialCategoryMapper.addInto(industrialCategory);
    }*/

    @Override//行业树
    public  List<TreeNode> getTree(String InduId){
        List<TreeNode> treeNodes = new ArrayList<>();
        QueryWrapper<IndustrialCategory> query = new QueryWrapper<>();
        query.ne("code","last");
        List<IndustrialCategory>list = industrialCategoryMapper.selectList(query);
        for(int i=0;i<list.size();i++){
            IndustrialCategory one = list.get(i);
            String id = one.getId().toString();
            String parentId = one.getParentId().toString();
            String name = one.getName();
            TreeNode node = new TreeNode(id,parentId,name);
            treeNodes.add(node);
        }
        System.out.println("daxiao"+treeNodes.size());
        IndustrialCategory industrialCategory = industrialCategoryMapper.selectById(InduId);
        TreeNode root = new TreeNode(industrialCategory.getId().toString(),industrialCategory.getParentId().toString(),industrialCategory.getName());
        System.out.println(root);
        List<TreeNode> tree = TreeUtil.build(treeNodes, InduId);
        return tree;
    }

   @Override //年度数据
   @Cacheable("tree")
    public List<NianData> getNianData(String id){
       System.out.println("begin"+LocalDateTime.now());
        List<NianData> nianDataList = new ArrayList<>();
        //获取产业名称
       IndustrialCategory industrialCategory = industrialCategoryMapper.selectById(id);
       String induName = industrialCategory.getName();

//       Integer employNum = industrialCategory.getEmployNum();
//       Integer zhuanNum = industrialCategory.getZhuanNum();
//       Integer companyNum = industrialCategory.getCompanyNum();
       //根据产业名称统计企业数据
       QueryWrapper<BusinessData> query2  = new QueryWrapper<>();
       List<BusinessData> list = businessDataMapper.selectList(query2);
//       Integer employNum = 0;
//       Integer zhuanNum = 0;
//       Integer companyNum = 0;
       Map<Integer,Double> profitMap = new HashMap<>();
       Map<Integer,Double> incomeMap = new HashMap<>();
       for(int n = 2012;n<=2019;n++) {
           profitMap.put(n,0.0);
           incomeMap.put(n,0.0);
       }
       String newRegist = "0";
       List<YearData> test = test(Integer.parseInt(id));
       //专利数
       Integer zhuanNum = industrialCategoryMapper.getZhuan(Integer.parseInt(id));

       //员工数
       Integer employNum = industrialCategoryMapper.getEmploy(Integer.parseInt(id));
       
       //公司数
       Integer companyNum = industrialCategoryMapper.getCompany(Integer.parseInt(id));

//       for (int i = 0; i < list.size(); i++) {
//           BusinessData businessData = list.get(i);
//           String tags = businessData.getTags();
//           if (tags.indexOf(induName) != -1) {//若该企业属于该行业
//               //companyNum++;
//               String empName = businessData.getEmpName();
//               //统计每年的营业收入和净利润
//               QueryWrapper<Profit> query3 = new QueryWrapper<>();
//               query3.eq("name", empName);
//               List<Profit> profitList = profitMapper.selectList(query3);
//               if(profitList.size()>0) {
//                   for (int m = 0; m < profitList.size(); m++) {
//                       for (year=2012; year <= 2019; year++) {
//                           Profit profit = profitList.get(m);
//                           String times = profit.getTimes().toString();
//                           if (times.indexOf(year.toString()) != -1) {
//                               if (profit.getBusinessIncomeTotal() != null) {
//                                   Double aDouble = profitMap.get(year);
//                                   aDouble = aDouble + Double.parseDouble(profit.getBusinessIncomeTotal());//营业收入
//                                   profitMap.put(year,aDouble);
//                               }
//                               if (profit.getNetProfit() != null) {
//                                   Double aDouble1 = incomeMap.get(year);
//                                   aDouble1 = aDouble1 + Double.parseDouble(profit.getNetProfit());//净利润
//                                   incomeMap.put(year,aDouble1);
//                               }
//                               break;
//                           }
//                       }
//                   }
//                  }
////               //获取专利数
//               QueryWrapper<ZhuanAndRen> query4 = new QueryWrapper<>();
//               query4.eq("emp_name",empName);
//               ZhuanAndRen zhuanAndRen = zhuanAndRenMapper.selectOne(query4);
//               if(zhuanAndRen!=null) {
//                   zhuanNum = zhuanNum+zhuanAndRen.getZhuanNum();
//               }
//               //获取就业人数
//               QueryWrapper<EnterpriseData> query5 = new QueryWrapper<>();
//               query5.eq("emp_name",empName);
//               EnterpriseData enterpriseData = enterpriseDataMapper.selectOne(query5);
//               if(enterpriseData!=null) {
//                   employNum = employNum + Integer.parseInt(enterpriseData.getEmployeeNum());
//               }
//           }

//       }
       if(test!=null) {
           for (int y = 0; y < 8; y++) {
               YearData yearData = test.get(y);
               System.out.println(yearData);
               if (yearData == null) {
                   break;
               }
               System.out.println("!!!!!!!!!!!!!!!");
               System.out.println("companyNum" + companyNum);
               System.out.println("yearData.getIncome().toString()" + yearData.getIncome().toString());
               System.out.println("yearData.getProfit().toString()" + yearData.getProfit().toString());
               System.out.println("employNum" + employNum);
               System.out.println("zhuanNum" + zhuanNum);
               System.out.println("newRegist" + newRegist);
               System.out.println("yearData.getYear()" + yearData.getYear());
               NianData nianData = new NianData(companyNum, yearData.getIncome().toString(), yearData.getProfit().toString(), employNum, zhuanNum, newRegist, yearData.getYear());
               nianDataList.add(nianData);
           }
       }
       else {
           for (int y = 0; y < 8; y++) {
               Integer year = 2019;
               System.out.println("nishichisads");
               NianData nianData = new NianData(companyNum,"0", "0", employNum, zhuanNum, newRegist, year--);
               nianDataList.add(nianData);
           }
       }
       System.out.println("end"+LocalDateTime.now());
       return nianDataList;
    }

    @Override//利润和收入
    public List<YearData> test(Integer id){
        return industrialCategoryMapper.getNianData(id);
    }


    @Override//获取产业图谱产业信息
    public ChanYeTuPu getChanTu(String id){
        Map<String,String> map = new HashMap<>();
        IndustrialCategory industrialCategory = industrialCategoryMapper.selectById(id);
        String name = industrialCategory.getName();
        if(industrialCategory!=null) {
            String indus = industrialCategory.getIndu();
            if(indus!=null) {
                List<String> list = Arrays.asList(indus.split("，"));
                for(int i=0;i<list.size();i++){
                    String indu = list.get(i);
                    System.out.println(indu);
                    QueryWrapper<IndustroyTupu> query = new QueryWrapper<>();
                    query.eq("name",indu);
                    query.eq("parent_id",-1);
                    IndustroyTupu industroyTupu = industroyTupuMapper.selectOne(query);
                    String chanId = industroyTupu.getId().toString();
                    map.put(chanId,indu);
                }
            }
            else
            {
                map.put("0","0");
            }
        }
        ChanYeTuPu chanYeTuPu = new ChanYeTuPu(id,name,map);
        return chanYeTuPu;
    }

    @Override//产业树
    public  List<TreeNode1> getTree2(String InduId){
        List<TreeNode1> treeNodes = new ArrayList<>();
        QueryWrapper<IndustroyTupu> query = new QueryWrapper<>();
        List<IndustroyTupu>list = industroyTupuMapper.selectList(query);
        for(int i=0;i<list.size();i++){
            IndustroyTupu one = list.get(i);
            String id = one.getId().toString();
            String parentId = one.getParentId().toString();
            String name = one.getName();
            String relation = one.getRelation();
            TreeNode1 node = new TreeNode1(id,parentId,name,relation,null);
            treeNodes.add(node);
        }
        System.out.println("daxiao"+treeNodes.size());
        IndustroyTupu industroyTupu = industroyTupuMapper.selectById(InduId);
        TreeNode1 root = new TreeNode1(industroyTupu.getId().toString(),industroyTupu.getParentId().toString(),industroyTupu.getName(),industroyTupu.getRelation(),null);
        System.out.println(root);
        List<TreeNode1> tree = TreeUtil1.build(treeNodes, InduId);
        return tree;
    }

    @Override
    public CompinfoV0 getQiYe(BusinessData businessData) {
        String empName = businessData.getEmpName();

        //EnterpriseData
        String netProfit = "0";
        String mainOperatingIncome="0";
        QueryWrapper<EnterpriseData> query1 = new QueryWrapper<>();
        query1.eq("emp_name", empName);
        EnterpriseData enterpriseData = enterpriseDataMapper.selectOne(query1);
        if(enterpriseData!=null){
            netProfit = enterpriseData.getNetProfit();
            mainOperatingIncome = enterpriseData.getMainOperatingIncome();
        }

        //ZhuanAndRen
        Integer zhuanNum =0;
        Integer renNum = 0;
        QueryWrapper<ZhuanAndRen> query2 = new QueryWrapper<>();
        query2.eq("emp_name", empName);
        ZhuanAndRen zhuanAndRen = zhuanAndRenMapper.selectOne(query2);
        if(zhuanAndRen!=null){
            zhuanNum = zhuanAndRen.getZhuanNum();
            renNum = zhuanAndRen.getRenNum();
        }


        CompinfoV0 companyInfo =new CompinfoV0(businessData,netProfit,mainOperatingIncome,zhuanNum,renNum);
        return companyInfo;
    }

   }

