package com.boot.zysf.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.boot.zysf.api.po.Indu;
import com.boot.zysf.api.po.IndustrialCategory;
import com.boot.zysf.api.service.InduService;
import com.boot.zysf.api.util.ChanyeRead;
import com.boot.zysf.api.util.InduRead;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Api(tags = "产业图谱" ,description = "产业图谱API")
@RestController
@RequestMapping("/indu")
public class InduController {
    @Autowired
    InduRead induRead;
    @Autowired
    InduService induService;
    @Autowired
    ChanyeRead chanyeRead;
    @PostMapping(value = "/addIndustroy", headers = "content-type=multipart/form-data")
    public void  add(@RequestParam(value = "file", required = true)MultipartFile file){
        chanyeRead.getExcelInfo(file);
    }
    /**
     * 指定字段分页查询数据
     *
     *
     *
     * @param indu   查询条件
     * @return
     */
//    @ApiOperation(value = "根据指定字段查询，默认是查询全部字段")
//    @GetMapping ("/list")
//    public List<Indu> listAll( @RequestParam(required = false) String[] fields,Indu indu){
//        QueryWrapper<Indu> query = Wrappers.query(indu);
//        if(fields !=null && fields.length > 0){
//            query.select(fields);
//        }
//        return induService.list(query);
//    }


    @ApiOperation(value = "查询生物医学")
    @GetMapping ("/list0")
    public R  list0( @RequestParam(required = false) Indu indu){
        QueryWrapper<Indu> query = Wrappers.query(indu);
        query.between("id",1,226);
        return R.ok(induService.list(query));
    }

    @ApiOperation(value = "查询现代服务业")
    @GetMapping ("/list1")
    public R  list1( @RequestParam(required = false) Indu indu){
        QueryWrapper<Indu> query = Wrappers.query(indu);
        query.between("id",227,930);
        return R.ok(induService.list(query));
    }

    @ApiOperation(value = "查询新材料")
    @GetMapping ("/list2")
    public R  list2( @RequestParam(required = false) Indu indu){
        QueryWrapper<Indu> query = Wrappers.query(indu);
        query.between("id",1136,1340);
        return R.ok(induService.list(query));
    }

    @ApiOperation(value = "查询新一代信息技术")
    @GetMapping ("/list3")
    public R  list3( @RequestParam(required = false) Indu indu){
        QueryWrapper<Indu> query = Wrappers.query(indu);
        query.between("id",1341,1380);
        return R.ok(induService.list(query));
    }

    @ApiOperation(value = "查询现代服务业")
    @GetMapping ("/list4")
    public R  listAll( @RequestParam(required = false) Indu indu){
        QueryWrapper<Indu> query = Wrappers.query(indu);
        query.between("id",1381,1648);
        return R.ok(induService.list(query));
    }

    @ApiOperation(value = "新材料产业全景")
    @GetMapping ("/list5")
    public R  list5( @RequestParam(required = false) Indu indu){
        QueryWrapper<Indu> query = Wrappers.query(indu);
        query.between("id",1653,1696);
        return R.ok(induService.list(query));
    }

    @ApiOperation(value = "装备制造产业全景")
    @GetMapping ("/list6")
    public R  list6( @RequestParam(required = false) Indu indu){
        QueryWrapper<Indu> query = Wrappers.query(indu);
        query.between("id",1697,1752);
        return R.ok(induService.list(query));
    }

    @ApiOperation(value = "生物医药产业全景")
    @GetMapping ("/list7")
    public R  list7( @RequestParam(required = false) Indu indu){
        QueryWrapper<Indu> query = Wrappers.query(indu);
        query.between("id",1753,1878);
        return R.ok(induService.list(query));
    }

    @ApiOperation(value = "现代服务业产业全景")
    @GetMapping ("/list8")
    public R  list8( @RequestParam(required = false) Indu indu){
        QueryWrapper<Indu> query = Wrappers.query(indu);
        query.between("id",1879,1914);
        return R.ok(induService.list(query));
    }

    @ApiOperation(value = "新一代信息技术产业全景")
    @GetMapping ("/list9")
    public R  list9( @RequestParam(required = false) Indu indu){
        QueryWrapper<Indu> query = Wrappers.query(indu);
        query.between("id",1915,1968);
        return R.ok(induService.list(query));
    }



}
