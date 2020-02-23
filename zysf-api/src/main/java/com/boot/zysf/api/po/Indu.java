package com.boot.zysf.api.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Indu {
    @TableId(value = "id", type= IdType.AUTO)
    private Integer id;
    private String name;
    private Integer category;//几级产业，从0开始
    private String label;//产业或者企业的名称
    private Integer symbolSize;//产业20 企业10
    private boolean ignores;//一二级产业true，其他false
    private boolean flag;//一级产业false，其余true
    private Integer numbers;//产业下企业个数
    private double money;//没有为0除一级外都没有
    private double lat;//纬度
    private double lng;//经度   经纬度只有企业有
    public Indu() {
    }
}
