package com.jerry.juc.sync;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author jerry
 * @Description 读写锁: 读锁：共享锁  写锁：独占锁
 * @Date 2022-05-10 15:31
 * @Version 1.0
 **/
public class ReadWriteLock {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 0; i < 5; i++) {
            final String num = String.valueOf(i);
            new Thread(() -> {
                myCache.put(num, num);
            }, String.valueOf(i)).start();
        }

        for (int i = 0; i < 5; i++) {
            final String num = String.valueOf(i);
            new Thread(() -> {
                myCache.get(num);
            }, String.valueOf(i)).start();
        }
    }
}

class MyCache {
    //synchronized和ReentrantLock是独占锁
    //ReentrantReadWriteLock 读写锁 ：一个资源可以被多个线程访问，或者可以被一个线程写，但是不能同时存在读写，读写互斥，读读共享
    //缺点：容易造成锁饥饿，一直读，没有写
    //读的时候不能写，只读完成才能写； 写操作可以读

    //锁降级
    //获取写锁===》获取读锁====》释放写锁===》释放读锁

    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private volatile Map<String, Object> map = new HashMap<>();

    public void put(String key, Object value) {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 正在写操作 " + key);
            TimeUnit.SECONDS.sleep(3);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + " 写完了 " + key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public Object get(String key) {
        readWriteLock.readLock().lock();
        Object result = null;
        try {
            System.out.println(Thread.currentThread().getName() + " 正在读操作 " + key);
            TimeUnit.SECONDS.sleep(3);
            result = map.get(key);
            System.out.println(Thread.currentThread().getName() + " 读完了 " + key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
        return result;
    }
}
