package com.boot.zysf.api.Runner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boot.zysf.api.config.CountableThreadPool;
import com.boot.zysf.api.po.BusinessData;
import com.boot.zysf.api.service.BusinessDataService;
import com.boot.zysf.api.util.AddressUntils;
import com.boot.zysf.api.util.BusinessDateRead;
import com.boot.zysf.api.util.IndustryRead;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class FileReadRunner implements CommandLineRunner {
    @Autowired
    BusinessDateRead businessDateRead;
    @Autowired
    AddressUntils addressUntils;
    @Autowired
    BusinessDataService businessDataService;
    @Autowired
    IndustryRead industryRead;
    //企业信息读取
    public Integer addIntoQiYe(List<BusinessData> businessDataList) {
        CountableThreadPool threadPool = new CountableThreadPool(30);
        while (!Thread.currentThread().isInterrupted()) {
            for (int i = 0; i < businessDataList.size(); i++) {
                int finalI = i;
                threadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        String lat = null;
                        String lng = null;
                        String add3 = null;
                        BusinessData businessData = businessDataList.get(finalI);
                        if (businessDataList.get(finalI) != null) {
                            List<String> location = addressUntils.getLocation(businessData.getAddress());
                            if (location.size() != 0) {
                                lat = location.get(0);
                                lng = location.get(1);
                                add3 = addressUntils.getAdd(lng, lat);
                            }
                            businessData.setAdd3(add3);
                            businessData.setLat(lat);
                            businessData.setLng(lng);
                           businessDataService.saveOrUpdate(businessData);
                        }
                    }
                });

                }
            break;
            }
        return businessDataList.size();
        }

     //行业信息读取
     public Integer addIntoHangYe(MultipartFile file) {
         final Integer[] count = {-1};
         CountableThreadPool threadPool = new CountableThreadPool(30);

         while (!Thread.currentThread().isInterrupted()) {
             threadPool.execute(new Runnable() {
                 @Override
                 public void run() {
                    count[0] = industryRead.getExcelInfo(file);
                 }
             });
             break;
         }
         return count[0];
     }
    @Override
    public void run(String... args) throws Exception {

    }
}
