package com.boot.zysf.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.boot.zysf.api.Runner.FileReadRunner;
import com.boot.zysf.api.mapper.BusinessDataMapper;
import com.boot.zysf.api.mapper.IndustrialCategoryMapper;
import com.boot.zysf.api.mapper.RegionMapper;
import com.boot.zysf.api.po.*;
import com.boot.zysf.api.po.InduAccess.*;
import com.boot.zysf.api.po.v0.BussinessDataV0;
import com.boot.zysf.api.po.v0.ChanYeMap;
import com.boot.zysf.api.po.v0.ComInfo;
import com.boot.zysf.api.po.v0.CompanyInfo;
import com.boot.zysf.api.service.*;
import com.boot.zysf.api.util.AddressUntils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/BusinessData")
public class BusinessDataController {
    @Autowired
    BusinessDataService businessDataService;
    @Autowired
    HandlerService handlerService;
    @Autowired
    AddressUntils addressUntils;
    @Autowired
    AssetLiabilityService assetLiabilityService;
    @Autowired
    CashFlowService cashFlowService;
    @Autowired
    ProfitService profitService;
    @Autowired
    EnterpriseDataService enterpriseDataService;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    IIndustrialCategoryService iIndustrialCategoryService;
    @Autowired
    IndustroyTupuService industroyTupuService;
    @Autowired
    RegionMapper regionMapper;
    
    @Autowired
    BusinessDataMapper businessDataMapper;
    @Autowired
    IndustrialCategoryMapper industrialCategoryMapper;
    @Autowired
    FileReadRunner businessDataReadRunner;

    @PostMapping(value = "/uploadBusinessData", headers = "content-type=multipart/form-data")
    public List<BusinessData> upLoad(@RequestParam(value = "file", required = true) MultipartFile file) {
        return businessDataService.getBusinessData(file);
    }

    @PostMapping(value = "/importQiye", headers = "content-type=multipart/form-data")
    public void importQiYe(@RequestParam(value = "file", required = true) MultipartFile file) {
    businessDataReadRunner.addIntoQiYe(file);

    }
    @PostMapping(value = "/addInto", headers = "content-type=multipart/form-data")
    public void addInto(@RequestParam(value = "file", required = true) MultipartFile file) {
        List<BusinessData> businessDataList = upLoad(file);
        ;
        for (int i = 0; i < businessDataList.size(); i++) {
            String lat = null;
            String lng = null;
            String add3 = null;
            BusinessData businessData = businessDataList.get(i);
            if (businessDataList.get(i) != null) {
                List<String> location = addressUntils.getLocation(businessData.getAddress());
                if (location.size() != 0) {
                    lat = location.get(0);
                    lng = location.get(1);
                    add3 = addressUntils.getAdd(lng, lat);
                }
                businessData.setAdd3(add3);
                businessData.setLat(lat);
                businessData.setLng(lng);
                businessDataService.save(businessData);
            }
        }
    }

    @PostMapping(value = "/getAdd3")
    public void getAdd3() {
        List<BusinessData> businessDataList = businessDataService.list();
        for (int i = 0; i < businessDataList.size(); i++) {
            BusinessData businessData = businessDataList.get(i);
            System.out.println(i);
            List<String> location = addressUntils.getLocation(businessData.getAddress());
            if (location.size() == 0) {
                continue;
            }
            String lat = location.get(0);
            String lng = location.get(1);
            String add3 = addressUntils.getAdd(lng, lat);
            businessData.setAdd3(add3);
            businessDataService.updateById(businessData);
        }
    }

    @PostMapping(value = "/getAdd")
    public void getAdd() {
        List<BusinessData> businessDataList = businessDataService.list();
        for (int i = 0; i < businessDataList.size(); i++) {
            String lat = null;
            String lng = null;
            BusinessData businessData = businessDataList.get(i);
            List<String> location = addressUntils.getLocation(businessData.getAddress());
            if (location.size() != 0) {
                lat = location.get(0);
                lng = location.get(1);
            }
            businessData.setLat(lat);
            businessData.setLng(lng);
            businessDataService.updateById(businessData);
        }
    }

    @GetMapping(value = "/getzichafuzhanbiao")
    public R getZichanfuzhan(@RequestParam(value = "id", required = true) Integer id) {
        QueryWrapper<BusinessData> query = new QueryWrapper<>();
        query.eq("id", id);
        BusinessData business = businessDataService.getOne(query);
        String name = business.getEmpName();
        QueryWrapper<AssetLiability> query1 = new QueryWrapper<>();
        query1.eq("name", name);
        List<AssetLiability> list = assetLiabilityService.list(query1);
        return R.ok(list);
    }

    @GetMapping(value = "/getxianjinliuliang")
    public R getXianjinliuliang(@RequestParam(value = "id", required = true) Integer id) {
        QueryWrapper<BusinessData> query = new QueryWrapper<>();
        query.eq("id", id);
        BusinessData business = businessDataService.getOne(query);
        String name = business.getEmpName();
        QueryWrapper<Profit> query1 = new QueryWrapper<>();
        query1.eq("name", name);
        List<Profit> list = profitService.list(query1);
        return R.ok(list);
    }

    @GetMapping(value = "/getlirui")
    public R getLirui(@RequestParam(value = "id", required = true) Integer id) {
        QueryWrapper<BusinessData> query = new QueryWrapper<>();
        query.eq("id", id);
        BusinessData business = businessDataService.getOne(query);
        String name = business.getEmpName();
        QueryWrapper<CashFlow> query1 = new QueryWrapper<>();
        query1.eq("name", name);
        List<CashFlow> list = cashFlowService.list(query1);
        return R.ok(list);
    }

//首页地图
    @PostMapping(value = "/mainMap")
    public R getMainMap() {
        System.out.println("begin"+LocalDateTime.now());
        IndexInfoV0 indexInfoV0 = new IndexInfoV0();
        //企业信息和企业数量
        List<BusinessData> list = businessDataService.list();
        Integer companyNum = list.size();
        List<BusinessData> list4 = businessDataMapper.findLatNotNull();
        List<BussinessDataV0> collect = list4.stream().map(BussinessDataV0::new).collect(Collectors.toList());
        indexInfoV0.setBusinessDataList(collect);
        indexInfoV0.setCompanyNum(companyNum);
        Integer zhuanliNum = 4276;
        Integer rencaiNum = 3401;
        Integer tupuNum = 0;
        Integer hangyeNum = 0;
        System.out.println("人才数量"+LocalDateTime.now());
        indexInfoV0.setRencaiNum(rencaiNum);
        indexInfoV0.setZhuanliNum(zhuanliNum);
        //行业数量
        QueryWrapper<IndustrialCategory> query = new QueryWrapper<>();
        query.ne(IndustrialCategory.CODE, "last");
        List<IndustrialCategory> list1 = iIndustrialCategoryService.list(query);
        hangyeNum = list1.size();
        indexInfoV0.setHangyeNum(hangyeNum);
        System.out.println("h行业数量"+LocalDateTime.now());
        //行业比例
        Map<String,Double> map = new HashMap<>();
        QueryWrapper<IndustrialCategory> query1 = new QueryWrapper<>();
        query1.eq("parent_id",-1);
        List<IndustrialCategory> list2 = iIndustrialCategoryService.list(query1);
        for(int i = 0;i<list2.size();i++){
            String name = list2.get(i).getName();
            Integer num = 1;
            Queue<IndustrialCategory>  queue = new LinkedList<>();
            queue.add(list2.get(i));
            while(!queue.isEmpty()) {
                Integer id = queue.poll().getId();
                for (int j = 0; j < list1.size(); j++) {
                    IndustrialCategory one = list1.get(j);
                    Integer parentId = one.getParentId();
                    if (parentId.equals(id)) {
                        queue.add(one);
                        num++;
                    }
                }
            }
            map.put(name,  (double)(num)/(double)(list1.size()));
        }
        indexInfoV0.setBili(map);
        System.out.println("行业比例"+LocalDateTime.now());
        //图谱数量
        QueryWrapper<IndustroyTupu> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("parent_id",-1);
        List<IndustroyTupu> list3 = industroyTupuService.list(queryWrapper);
        for(int i =0;i<list3.size();i++){
            Integer id = list3.get(i).getId();
            for(int j = 0;j<list3.size();j++){
                if(list3.get(j).getParentId()==id){
                    tupuNum++;
                    break;
                }
            }
        }
        indexInfoV0.setTupuNum(tupuNum);
        System.out.println("end"+LocalDateTime.now());
        return  R.ok(indexInfoV0);

    }
    //查询企业信息
    @GetMapping(value = "/getCompany")
    public CompanyInfo getCompany(@RequestParam(value = "empName",required = true)String name){
        QueryWrapper<BusinessData> query = new QueryWrapper<>();
        query.eq("emp_name",name);
        BusinessData businessData = businessDataService.getOne(query);
        CompanyInfo qiye = businessDataService.getQiye(businessData);
        return qiye;
    }
    @GetMapping(value = "/dealData")
    public void dealData(){
        List<EnterpriseData> list = enterpriseDataService.list();
        for(int i=0;i<list.size();i++){
            EnterpriseData enterpriseData = list.get(i);
            String empName = enterpriseData.getEmpName();
            QueryWrapper<AssetLiability> query = new QueryWrapper<>();
            query.eq("name",empName);
            List<AssetLiability> list1 = assetLiabilityService.list(query);
            if(list1.size()>0) {
                String assetTotal = list1.get(0).getAssetTotal();
                enterpriseData.setAssetTotal(assetTotal);
                enterpriseDataService.saveOrUpdate(enterpriseData);
            }
            System.out.println(i);
        }
    }

    @GetMapping(value = "/chanyeditu")
    public ChanYeMap getChanMap(@RequestParam(value = "区域id",required = false,defaultValue = "100000")String regionId,@RequestParam(value = "产业id",required = true)String induId){
        String id = "100000";
        String name = "中国";
        QueryWrapper<Region> query1 = new QueryWrapper<>();
        query1.eq("id", Integer.parseInt(regionId));
        Region region = regionMapper.selectOne(query1);
        System.out.println(region);
        if(region!=null){
            if(region.getLevel()==0){//全国

                //企业信息列表
                List<ComInfo> china = businessDataMapper.getChina(Integer.parseInt(induId));

                //专利列表
                List<ZhuanLi> zhuanInfo = industrialCategoryMapper.getZhuanInfo(Integer.parseInt(induId));//该产业的专利

                //人才列表
                List<RenCai> renInfo = industrialCategoryMapper.getRenInfo(Integer.parseInt(induId));
                ChanYeMap chanYeMap = new ChanYeMap(id,name,china,zhuanInfo,renInfo);
                return chanYeMap;
            }
            //省
            else if(region.getLevel() == 1){
                System.out.println("in");
                id = region.getId().toString();
                name = region.getMername();
                //企业信息列表
                int i1 = Integer.parseInt(induId);
                System.out.println(i1);
                List<ComInfo> china = businessDataMapper.getSheng(i1,Integer.parseInt(regionId));

                //专利列表
                List<ZhuanLi> zhuanInfo = businessDataMapper.getShengZhuan(i1,Integer.parseInt(regionId));//该产业的专利


                //人才列表
                List<RenCai> renInfo = businessDataMapper.getShengRen(Integer.parseInt(induId),Integer.parseInt(regionId));

                ChanYeMap chanYeMap = new ChanYeMap(id,name,china,zhuanInfo,renInfo);
                return chanYeMap;
            }

            //市
            else if(region.getLevel() == 2){

                id = region.getId().toString();
                name = region.getMername();
                //企业信息列表
                List<ComInfo> china = businessDataMapper.getShi(Integer.parseInt(induId),Integer.parseInt(regionId));

                //专利列表
                List<ZhuanLi> zhuanInfo = businessDataMapper.getShiZhuan(Integer.parseInt(induId),Integer.parseInt(regionId));//该产业的专利


                //人才列表
                List<RenCai> renInfo = businessDataMapper.getShiRen(Integer.parseInt(induId),Integer.parseInt(regionId));


                ChanYeMap chanYeMap = new ChanYeMap(id,name,china,zhuanInfo,renInfo);
                return chanYeMap;
            }
            //县
            else if(region.getLevel() == 3){

                id = region.getId().toString();
                name = region.getMername();
                //企业信息列表
                List<ComInfo> china = businessDataMapper.getQu(Integer.parseInt(induId),Integer.parseInt(regionId));

                //专利列表
                List<ZhuanLi> zhuanInfo = businessDataMapper.getQuZhuan(Integer.parseInt(induId),Integer.parseInt(regionId));//该产业的专利

                //人才列表
                List<RenCai> renInfo = businessDataMapper.getQuRen(Integer.parseInt(induId),Integer.parseInt(regionId));


                ChanYeMap chanYeMap = new ChanYeMap(id,name,china,zhuanInfo,renInfo);
                return chanYeMap;
            }


        }
        return null;
    }

    @GetMapping(value = "/gongxian")
    public GongXian getPingGu(@RequestParam(value = "区域id",required = false,defaultValue = "100000")String regionId, @RequestParam(value = "产业id",required = true)String induId){
        Map<String, Integer> mapEmployNum = businessDataService.employNum(induId, regionId);
        Map<String, Double> tax = businessDataService.getTax(induId, regionId);
        GongXian gongXian =new GongXian(mapEmployNum,tax);
        return gongXian;
    }

    @GetMapping(value = "/jingzheng")
    public JingZheng getJingZheng(@RequestParam(value = "区域id",required = false,defaultValue = "100000")String regionId, @RequestParam(value = "产业id",required = true)String induId){
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

    @GetMapping(value = "/chuangxin")
    public ChuangXin getChuangXin(@RequestParam(value = "区域id",required = false,defaultValue = "100000")String regionId, @RequestParam(value = "产业id",required = true)String induId){
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

    @GetMapping(value = "/zibenli")
    public ZiBen getZiBenLi(@RequestParam(value = "区域id",required = false,defaultValue = "100000")String regionId, @RequestParam(value = "产业id",required = true)String induId){
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

    @GetMapping(value = "/chanyepinggu")
    public ChanYePingGu getChanYePingGu(@RequestParam(value = "区域id",required = false,defaultValue = "100000")String regionId,@RequestParam(value = "产业id",required = true)String induId){
        FaYu faYu = businessDataService.getFaYu(induId, regionId);
        GongXian gongXian = businessDataService.getGongXian(induId, regionId);
        JingZheng jingZheng = businessDataService.getJingZheng(induId, regionId);
        ChuangXin chuangXin = businessDataService.getChuangXin(induId, regionId);
        ZiBen ziBen = businessDataService.getZiBen(induId, regionId);
        ChengZhang chengZhang = businessDataService.getChengZhang(induId, regionId);
        ChanYePingGu chanYePingGu = new ChanYePingGu(faYu,gongXian,jingZheng,chuangXin,ziBen,chengZhang);
        return  chanYePingGu;
    }
}