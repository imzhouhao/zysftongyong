package com.boot.zysf.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.boot.zysf.api.po.Indu;

import java.util.List;

public interface InduService extends IService<Indu> {
    Indu findByLabel(String label);


}
