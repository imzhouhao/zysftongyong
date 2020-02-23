package com.boot.zysf.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.boot.zysf.api.Runner.FileReadRunner;
import com.boot.zysf.api.Runner.TagRunner;
import com.boot.zysf.api.mapper.IndustrialCategoryMapper;
import com.boot.zysf.api.mapper.IndustroyTupuMapper;
import com.boot.zysf.api.mapper.RegionMapper;
import com.boot.zysf.api.po.*;
import com.boot.zysf.api.po.v0.*;
import com.boot.zysf.api.service.BusinessDataService;
import com.boot.zysf.api.service.IIndustrialCategoryService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boot.zysf.api.service.IndustroyTupuService;
import com.boot.zysf.api.util.BusinessDateRead;
import com.boot.zysf.api.util.IndustryRead;
import com.boot.zysf.api.util.Tagging;
import org.apache.ibatis.executor.Executor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * <p>
 * 产业分类信息表 控制器类
 * </p>
 * @author zzq
 * @since 2019-07-16
 */
@RestController
@RequestMapping("/industrial-category")
public class IndustrialCategoryController {
    @Autowired
    public IIndustrialCategoryService iIndustrialCategoryService;
    @Autowired
    public BusinessDataService businessDataService;
    @Autowired
    private IndustryRead industryRead;
    @Autowired
    private BusinessDateRead businessDateRead;

    @Autowired
    IndustrialCategoryMapper industrialCategoryMapper;

    @Autowired
    IndustroyTupuMapper industroyTupuMapper;

    @Autowired
    RegionMapper regionMapper;
    @Autowired
    TagRunner tagRunner;
    @Autowired
    FileReadRunner fileReadRunner;

    @Autowired
    private IndustroyTupuService industroyTupuService;



    /**
    * 分页查询数据
    *
    * @param page 分页对象
    * @param industrialCategory   查询条件
    * @return
    */
    @GetMapping("/page")
    public  R<IPage> page(Page page ,IndustrialCategory industrialCategory) {
        return R.ok(iIndustrialCategoryService.page(page, Wrappers.query(industrialCategory)));
    }

        /**
         * 指定字段分页查询数据
         *
         * @param page 分页对象
         * @param fields 字段，前端可以通过字段之间间隔,分传递参数
         * @param industrialCategory   查询条件
         * @return
         */
        @GetMapping("/page/fields")
        public  R<IPage> fieldsPage(Page page, String[] fields, IndustrialCategory industrialCategory) {
            QueryWrapper<IndustrialCategory> query = Wrappers.query(industrialCategory);
            query.select(fields);
            return R.ok(iIndustrialCategoryService.page(page, query));
        }


    /**
     * 指定字段查询全部数据
     *
     * @param fields 字段，前端可以通过字段之间间隔,分传递参数
     * @param industrialCategory   查询条件
     * @return
     */
    @GetMapping("/list/fields")
    public  R fieldsList(String[] fields, IndustrialCategory industrialCategory) {
        QueryWrapper<IndustrialCategory> query = Wrappers.query(industrialCategory);
        query.select(fields);
        return R.ok(iIndustrialCategoryService.list(query));
    }

    @GetMapping("/list/name")
    public List<String> getAllName(){
        QueryWrapper<IndustrialCategory> query = new QueryWrapper<>();
        query.select(IndustrialCategory.NAME);
        List<IndustrialCategory> list = iIndustrialCategoryService.list(query);
        List<String> names = list.stream().map(IndustrialCategory::getName).collect(Collectors.toList());
//        return R.ok(list);
        return names;
    }
    /**
     * 查询全部数据
     *
     * @param industrialCategory   查询条件
     * @return
     */
    @GetMapping("/list")
    public  R list(IndustrialCategory industrialCategory) {
        QueryWrapper<IndustrialCategory> query = new QueryWrapper<>(industrialCategory);
        query.ne(IndustrialCategory.CODE, "last");
         return R.ok(iIndustrialCategoryService.list(query));
    }

    /**
     * 查询全部数据
     *
     * @param ids   查询条件
     * @return
     */
    @GetMapping("/list/ids")
    public  R listByIds(@RequestParam("ids") String[] ids) {
        QueryWrapper<IndustrialCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(IndustrialCategory.ID, ids)
                .or()
                .in(IndustrialCategory.PARENT_ID, ids);
        return R.ok(iIndustrialCategoryService.list(queryWrapper));
    }

    /**
    * 通过ID查询数据信息
    *
    * @param id ID
    * @return 信息
    */
    @GetMapping("/{id}")
    public R getById(@PathVariable String id) {
        return R.ok(iIndustrialCategoryService.getById(id));
    }

    /**
    * 添加数据
    *
    * @param industrialCategory 添加信息，需要权限验证
    * @return success、false
    */
    @PostMapping
    public R save(@Valid @RequestBody IndustrialCategory industrialCategory) {
        return R.ok(iIndustrialCategoryService.save(industrialCategory));
    }

    /**
    * 删除信息
    *
    * @param id   ID
    * @return R
    */
    @DeleteMapping("/{id}")
    public R removeById(@PathVariable String id) {
        return  R.ok(iIndustrialCategoryService.removeById(id));
    }

    /**
    * 修改信息
    *
    * @param  industrialCategory IndustrialCategory信息
    * @return success/false
    */
    @PutMapping
    public R updateById(@Valid @RequestBody IndustrialCategory industrialCategory) {
        return R.ok(iIndustrialCategoryService.updateById(industrialCategory));
    }
    /**
     * 批量插入信息
	 *
	 * @param industrialCategorys IndustrialCategory列表信息
	 * @return success/false
	 */
    @PostMapping("/batch")
    public R saveBatch(@RequestBody List<IndustrialCategory> industrialCategorys) {
        return R.ok(iIndustrialCategoryService.saveBatch(industrialCategorys));
    }

   /* @PostMapping(value = "/addIndus", headers = "content-type=multipart/form-data")
    public List<IndustrialCategory> addIndus(@RequestParam(value = "file", required = true) MultipartFile file){
        return iIndustrialCategoryService.addFile(file);
    }
   @PostMapping(value = "/addInto", headers = "content-type=multipart/form-data")
    public void addInto(@RequestParam(value = "file", required = true) MultipartFile file){
        List<IndustrialCategory> industrialCategoryList =  addIndus(file);;
        for(int i=0;i<industrialCategoryList.size();i++){
            iIndustrialCategoryService.addInto(industrialCategoryList.get(i));
        }


    }*/
    @PostMapping(value = "/addIndustroy", headers = "content-type=multipart/form-data")
    public void addInto2(@RequestParam(value = "file", required = true) MultipartFile file){
        industryRead.getExcelInfo(file);
    }

    @PostMapping(value = "/importHangYe", headers = "content-type=multipart/form-data")
    public void importHangYe(@RequestParam(value = "file", required = true) MultipartFile file){
        fileReadRunner.addIntoHangYe(file);
    }

    @GetMapping("/tag")
    public void getTag() throws Exception {
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        System.out.println(dateFormat.format(date));
       tagRunner.getTag();
    }
//    @DeleteMapping("/test")
//    public R delete(){
//        return R.ok(iIndustrialCategoryService.removeById())
//    }

    @DeleteMapping("/id")
    public R deleteById(){
      QueryWrapper<IndustrialCategory> query = new QueryWrapper<>();
      query.between("id",3846,4795);
      boolean remove = iIndustrialCategoryService.remove(query);
      return R.ok(remove);
    }

    //获取产业全景
    @GetMapping("/getChanYeQuanJing")
    public InduQuanJingV0 getChanYeQuanJing(@RequestParam(value = "id",required = true) String id){
        IndustrialCategory industrialCategory = industrialCategoryMapper.selectById(id);
        //获取产业名称
        String name = industrialCategory.getName();

        //获取年度数据
        List<NianData> nianData = iIndustrialCategoryService.getNianData(id);

        //获取产业树
        List<TreeNode> tree = iIndustrialCategoryService.getTree(id);

        InduQuanJingV0 induQuanJingV0 = new InduQuanJingV0(name,id,nianData,tree);
        return  induQuanJingV0;
    }
  //利润和收入
    @GetMapping("/test")
    public List<YearData> test(Integer id){
        return iIndustrialCategoryService.test(id);
    }

    //产业图谱
    @GetMapping("/chanye-tupu")
    public ChanYeTuPu getChanYeTuPu(String id){
        return iIndustrialCategoryService.getChanTu(id);
    }

    //上下游关系拓扑图
    @PostMapping("/relation-map")
    public RelationMap getRelationMap(@RequestParam(value = "id",required = true)Integer id){
        //企业数量
        Integer companyNum = industrialCategoryMapper.getCompany2(id);

        //专利列表
        List<ZhuanLi> zhuanli = industrialCategoryMapper.getZhuanInfo(id);
//
//        //人才列表
        List<RenCai> rencai = industrialCategoryMapper.getRenInfo(id);

        //当前产业名称
        IndustroyTupu industroyTupu = industroyTupuMapper.selectById(id);
        String name = industroyTupu.getName();


        //产业树
        List<TreeNode1> tree = iIndustrialCategoryService.getTree2(id.toString());

        //企业信息数组
        List<String> companyInfos = new ArrayList<>();
        List<BusinessData> companyInfo = industrialCategoryMapper.getCompanyInfo(id);
        if(companyInfo.size()>0) {
            for (int i = 0; i < companyInfo.size(); i++) {
                BusinessData businessData = companyInfo.get(i);
                String tags2 = businessData.getTags2();
                if(tags2.indexOf(name)!=-1){
                    companyInfos.add(businessData.getEmpName());
                }
            }
        }
        RelationMap relationMap = new RelationMap(companyNum.toString(),zhuanli,rencai,name,id.toString(),tree,companyInfos);
        return  relationMap;
    }

    @GetMapping("/indu_list")
    public R list(){
        QueryWrapper<IndustrialCategory> query = new QueryWrapper<>();
        query.eq("parent_id",-1);
        return R.ok(iIndustrialCategoryService.list(query));
    }
    //genjuidxuezhao chengshi
    @GetMapping("/city")
    public R citys(@RequestParam (value = "regionid",required = false,defaultValue = "100000") Integer id){
        QueryWrapper<Region> query = new QueryWrapper<>();
        query.eq("pid",id);
        List<Region> regions = regionMapper.selectList(query);
        return R.ok(regions);
    }
}
