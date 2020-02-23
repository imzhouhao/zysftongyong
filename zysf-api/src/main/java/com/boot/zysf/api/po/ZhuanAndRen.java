package com.boot.zysf.api.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class ZhuanAndRen {
    @TableId(value = "id", type= IdType.AUTO)
    private Integer id;

    private String empName;

    private String empId;

    //专利数
    private Integer zhuanNum;

    //人才数
    private Integer renNum;

    public ZhuanAndRen() {
    }
}
