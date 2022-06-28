package com.jerry.juc.sync;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author jerry
 * @Description 线程通信
 * @Date 2022-05-10 00:45
 * @Version 1.0
 **/
public class CommunicationThread {

    public static void main(String[] args) {

        Share share = new Share();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.incrByLock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.decrByLock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
    }
}

class Share {
    private int number = 0;

    private final Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public synchronized void incrBySync() throws InterruptedException {
        //判断 干活 通知
        while (number != 0) {
            //如果number不是0，等待
            this.wait();
        }
        //干活
        number++;
        System.out.println(Thread.currentThread().getName() + "::" + number);
        //通知其他线程
        this.notifyAll();
    }

    public synchronized void decrBySync() throws InterruptedException {
        //判断 干活 通知
        while (number != 1) {
            //如果number不是0，等待
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "::" + number);
        //通知其他线程
        this.notifyAll();
    }

    public void incrByLock() throws InterruptedException {
        lock.lock();
        //判断 干活 通知
        try {
            while (number != 0) {
                //如果number不是0，等待
                condition.await();
            }
            //干活
            number++;
            System.out.println(Thread.currentThread().getName() + "::" + number);
            //通知
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void decrByLock() throws InterruptedException {
        lock.lock();
        try {
            //判断 干活 通知
            while (number != 1) {
                //如果number不是0，等待
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "::" + number);
            //通知其他线程
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
