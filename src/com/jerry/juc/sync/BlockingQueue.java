package com.jerry.juc.sync;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Author jerry
 * @Description 阻塞队列
 * @Date 2022-05-10 16:23
 * @Version 1.0
 **/
public class BlockingQueue {
    public static void main(String[] args) {
        //新增 add()     offer()    put()
        //移除 remove()   poll()    take()
        //查看 element()   peek()

        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        //arrayBlockingQueue.take()
    }
}
