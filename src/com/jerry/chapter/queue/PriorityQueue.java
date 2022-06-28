package com.jerry.chapter.queue;

/**
 * @Author jerry
 * @Description 优先级队列
 * @Date 2022-06-05 13:57
 * @Version 1.0
 **/
public class PriorityQueue {
    private int items[];
    private int capacity;
    private int count;

    public PriorityQueue(int capacity) {
        this.capacity = capacity;
        this.items = new int[capacity];
        this.count = 0;
    }

    public void enqueue(int item) {
        if (count == 0) {
            items[count++] = item;
        } else {
            int i;
            for (i = count - 1; i >= 0; i--) {
                if (item > items[i]) {
                    //移动
                    items[i + 1] = items[i];
                } else {
                    break;
                }
            }
            items[i + 1] = item;
            count++;
        }
    }

    public int dequeue() {
        return items[--count];
    }
}
