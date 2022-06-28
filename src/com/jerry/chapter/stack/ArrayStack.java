package com.jerry.chapter.stack;

/**
 * @Author jerry
 * @Description 顺序栈
 * @Date 2022-06-05 13:41
 * @Version 1.0
 **/
public class ArrayStack {
    private int[] items;
    private int capacity;
    private int count;


    public ArrayStack(int capacity) {
        this.items = new int[capacity];
        this.capacity = capacity;
        this.count = 0;
    }

    public boolean push(int item) {
        if (count == capacity) {
            return false;
        }
        items[++count] = item;
        return true;
    }

    public Integer pop() {
        if (count == 0) {
            return null;
        }
        return items[--count];
    }
}
