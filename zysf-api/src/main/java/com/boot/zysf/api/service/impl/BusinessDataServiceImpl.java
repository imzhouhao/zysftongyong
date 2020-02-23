package com.boot.zysf.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.zysf.api.mapper.*;
import com.boot.zysf.api.po.*;
import com.boot.zysf.api.po.InduAccess.*;
import com.boot.zysf.api.po.Model.CommonModel;
import com.boot.zysf.api.po.v0.CompanyInfo;
import com.boot.zysf.api.po.v0.QiYeTuPu;
import com.boot.zysf.api.service.BusinessDataService;
import com.boot.zysf.api.util.BusinessDateRead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class BusinessDataServiceImpl extends ServiceImpl<BusinessDataMapper, BusinessData> implements BusinessDataService {
    @Autowired
    private BusinessDateRead businessDateRead;
    @Autowired
    private BusinessDataMapper businessDataMapper;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    ZhuanAndRenMapper zhuanAndRenMapper;
    @Autowired
    ZhuanLiMapper zhuanLiMapper;

    @Autowired
    RenCaiMapper renCaiMapper;

    @Autowired
    EnterpriseDataMapper enterpriseDataMapper;

    @Autowired
    IndustroyTupuMapper industroyTupuMapper;

    @Autowired
    RegionMapper regionMapper;
    @Override
    public List<BusinessData> getBusinessData(MultipartFile file){
        List<BusinessData> businessDataList = businessDateRead.getExcelInfo(file);
        return businessDataList;
    }
    @Override
    public void addData(BusinessData businessData){
        businessDataMapper.addDate(businessData);
    }
    
    @Override
    public String getEmpId(BusinessData businessData){
        String empName = businessData.getEmpName();
        //获取企业id的接口
        String url1 = "http://47.92.240.36/academic/api/v1/s?containsVerify=true&content="+empName+"&num=10&page=0&type=4";
        CommonModel forObject1 = restTemplate.getForObject(url1, CommonModel.class);
        Object data = forObject1.getData();
        List list = (List) data;
        if(list!=null&&list.size()>0) {
            Map map = (Map) list.get(0);
            String id =  map.get("id").toString();
            return id;
        }
        return null;
    }

    @Override
    public Integer getZhuanliNum(String empId){
        //获取专利个数的接口
        String url = "http://47.92.240.36/academic/api/v1/organizations/"+empId+"/patents?num=10&page=0";
        CommonModel forObject1 = restTemplate.getForObject(url, CommonModel.class);
        Object data = forObject1.getData();
        Map data1 = (Map) data;
        List content = (List) data1.get("content");
        Integer num = content.size();
        return num;
    }

    @Override
    public Integer getRencaiNum(String empId){
        //获取人才个数 的接口
        String url1 = "http://47.92.240.36/academic/api/v1/rank/top-talents-by-orgId?orgId="+empId+"&page=0";
        CommonModel forObject1 = restTemplate.getForObject(url1, CommonModel.class);
        Object data = forObject1.getData();
        List list = (List) data;
        Integer num = list.size();
        return num;
    }

    @Override
    public  void dealData(BusinessData businessData){
        ZhuanAndRen zhuanAndRen =new ZhuanAndRen();
        //获取企业id
        String empId = getEmpId(businessData);
        System.out.println(empId);
        zhuanAndRen.setEmpId(empId);
        zhuanAndRen.setEmpName(businessData.getEmpName());
        if(empId!=null) {
            //获取专利个数和专利
            String url = "http://47.92.240.36/academic/api/v1/organizations/" + empId + "/patents?num=10&page=0";
            CommonModel forObject1 = restTemplate.getForObject(url, CommonModel.class);
            Object data = forObject1.getData();
            Map data1 = (Map) data;
            List content = (List) data1.get("content");
            Integer zhuanNum = content.size();
            zhuanAndRen.setZhuanNum(zhuanNum);
            for (int i = 0; i < content.size(); i++) {
                ZhuanLi zhuanLi = new ZhuanLi();
                Map map = (Map) content.get(i);
                String inventorName = map.get("inventorName").toString();
                String title = map.get("title").toString();
                zhuanLi.setEmpId(empId);
                zhuanLi.setEmpName(businessData.getEmpName());
                zhuanLi.setInventor(inventorName);
                zhuanLi.setTitle(title);
                zhuanLiMapper.insert(zhuanLi);
                System.out.println("专利添加成功" + businessData.getEmpName());
            }

            //获取人才个数和人才
            String url1 = "http://47.92.240.36/academic/api/v1/rank/top-talents-by-orgId?orgId=" + empId + "&page=0";
            CommonModel forObject2 = restTemplate.getForObject(url1, CommonModel.class);
            Object data2 = forObject2.getData();
            List list = (List) data2;
            Integer num = list.size();
            zhuanAndRen.setRenNum(num);
            zhuanAndRenMapper.insert(zhuanAndRen);
            System.out.println("专利和人才添加成功" + businessData.getEmpName());
            for (int i = 0; i < list.size(); i++) {
                RenCai renCai = new RenCai();
                Map map1 = (Map) list.get(i);
                String name = map1.get("name").toString();
                String field = map1.get("ownName").toString();
                renCai.setEmpId(empId);
                renCai.setEmpName(businessData.getEmpName());
                renCai.setField(field);
                renCai.setName(name);
                renCaiMapper.insert(renCai);
                System.out.println("人才添加成功" + businessData.getEmpName());
            }
        }

    }

    @Override
    public CompanyInfo getQiye(BusinessData businessData) {
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
        //QiYeTuPu
        QiYeTuPu qiYeTuPu = new QiYeTuPu();
        String tags2 = businessData.getTags2();
        List<String> list = Arrays.asList(tags2.split(","));
        if (list != null) {
            Integer len = list.size();
            if (len > 1) {
            String curInduName = list.get(1);
            QueryWrapper<IndustroyTupu> query3 = new QueryWrapper<>();
            query3.eq("name", curInduName);
                List<IndustroyTupu> industroyTupuList = industroyTupuMapper.selectList(query3);
                Integer curInduId = industroyTupuList.get(0).getId();
            qiYeTuPu.setCurInduId(curInduId);
            qiYeTuPu.setCurInduName(curInduName);

            String upInduName = list.get(0);
            QueryWrapper<IndustroyTupu> query4 = new QueryWrapper<>();
            query4.eq("name", upInduName);
                List<IndustroyTupu> industroyTupuList2 = industroyTupuMapper.selectList(query4);
            Integer upInduId = industroyTupuList2.get(0).getId();
            qiYeTuPu.setUpInduId(upInduId);
            qiYeTuPu.setUpInduName(upInduName);

            //
            if (len > 2) {
                String lowerInduName = list.get(2);
                QueryWrapper<IndustroyTupu> query5 = new QueryWrapper<>();
                query5.eq("name", lowerInduName);
                List<IndustroyTupu> industroyTupuList3 = industroyTupuMapper.selectList(query5);
                Integer lowerInduId = industroyTupuList3.get(0).getId();
                qiYeTuPu.setLowerInduName(lowerInduName);
                qiYeTuPu.setLowerInduId(lowerInduId);
            }
            else if (len<2){
                qiYeTuPu.setLowerInduName("0");
                qiYeTuPu.setLowerInduId(0);
            }

        }
            else {
                qiYeTuPu.setLowerInduId(0);
                qiYeTuPu.setLowerInduName("0");
                qiYeTuPu.setCurInduName("0");
                qiYeTuPu.setCurInduId(0);
                qiYeTuPu.setUpInduId(0);
                qiYeTuPu.setUpInduName("0");
            }
    }
        CompanyInfo companyInfo =new CompanyInfo(businessData,netProfit,mainOperatingIncome,qiYeTuPu,zhuanNum,renNum);
        return companyInfo;
    }

    @Override
    public Double getShengCun(String induId,String regionId){

        QueryWrapper<Region> query1 = new QueryWrapper<>();
        query1.eq("id", Integer.parseInt(regionId));
        Region region = regionMapper.selectOne(query1);
        Integer level = region.getLevel();
        Integer companyNum = businessDataMapper.getCompanyNum(Integer.parseInt(induId), Integer.parseInt(regionId), level);
        System.out.println(companyNum);
        List<Date> date = businessDataMapper.getDate(Integer.parseInt(induId), Integer.parseInt(regionId), level);
        Double sum = 0.0;
        LocalDateTime now = LocalDateTime.now();
        Double yearnow = Double.parseDouble(String.format("%tY", now));
        for(int i =0;i<date.size();i++){
            Date date1 = date.get(i);
            Double year = Double.parseDouble(String.format("%tY", date1));
            sum = sum + yearnow - year ;
        }
        if(companyNum==0){
            return 0.0;
        }
        return sum/companyNum;
    }

    @Override
    public Integer getShiTi(String induId,String regionId){
        QueryWrapper<Region> query1 = new QueryWrapper<>();
        query1.eq("id", Integer.parseInt(regionId));
        Region region = regionMapper.selectOne(query1);
        Integer level = region.getLevel();
        int i = Integer.parseInt(induId) ;
        System.out.println(i+"!!!!!!!!!!!!!");
        Integer shiTi = businessDataMapper.getShiTi(i, Integer.parseInt(regionId), level);
        return shiTi;
    }

    @Override
    public FaYu getFaYu(String induId, String regionId){
        QueryWrapper<Region> query1 = new QueryWrapper<>();
        query1.eq("id", Integer.parseInt(regionId));
        Region region = regionMapper.selectOne(query1);
        Integer level = region.getLevel();
        String china = "100000";

        //生存期
        Double shengCun2 =getShengCun(induId, regionId);
        Double shengCun  = getShengCun(induId,china);
        Map<String,Double> mapShengCun  =new HashMap<>();
        mapShengCun.put("全国",shengCun);
        mapShengCun.put("本地",shengCun2);
        Integer i = Integer.parseInt(induId);
        i = i +1;
        //实体
        Integer shiTi2 =getShiTi(i.toString(), regionId);
        Integer shiTi = getShiTi(i.toString(),china);
        Map<String,Integer> mapShiTi = new HashMap<>();
        mapShiTi.put("全国",shiTi);
        mapShiTi.put("本地",shiTi2);


        //企业个数
        Integer companyNum2 = businessDataMapper.getCompanyNum(Integer.parseInt(induId), Integer.parseInt(regionId), level);
        Integer companyNum = businessDataMapper.getCompanyNum(Integer.parseInt(induId), 100000, 0);
        System.out.println(companyNum);
        Map<String,Integer> mapQiYe = new HashMap<>();
        mapQiYe.put("全国",companyNum);
        mapQiYe.put("本地",companyNum2);

        FaYu faYu = new FaYu(mapShengCun,mapShiTi,mapQiYe);
        return faYu;

    }

    @Override
    public  Map<String,Integer> employNum(String induId,String regionId){
        QueryWrapper<Region> query1 = new QueryWrapper<>();
        query1.eq("id", Integer.parseInt(regionId));
        Region region = regionMapper.selectOne(query1);
        Integer level = region.getLevel();
        //本地
        Integer emploNum2 = businessDataMapper.getEmploNum(Integer.parseInt(induId), Integer.parseInt(regionId), level);
        Integer companyNum2 = businessDataMapper.getCompanyNum(Integer.parseInt(induId), Integer.parseInt(regionId), level);

        //全国
        Integer emploNum = businessDataMapper.getEmploNum(Integer.parseInt(induId), 100000, 0);
        Integer companyNum = businessDataMapper.getCompanyNum(Integer.parseInt(induId), 100000, 0);

        Map<String,Integer> map = new HashMap<>();
        if(emploNum==null||companyNum2==null||companyNum==null||emploNum2==null){
            map.put("全国",0);
            map.put("本地",0);
            return map;
        }
        map.put("全国",emploNum/companyNum);
        map.put("本地",emploNum2/companyNum2);
        return map;
    }

    @Override
    public Map<String,Double> getTax(String induId, String regionId){
        QueryWrapper<Region> query1 = new QueryWrapper<>();
        query1.eq("id", Integer.parseInt(regionId));
        Region region = regionMapper.selectOne(query1);
        Integer level = region.getLevel();
        //本地
        Double tax2 = businessDataMapper.getTax(Integer.parseInt(induId), Integer.parseInt(regionId), level);

        //全国
        Double tax = businessDataMapper.getTax(Integer.parseInt(induId), 100000, 0);

        Map<String,Double> map = new HashMap<>();
        if(tax==null||tax2==null){
            map.put("全国",0.0);
            map.put("本地",0.0);
            return map;
        }
        map.put("全国",tax);
        map.put("本地",tax2);
        return map;
    }

    @Override
    public GongXian getGongXian(String induId, String regionId){
        Map<String, Integer> mapEmployNum = employNum(induId, regionId);
        Map<String, Double> tax = getTax(induId, regionId);
        GongXian gongXian =new GongXian(mapEmployNum,tax);
        return gongXian;

    }

    @Override
    public JingZheng getJingZheng(String induId, String regionId){
        QueryWrapper<Region> query1 = new QueryWrapper<>();
        query1.eq("id", Integer.parseInt(regionId));
        Region region = regionMapper.selectOne(query1);
        Integer level = region.getLevel();

        Map<String,Double> meanIncome = new HashMap<>();
        Map<String,Double> netProfit = new HashMap<>();
        Map<String,Double> aevergeReturn = new HashMap<>();
        //本地
        JingZhengV0 jingZheng1 = businessDataMapper.getJingZheng(Integer.parseInt(induId), Integer.parseInt(regionId), level);

        //全国
        JingZhengV0 jingZheng2 = businessDataMapper.getJingZheng(Integer.parseInt(induId), 100000, 0);
        if(jingZheng1==null||jingZheng2==null){
            meanIncome.put("全国",0.0);
            meanIncome.put("本地",0.0);

            netProfit.put("全国",0.0);
            netProfit.put("本地",0.0);
            aevergeReturn.put("全国",0.0);
            aevergeReturn.put("本地",0.0);
        }
        else {
            meanIncome.put("全国", jingZheng2.getMeanIncome());
            meanIncome.put("本地", jingZheng1.getMeanIncome());

            netProfit.put("全国", jingZheng2.getNetProfit());
            netProfit.put("本地", jingZheng1.getNetProfit());
            aevergeReturn.put("全国", jingZheng2.getAevergeReturn());
            aevergeReturn.put("本地", jingZheng1.getAevergeReturn());

        }
        JingZheng jingZheng = new JingZheng(meanIncome,netProfit,aevergeReturn);
        return jingZheng;

    }

    @Override
    public ChuangXin getChuangXin(String induId, String regionId){
        QueryWrapper<Region> query1 = new QueryWrapper<>();
        query1.eq("id", Integer.parseInt(regionId));
        Region region = regionMapper.selectOne(query1);
        Integer level = region.getLevel();

        Integer zhuanli2 = businessDataMapper.getZhuanli(Integer.parseInt(induId), Integer.parseInt(regionId), level);
        Integer zhuanli = businessDataMapper.getZhuanli(Integer.parseInt(induId), 100000, 0);

        Integer emploNum2 = businessDataMapper.getEmploNum(Integer.parseInt(induId), Integer.parseInt(regionId), level);
        Integer emploNum = businessDataMapper.getEmploNum(Integer.parseInt(induId), 100000, 0);
        Map<String,Double> qiangDu = new HashMap<>();
        Map<String,Double> miDu = new HashMap<>();
        if(zhuanli==null||zhuanli2==null||emploNum2==null||emploNum==null){
            qiangDu.put("全国",0.0);
            qiangDu.put("本地",0.0);
            miDu.put("全国", 0.0);
            miDu.put("本地",0.0);

        }
        else
        {
            qiangDu.put("全国",0.0);
            qiangDu.put("本地",0.0);

            double midu1 = 0.00;
            midu1 = midu1 + zhuanli ;
            midu1 = midu1 * 1000;
            midu1 = midu1/emploNum;

            double midu2 = 0.00;
            midu2 = midu2 + zhuanli2 ;
            midu2 = midu2 * 1000;
            midu2 = midu2/emploNum2;
            miDu.put("全国", midu1);
            miDu.put("本地",midu2);
        }


        ChuangXin chuangXin = new ChuangXin(qiangDu,miDu);
        return  chuangXin;
    }

    @Override
    public  ZiBen getZiBen(String induId, String regionId){
        QueryWrapper<Region> query1 = new QueryWrapper<>();
        query1.eq("id", Integer.parseInt(regionId));
        Region region = regionMapper.selectOne(query1);
        Integer level = region.getLevel();

        Map<String,Double> fuZhai = new HashMap<>();
        Map<String,Double> rongZi = new HashMap<>();
        Map<String,Double> touZi = new HashMap<>();

        Double fuZhan2 = businessDataMapper.getFuZhan(Integer.parseInt(induId), Integer.parseInt(regionId), level);
        Double fuZhan1 = businessDataMapper.getFuZhan(Integer.parseInt(induId), 100000, 0);

        Double chouZi2 = businessDataMapper.getChouZi(Integer.parseInt(induId), Integer.parseInt(regionId), level);
        Double chouZi1 = businessDataMapper.getChouZi(Integer.parseInt(induId),100000, 0);

        Double touZi2 = businessDataMapper.getTouZi(Integer.parseInt(induId), Integer.parseInt(regionId), level);
        Double touZi1 = businessDataMapper.getTouZi(Integer.parseInt(induId),100000, 0);

        Integer companyNum2 = businessDataMapper.getCompanyNum(Integer.parseInt(induId), Integer.parseInt(regionId), level);
        Integer companyNum1 = businessDataMapper.getCompanyNum(Integer.parseInt(induId), 100000, 0);
        if(fuZhan2==null||fuZhan1==null||chouZi2==null||chouZi1==null||touZi2==null||touZi1==null||companyNum2==null||companyNum1==null){
            fuZhai.put("全国",0.0);
            fuZhai.put("本地",0.0);
            rongZi.put("全国",0.0);
            rongZi.put("本地",0.0);
            touZi.put("全国",0.0);
            touZi.put("本地",0.0);
        }
        else {
            fuZhai.put("全国",fuZhan1);
            fuZhai.put("本地",fuZhan2);

            rongZi.put("全国",chouZi1/companyNum1);
            rongZi.put("本地",chouZi2/companyNum2);

            touZi.put("全国",touZi1/companyNum1);
            touZi.put("本地",touZi2/companyNum1);

        }
        ZiBen ziBen = new ZiBen(fuZhai,rongZi,touZi);
        return ziBen;
    }

    @Override
    public  ChengZhang getChengZhang(String induId, String regionId){
        QueryWrapper<Region> query1 = new QueryWrapper<>();
        query1.eq("id", Integer.parseInt(regionId));
        Region region = regionMapper.selectOne(query1);
        Integer level = region.getLevel();

        Map<String,Double> incomeSpeed = new HashMap<>();
        Map<String,Double> profitSpeed = new HashMap<>();

        List<Double> incomeSpeed2 = businessDataMapper.getIncomeSpeed(Integer.parseInt(induId), Integer.parseInt(regionId), level);
        List<Double> incomeSpeed1 = businessDataMapper.getIncomeSpeed(Integer.parseInt(induId), 100000, 0);

        List<Double> profitSpeed2 = businessDataMapper.getProfitSpeed(Integer.parseInt(induId), Integer.parseInt(regionId), level);
        List<Double> profitSpeed1 = businessDataMapper.getProfitSpeed(Integer.parseInt(induId), 100000, 0);

        if (incomeSpeed2==null||incomeSpeed1==null||profitSpeed2==null||profitSpeed1==null){
            incomeSpeed.put("全国",0.0);
            incomeSpeed.put("本地",0.0);

            profitSpeed.put("全国",0.0);
            profitSpeed.put("本地",0.0);
        }

        else {
            incomeSpeed.put("全国",incomeSpeed1.get(0)/incomeSpeed1.get(1)-1);
            incomeSpeed.put("本地",incomeSpeed2.get(0)/incomeSpeed1.get(1)-1);

            profitSpeed.put("全国",profitSpeed1.get(0)/incomeSpeed1.get(1)-1);
            profitSpeed.put("本地",profitSpeed2.get(0)/incomeSpeed1.get(1)-1);
        }
        return new ChengZhang(incomeSpeed,profitSpeed);

    }


}
