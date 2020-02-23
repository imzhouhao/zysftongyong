package com.boot.zysf.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.boot.zysf.api.po.BusinessData;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/*
处理数据
@author zh
 */
public interface HandlerService {
    //行业id和企业名称
   Map<Integer,String>  listMatch();
   void addTags(Map<Integer,String> map);
   String getadd3(String s);
}
