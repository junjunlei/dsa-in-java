package com.jerry.juc.sync;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * @Author jerry
 * @Description 减法计数器（闭锁）
 * @Date 2022-05-10 15:02
 * @Version 1.0
 **/
public class CountDownLatchTest {
    public static void main(String[] args) {
        //减法计数器
        CountDownLatch countDownLatch = new CountDownLatch(7);
        //栅栏
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            //这里执行操作
        });
        //信号量  控制并发
        Semaphore semaphore = new Semaphore(7);
    }
}
