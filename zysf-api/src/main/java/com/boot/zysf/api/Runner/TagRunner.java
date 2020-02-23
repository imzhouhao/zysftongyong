package com.boot.zysf.api.Runner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boot.zysf.api.config.CountableThreadPool;
import com.boot.zysf.api.config.SpringContextUtil;
import com.boot.zysf.api.po.BusinessData;
import com.boot.zysf.api.po.IndustrialCategory;
import com.boot.zysf.api.po.IndustroyTupu;
import com.boot.zysf.api.service.BusinessDataService;
import com.boot.zysf.api.service.IIndustrialCategoryService;
import com.boot.zysf.api.service.IndustroyTupuService;
import com.boot.zysf.api.util.Tagging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class TagRunner implements CommandLineRunner {
    @Autowired
    Tagging tagging;
    @Autowired
    IndustroyTupuService industroyTupuService;
    @Autowired
    BusinessDataService businessDataService;
    @Autowired
    IIndustrialCategoryService iIndustrialCategoryService;

    public void getTag() throws Exception {
        tagging.getCode();
        CountableThreadPool threadPool = new CountableThreadPool(60);
        List<BusinessData> businessDataList = businessDataService.list();
        QueryWrapper<IndustroyTupu> query = new QueryWrapper<>();
        query.eq("parent_id",-1);
        List<IndustroyTupu> industroyTupuList = industroyTupuService.list(query);
        QueryWrapper<IndustrialCategory> query2 = new QueryWrapper<>();
        query2.eq("parent_id", -1);
        List<IndustrialCategory> industrialCategoryList = iIndustrialCategoryService.list(query2);
        while(!Thread.currentThread().isInterrupted()) {
            for ( int i=0; i < businessDataList.size(); i++) {
                BusinessData businessData = businessDataList.get(i);
                int index = i;
                threadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        List<String> tag = tagging.getTagsChanYe(businessData, industroyTupuList);
                        List<String> tagsHangYe = tagging.getTagsHangYe(businessData, industrialCategoryList);
                        System.out.println("index"+index+tag);
                        String tags = "";
                        String tags1 = "";
                        for (int j = 0; j < tag.size(); j++) {
                         if(tag.get(j)!=null) {
                        tags = tags.concat(tag.get(j) + ",");
                         }
                      }
                        businessData.setTags2(tags);
                        for (int j = 0; j < tagsHangYe.size(); j++) {
                            if(tagsHangYe.get(j)!=null) {
                                tags1 = tags1.concat(tagsHangYe.get(j) + ",");
                            }
                        }
                        businessData.setTags(tags1);
                        businessDataService.updateById(businessData);
                }

                });
                Date date = new Date();
                SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
                System.out.println(dateFormat.format(date));

            }
            break;
        }
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
