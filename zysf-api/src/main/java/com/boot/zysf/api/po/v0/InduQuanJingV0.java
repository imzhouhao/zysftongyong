package com.boot.zysf.api.po.v0;

import com.boot.zysf.api.po.NianData;
import com.boot.zysf.api.po.TreeNode;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

//产业全景
@Data
@Accessors(chain = true)
public class InduQuanJingV0 {

    //产业名称
    private String name;

    //产业id
    private String id;

    //年度数据
    private List<NianData> nianData;

    //产业树
    private List<TreeNode> tree;

    public InduQuanJingV0(String name, String id, List<NianData> nianData, List<TreeNode> tree) {
        this.name = name;
        this.id = id;
        this.nianData = nianData;
        this.tree = tree;
    }
}
