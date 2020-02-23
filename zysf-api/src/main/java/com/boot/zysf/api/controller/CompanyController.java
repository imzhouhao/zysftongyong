package com.boot.zysf.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.boot.zysf.api.po.Company;
import com.boot.zysf.api.service.ICompanyService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boot.zysf.api.util.CompanyRead;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 企业信息 控制器类
 * </p>
 * @author zzq
 * @since 2019-07-10
 */
@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    public ICompanyService iCompanyService;
    @Autowired
    CompanyRead companyRead;

    /**
    * 分页查询数据
    *
    * @param page 分页对象
    * @param company   查询条件
    * @return
    */
    @ApiOperation(value = "上传视频接口",notes = "上传视频接口")
    @GetMapping("/page")
    public R<IPage> page(Page page , Company company) {

        return R.ok(iCompanyService.page(page, buildQuery(company)));
    }

    private QueryWrapper<Company> buildQuery(Company company) {
        QueryWrapper<Company> query = null;
        if(company != null) {
            String name = company.getName();
            String address = company.getAddress();
            company.setAddress(null);
            company.setName(null);
            query = Wrappers.query(company);
            if(name != null) {
                query.like(Company.NAME, name);
            }
            if(address != null) {
                query.like(Company.ADDRESS, address);
            }
        }
        return query;
    }

    /**
     * 查询全部数据
     *
     * @param company   查询条件
     * @return
     */
    @GetMapping("/list")
    public  R list(Company company) {
        return R.ok(iCompanyService.list(buildQuery(company)));
    }

    /**
     * 查询全部数据
     *
     * @param fields   查询的数据字段
     * @param company   查询条件
     * @return
     */
    @GetMapping("/list/fields")
    public  R fieldsList(String[] fields, Company company) {
        QueryWrapper<Company> query = buildQuery(company);
        if(query == null) {
            query = Wrappers.query();
        }
        query.select(fields);
        List<Company> result = iCompanyService.list(query);
        return R.ok(result);
    }


    /**
    * 通过ID查询数据信息
    *
    * @param id ID
    * @return 信息
    */
    @GetMapping("/{id}")
    public R getById(@PathVariable String id) {
        return R.ok(iCompanyService.getById(id));
    }

    /**
    * 添加数据
    *
    * @param company 添加信息，需要权限验证
    * @return success、false
    */
    @PostMapping
    public R save(@Valid @RequestBody Company company) {
        return R.ok(iCompanyService.save(company));
    }

    /**
    * 删除信息
    *
    * @param id   ID
    * @return R
    */
    @DeleteMapping("/{id}")
    public R removeById(@PathVariable String id) {
        return R.ok(iCompanyService.removeById(id));
    }

    /**
    * 修改信息
    *
    * @param  company Company信息
    * @return success/false
    */
    @PutMapping
    public R updateById(@Valid @RequestBody Company company) {
        return R.ok(iCompanyService.updateById(company));
    }
    /**
     * 批量插入信息
	 *
	 * @param companys Company列表信息
	 * @return success/false
	 */
    @PostMapping("/batch")
    public R saveBatch(@RequestBody List<Company> companys) {
        return R.ok(iCompanyService.saveBatch(companys));
    }


    @PostMapping(value = "/addIndustroy", headers = "content-type=multipart/form-data")
    public void addInto2(@RequestParam(value = "file", required = true) MultipartFile file){
        companyRead.getExcelInfo(file);
    }

}
