package com.jerry.juc.sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author jerry
 * @Description 1.创建资源类 定义属性和方法
 * @Date 2022-05-10 00:11
 * @Version 1.0
 **/
public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                ticket.saleBySync();
                //ticket.saleByLock();
            }
        }, "售票员1").start();
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                ticket.saleBySync();
                //ticket.saleByLock();
            }
        }, "售票员2").start();
        new Thread(() -> {
            for (int i = 0; i < 30; i++) {
                ticket.saleBySync();
                //ticket.saleByLock();
            }
        }, "售票员3").start();
    }
}

class Ticket {

    //Lock和synchronized不同点

    //1.Lock是一个接口，而synchronized是java关键字，是内置的
    //2.synchronized在发生异常的时候，会自动释放线程占有的锁，因此不会导致死锁
    //而Lock在发生异常的时候，如果没有主动通过unLock()去释放锁，则很可能造成死锁现象
    //3.Lock 可以让等待锁的线程响应中断，而synchronized却不行，使用synchronized时，等待的线程会一直等待下去，不能够响应中断
    //4.Lock可以知道有没有成功获取到锁，而synchronized不行


    //票数
    private int number = 30;

    private final Lock lock = new ReentrantLock();

    //卖票
    public synchronized void saleBySync() {
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出第" + (number--) + "还剩下" + number);
        }
    }

    public void saleByLock() {
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出第" + (number--) + "还剩下" + number);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
