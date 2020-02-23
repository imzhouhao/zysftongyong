package com.boot.zysf.api.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Region {
    @TableId(value = "id", type= IdType.AUTO)
     private Integer id;

    private  String name;

    private  Integer pid;

    private  String sname;

    private  Integer level;

    private  String citycode;

    private  String yzcode;

    private  String mername;

    private  Float Lng;

    private  Float Lat;

    private String pinyin;

    public Region() {
    }
}
