package com.boot.zysf.api.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class IndustroyTupu {
    @TableId(value="id", type= IdType.AUTO)
    private Integer id;
    private Integer parentId;
    private String  name;
    private String  relation;
    private String code;

    public IndustroyTupu() {
    }
    public static final String ID = "id";
    public static final String PARENT_ID = "parent_id";
    public static final String NAME = "name";
    public static final String RELATION = "relation";
    public static final String CODE = "code";
}
