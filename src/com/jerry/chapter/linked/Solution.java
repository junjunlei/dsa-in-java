package com.jerry.chapter.linked;

import org.junit.Test;

/**
 * @Author jerry
 * @Description TODO
 * @Date 2022-04-11 16:56
 * @Version 1.0
 **/
public class Solution {


    @Test
    public void testDeleteNode() {
        Node node = new Node(4);
        Node node1 = new Node(5);
        Node node2 = new Node(1);
        Node node3 = new Node(9);
        node.next = node1;
        node1.next = node2;
        node2.next = node3;
//        Node node4 = deleteNode(node, 1);
//        System.out.println(node4);
        insertAtHead(node,new Node(11));
        //0 1 0 0 0 1  100010
        System.out.println(1%2);
        System.out.println(1/2);
    }

    public Node insertAtHead(Node head, Node newNode) {
        newNode.next = head;
        head = newNode;
        return head;
    }


    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param head ListNode类
     * @param val  int整型
     * @return ListNode类
     */
    public Node deleteNode(Node head, int val) {

        if (head == null) {
            return null;
        }

        if (head.val == val) {
            return head.next;
        }
        //构造前置节点，并从第二个开始运行
        Node cur = head.next;
        Node pre = head;
        // write code here
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            }
            pre = cur;
            cur = cur.next;
        }
        return head;
    }
}
