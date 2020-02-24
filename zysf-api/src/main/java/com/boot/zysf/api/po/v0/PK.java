package com.boot.zysf.api.po.v0;

import com.boot.zysf.api.po.InduAccess.ChanYePingGu;
import com.boot.zysf.api.po.TreeNode1;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

@Data
@Accessors(chain = true)
public class PK {
    //评估信息
    private Map<String, ChanYePingGu> pingGu;

    //优劣势
    private Map<String,Map<String,String>> zongHe;

    //上下游关系图谱
    private  List<TreeNode1> trees;

    public PK(Map<String, ChanYePingGu> pingGu, Map<String, Map<String, String>> zongHe, List<TreeNode1> trees) {
        this.pingGu = pingGu;
        this.zongHe = zongHe;
        this.trees = trees;
    }
}
