package com.boot.zysf.api.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode1<T> {
    protected String id;
    protected String parentId;
    protected String name;
    protected String relation;
    protected String code;
    protected List<T> children = new ArrayList<>();

    public TreeNode1(String id, String parentId, String name, String relation,String code) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.relation = relation;
        this.code = code;
    }
    public void add(T node) {
        children.add(node);
    }
}
