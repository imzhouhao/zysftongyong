package com.boot.zysf.api.util;


import com.boot.zysf.api.config.CountableThreadPool;
import com.boot.zysf.api.config.SpringContextUtil;
import com.boot.zysf.api.po.BusinessData;
import com.boot.zysf.api.po.IndustrialCategory;
import com.boot.zysf.api.po.IndustroyTupu;
import com.boot.zysf.api.service.IIndustrialCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;
public class ThreadTag1 implements  Runnable{


    private final BusinessData businessData;


    public ThreadTag1(BusinessData businessData) {
        this.businessData = businessData;
    }

    @Override
    public void run() {
        Tagging bean = SpringContextUtil.getApplicationContext().getBean(Tagging.class);
        CountableThreadPool threadPool = new CountableThreadPool(30);
        while(!Thread.currentThread().isInterrupted()) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    List<String> fenci = bean.fenci("我要好好学校");
                    System.out.println(fenci);
                }
            });
            }
        }
    }

