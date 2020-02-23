package com.boot.zysf.api.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class CompanyQualification extends Model<BusinessData> {
    @TableId(value = "id", type= IdType.AUTO)
    private Integer id;
    //企业名称
    private String empName;

    //统一社会信用代码
    private String socialCode;

    //高新技术产业
    private String advancedTechEmp;

    //高新认定主体
    private String adIdentificationBody;

    //高新技术认定日期
    private Date advancedTechDate;

    //证书编号
    private String certificateId;

    //科技型中小企业
    private String smallTechEmp;

    //地址
    private String address;

    //科技型中小企业认证日期
    private Date smallTechDate;

    //专精特新小巨人
    private String specializedNewGiant;

    //专精特新小巨人认证日期
    private Date specNewGiaDate;

    //主导产品
    private  String product;

    //产教融合型企业
    private String industryEducationRmp;

    //产教认定主体
    private String indusEduIdentificationBody;

    //产教认定日期
    private Date indusEduIdenticicationDate;

    //规模以上工业企业
    private String industrialEnterprisesAbove;

    //限额以上服务企业
    private String serviceEnterprisesAbove;

    //限额以上零住餐企业
    private String retaileEnterprisesAbove;

    //资质等级建筑企业
    private String qualificationGradeConstructionEmp;

    //500强企业
    private String top500;

    //500强排名
    private Integer rankTop500;

    public CompanyQualification() {
    }

    public CompanyQualification(String empName, String socialCode, String advancedTechEmp, String adIdentificationBody, Date advancedTechDate, String certificateId,
                                String smallTechEmp, String address, Date smallTechDate, String specializedNewGiant, Date specNewGiaDate, String product,
                                String industryEducationRmp, String indusEduIdentificationBody, Date indusEduIdenticicationDate, String industrialEnterprisesAbove,
                                String serviceEnterprisesAbove, String retaileEnterprisesAbove, String qualificationGradeConstructionEmp, String top500, Integer rankTop500) {
        this.empName = empName;
        this.socialCode = socialCode;
        this.advancedTechEmp = advancedTechEmp;
        this.adIdentificationBody = adIdentificationBody;
        this.advancedTechDate = advancedTechDate;
        this.certificateId = certificateId;
        this.smallTechEmp = smallTechEmp;
        this.address = address;
        this.smallTechDate = smallTechDate;
        this.specializedNewGiant = specializedNewGiant;
        this.specNewGiaDate = specNewGiaDate;
        this.product = product;
        this.industryEducationRmp = industryEducationRmp;
        this.indusEduIdentificationBody = indusEduIdentificationBody;
        this.indusEduIdenticicationDate = indusEduIdenticicationDate;
        this.industrialEnterprisesAbove = industrialEnterprisesAbove;
        this.serviceEnterprisesAbove = serviceEnterprisesAbove;
        this.retaileEnterprisesAbove = retaileEnterprisesAbove;
        this.qualificationGradeConstructionEmp = qualificationGradeConstructionEmp;
        this.top500 = top500;
        this.rankTop500 = rankTop500;
    }
}
