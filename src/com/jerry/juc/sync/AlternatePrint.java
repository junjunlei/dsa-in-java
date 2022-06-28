package com.jerry.juc.sync;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author jerry
 * @Description 多线程顺序调用 实现A>B>C
 * 三个线程启动 A打印5次 B打印10次 C打印15次
 * @Date 2022-05-10 13:12
 * @Version 1.0
 **/
public class AlternatePrint {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    shareResource.printA(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    shareResource.printB(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    shareResource.printC(15);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"CC").start();
    }
}

class ShareResource {
    private int number = 1;

    private final Lock lock = new ReentrantLock();
    private final Condition condition1 = lock.newCondition();
    private final Condition condition2 = lock.newCondition();
    private final Condition condition3 = lock.newCondition();

    public void printA(int loop) throws InterruptedException {
        lock.lock();
        try {
            while (number != 1) {
                condition1.await();
            }
            for (int i = 0; i < loop; i++) {
                System.out.println(Thread.currentThread().getName() + " : " + "AA");
            }
            number = 2;
            condition2.signal();
        } finally {
            lock.unlock();
        }
    }

    public void printB(int loop) throws InterruptedException {
        lock.lock();
        try {
            while (number != 2) {
                condition2.await();
            }
            for (int i = 0; i < loop; i++) {
                System.out.println(Thread.currentThread().getName() + " : " + "BB");
            }
            number = 3;
            condition3.signal();
        } finally {
            lock.unlock();
        }
    }

    public void printC(int loop) throws InterruptedException {
        lock.lock();
        try {
            while (number != 3) {
                condition3.await();
            }
            for (int i = 0; i < loop; i++) {
                System.out.println(Thread.currentThread().getName() + " : " + "CC");
            }
            number = 1;
            condition1.signal();
        } finally {
            lock.unlock();
        }
    }
}
