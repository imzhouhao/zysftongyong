package com.boot.zysf.api.util;




import com.boot.zysf.api.po.TreeNode;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 */

public class TreeUtil {
	/**
	 * 两层循环实现建树
	 *
	 * @param treeNodes 传入的树节点列表
	 * @return
	 */
	public static  <T extends TreeNode> List<T> build(List<T> treeNodes, Object root) {

		List<T> trees = new ArrayList<>();

		for (T treeNode : treeNodes) {

			if (root.equals(treeNode.getParentId())) {
				trees.add(treeNode);
			}

			for (T it : treeNodes) {
				if (it.getParentId().equals(treeNode.getId())) {
					if (treeNode.getChildren() == null) {
						treeNode.setChildren(new ArrayList<>());
					}
					treeNode.add(it);
				}
			}
		}
		return trees;
	}

	/**
	 * 使用递归方法建树
	 *
	 * @param treeNodes
	 * @return
	 */
	public static <T extends TreeNode> List<T> buildByRecursive(List<T> treeNodes, Object root) {
		List<T> trees = new ArrayList<>();
		for (T treeNode : treeNodes) {
			if (root.equals(treeNode.getParentId())) {
				trees.add(findChildren(treeNode, treeNodes));
			}
		}
		return trees;
	}

	/**
	 * 递归查找子节点
	 *
	 * @param treeNodes
	 * @return
	 */
	public static <T extends TreeNode> T findChildren(T treeNode, List<T> treeNodes) {
		for (T it : treeNodes) {
			if (treeNode.getId().equals(it.getParentId())) {
				if (treeNode.getChildren() == null) {
					treeNode.setChildren(new ArrayList<>());
				}
				treeNode.add(findChildren(it, treeNodes));
			}
		}
		return treeNode;
	}

	/**
	 * 获取树结构中的全部的id
	 * @param orgIds
	 * @param orgTrees
	 */
	public static void getAllIds(List<String> orgIds, List<? extends TreeNode> orgTrees){
		if(orgIds == null) {
			orgIds = new ArrayList<>();
		}
		for (TreeNode orgTree : orgTrees) {
			orgIds.add(orgTree.getId());
			List<TreeNode> children = orgTree.getChildren();
			if(children != null && children.size() > 0) {
				getAllIds(orgIds, children);
			}
		}
	}

	/**
	 * 没有根节点的时候构建树
	 * @param treeNodes
	 * @param <T>
	 * @return
	 */
	public static <T extends TreeNode> List<T> buildNoRoot(List<T> treeNodes) {
		if(treeNodes == null || treeNodes.size() == 0) {
			return null;
		}

		List<T> cloneData = new ArrayList<>(treeNodes), treeList = new ArrayList<>();
		Map<String, T> idMap = new HashMap<>();
		//生成一个以id为键的对象
		for (T datum : cloneData) {
			idMap.put(datum.getId(),datum);
		}
		for (T node : cloneData) {
			//如果aValParent存在；就说明当前的node是parentNode的孩子
			T parentNode = idMap.get(node.getParentId());
			if (parentNode != null) {
				List<TreeNode> children = parentNode.getChildren();
				if (children == null) {
					children = new ArrayList<>();
				}
				children.add(node);
			} else {
				treeList.add(node);
			}
		}
		return treeList;
	}

	public static void main(String[] args) {
		TreeNode node0 = new TreeNode("1","0","aa");
		TreeNode node1 = new TreeNode("2","1","bb");
		TreeNode node2 = new TreeNode("3","1","cc");
		TreeNode node3 = new TreeNode("5","2","dd");
		TreeNode node4 = new TreeNode("4","5","ee");
		List<TreeNode> treeNodes = Arrays.asList(node0, node1, node2, node3, node4);
		List<TreeNode> treeNodes1 = buildNoRoot(treeNodes);
		for (TreeNode node : treeNodes1) {
			System.out.println(node);
		}
	}
}
