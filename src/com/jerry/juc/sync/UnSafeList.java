package com.jerry.juc.sync;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author jerry
 * @Description List的线程安全
 * @Date 2022-05-10 13:30
 * @Version 1.0
 **/
public class UnSafeList {
    public static void main(String[] args) {
        //CopyOnWriteArrayList原理
        //1.加锁
        //2.数组拷贝：保证数组内存地址被修改 触发volatile的可见性，其他线程立马知道数组已经被修改了
        //3.新数组上进行操作，并把新数组赋值给原来的数组
        //4.解锁


        //ConcurrentModificationException
        List<String> list = new ArrayList<>();
        //Vector 解决
        Vector<String> vector = new Vector<>();
        //Collections.synchronizedList()解决
        List<Object> syncList = Collections.synchronizedList(new ArrayList<>());
        //CopyOnWriteArrayList解决
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                copyOnWriteArrayList.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(copyOnWriteArrayList);
            }, String.valueOf(i)).start();
        }
    }
}
