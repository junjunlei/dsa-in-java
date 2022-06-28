package com.jerry.juc.sync;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author jerry
 * @Description Set的线程安全
 * @Date 2022-05-10 14:08
 * @Version 1.0
 **/
public class UnSafeSet{
    public static void main(String[] args) {
        //ConcurrentModificationException
        //HashSet底层是HashMap
        Set<String> set=new HashSet<>();
        //解决方案
        //CopyOnWriteArraySet
        CopyOnWriteArraySet<String> copyOnWriteArraySet = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 30; i++) {
            copyOnWriteArraySet.add(UUID.randomUUID().toString().substring(0,8));
            System.out.println(copyOnWriteArraySet);
        }
    }
}
