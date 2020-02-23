package com.boot.zysf.api.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boot.zysf.api.po.Region;
import com.boot.zysf.api.service.RegionService;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

@UtilityClass
@Slf4j
public class StringUtil {
@Autowired
    RegionService regionService;
    public boolean isNullOrBlank(String s){
        if(s == null || "".equals(s.trim()) ){
            return true;
        }
        return false;
    }

}
