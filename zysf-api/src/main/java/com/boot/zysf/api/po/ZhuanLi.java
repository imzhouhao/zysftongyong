package com.boot.zysf.api.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)

public class ZhuanLi {
    @TableId(value = "id", type= IdType.AUTO)
    private Integer id;

    private String empName;

    private String empId;

    //题目
    private String title;

    //发明者
    private String inventor;

    public ZhuanLi() {
    }
}
