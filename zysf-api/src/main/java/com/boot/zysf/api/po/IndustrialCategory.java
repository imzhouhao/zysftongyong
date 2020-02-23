package com.boot.zysf.api.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 产业分类信息表
 * </p>
 *
 * @author zzq
 * @since 2019-07-16
 */
@Data
@Accessors(chain = true)
public class IndustrialCategory extends Model<IndustrialCategory> {

    private static final long serialVersionUID = 1L;

    @TableId(value="id", type= IdType.AUTO)
private Integer id;
/**
 * 父id
 */
private Integer parentId;
/**
 * 名称
 */
private String name;
    /**
     * 分类代码
     */
private String code;
    /**
 * 创建时间
 */
private LocalDateTime ctime;
/**
 * 更新时间
 */
private LocalDateTime utime;

//产业
private String indu;

    public static final String ID = "id";
    public static final String PARENT_ID = "parent_id";
    public static final String NAME = "name";
    public static final String CTIME = "ctime";
    public static final String UTIME = "utime";
    public static final String CODE = "code";
    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
