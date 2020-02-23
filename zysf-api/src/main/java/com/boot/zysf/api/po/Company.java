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
 * 企业信息
 * </p>
 *
 * @author zzq
 * @since 2019-07-10
 */
@Data
@Accessors(chain = true)
public class Company extends Model<Company> {

    private static final long serialVersionUID = 1L;

/**
 * 注解
 */
@TableId(value="id", type= IdType.AUTO)
private Integer id;
/**
 * 企业名称
 */
private String name;
/**
 * 信用编码
 */
private String code;
/**
 * 企业地址
 */
private String address;
/**
 * 经度
 */
private Double lng;
/**
 * 纬度
 */
private Double lat;



private LocalDateTime ctime;
private LocalDateTime utime;

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String CODE = "code";
    public static final String ADDRESS = "address";
    public static final String LNG = "lng";
    public static final String LAT = "lat";
    public static final String CTIME = "ctime";
    public static final String UTIME = "utime";
    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
