package com.boot.zysf.api.po.v0;

import com.boot.zysf.api.po.RenCai;
import com.boot.zysf.api.po.TreeNode1;
import com.boot.zysf.api.po.ZhuanLi;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

@Data
@Accessors(chain = true)
public class RelationMap {
    //企业数量
    private String companyNum;

    //专利列表 inventor-name title
    private List<ZhuanLi> zhuanli;

    //人才列表 name--field
    private List<RenCai> rencai;

    //当前产业名称
    private String name;

    //当前产业id
    private String id;

    ////产业树
    private List<TreeNode1> tree;

    //企业信息数组
    private List<String> companyInfos;

    public RelationMap(String companyNum, List<ZhuanLi> zhuan, List<RenCai> ren, String name, String id, List<TreeNode1> tree, List<String> companyInfos) {
        this.companyNum = companyNum;
        this.zhuanli = zhuan;
        this.rencai = ren;
        this.name = name;
        this.id = id;
        this.tree = tree;
        this.companyInfos = companyInfos;
    }
}
