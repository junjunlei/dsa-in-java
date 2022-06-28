package com.jerry.chapter.tree;

import java.util.LinkedList;

/**
 * @Author jerry
 * @Description 树节点
 * @Date 2022-05-08 13:24
 * @Version 1.0
 **/
public class TreeNode {

    public int val;

    public TreeNode left;

    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
        LinkedList<Object> objects = new LinkedList<>();
    }
}
