package com.boot.zysf.api.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RenCai {
    @TableId(value = "id", type= IdType.AUTO)
    private Integer id;

    private String empName;

    private String empId;

    //姓名
    private String name;

    //领域
    private String field;

    public RenCai() {
    }
}
