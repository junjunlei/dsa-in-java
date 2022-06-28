package com.jerry.chapter.linked;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author jerry
 * @Description 请你设计并实现一个满足 LRU (最近最少使用) 缓存 约束的数据结构。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以 正整数 作为容量capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value)如果关键字key 已经存在，则变更其数据值value ；如果不存在，则向缓存中插入该组key-value 。
 * 如果插入操作导致关键字数量超过capacity ，则应该 逐出 最久未使用的关键字。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lru-cache
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * <p>
 * 思路： map缓存节点，快速映射节点在链表中到位置，双向链表头插存节点，越靠近头部是最近使用
 * @Date 2022-04-24 14:35
 * @Version 1.0
 **/
public class LRUCache {

    class DNode {
        public int key;
        public int value;
        DNode next;
        DNode pre;

        DNode() {
        }

        DNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;

    private int size;

    private DNode head;

    private DNode tail;

    private Map<Integer, DNode> cache = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new DNode();
        tail = new DNode();
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        DNode dNode = cache.get(key);
        if (dNode == null) {
            return -1;
        }
        //如果存在，则移动到链表头部
        moveToHead(dNode);
        return dNode.value;
    }

    public void put(int key, int value) {
        DNode dNode = cache.get(key);
        if (dNode == null) {
            //不存在，则新建节点
            DNode newNode = new DNode(key, value);
            //加入缓存
            cache.put(key, newNode);
            //添加到链表头部
            addToHead(newNode);
            ++size;
            if (size > capacity) {
                //超出容量，删除尾部节点
                DNode tail = removeTail();
                //删除缓存
                cache.remove(tail.key);
                --size;
            }
        } else {
            //存在 更新value
            dNode.value = value;
            //并移动到头部
            moveToHead(dNode);
        }
    }

    private DNode removeTail() {
        DNode dNode = tail.pre;
        removeNode(dNode);
        return dNode;
    }

    private void addToHead(DNode newNode) {
        newNode.pre = head;
        newNode.next = head.next;
        head.next.pre = newNode;
        head.next = newNode;
    }

    private void moveToHead(DNode dNode) {
        //删除节点
        removeNode(dNode);
        //头部新增节点
        addToHead(dNode);
    }

    private void removeNode(DNode dNode) {
        dNode.pre.next = dNode.next;
        dNode.next.pre = dNode.pre;
    }
}
