package com.boot.zysf.api.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode<T> {
	protected String id;
	protected String parentId;
	protected String name;
	protected List<T> children = new ArrayList<>();

	public TreeNode(String id, String parentId,String name){
		this.id = id;
		this.parentId = parentId;
		this.name = name;
	}

	public void add(T node) {
		children.add(node);
	}
}
