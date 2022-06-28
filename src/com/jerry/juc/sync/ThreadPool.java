package com.jerry.juc.sync;

import java.util.concurrent.*;

/**
 * @Author jerry
 * @Description 线程池
 * @Date 2022-05-10 16:31
 * @Version 1.0
 **/
public class ThreadPool {
    public static void main(String[] args) {
        //固定数线程池,类似银行n个窗口办理业务
        //ExecutorService threadPool = Executors.newFixedThreadPool(5);

        //一池一个工作线程，类似一个银行有一个受理窗口
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();

        //根据需要创建新线程 可扩容
        ExecutorService threadPool = Executors.newCachedThreadPool();
        /**
         * Executors工具类底层还是使用的 new ThreadPoolExecutor()创建的线程
         *    1.newFixedThreadPool 方法 corePoolSize和 maximumPoolSize设置的相同，也就是传入的固定参数，
         *    队列使用的
         *    LinkedBlockingQueue，所以允许请求的队列最大长度为Integer.MAX_VALUE，可能会堆积大量请求
         *    导致OOM
         *
         *    2.newSingleThreadExecutor 方法 corePoolSize和 maximumPoolSize为1，队列使用的
         *    LinkedBlockingQueue，所以允许请求的队列最大长度为Integer.MAX_VALUE，可能会堆积大量请求
         *    导致OOM
         *
         *    3.newCachedThreadPool 方法corePoolSize为0 和corePoolSize为Integer.MAX_VALUE,也就是说
         *    会创建大量线程，导致OOM 阻塞队列为SynchronousQueue
         *
         */

        //自定义线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,
                5,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(500),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        //分支合并框架
        ForkJoinPool pool=new ForkJoinPool();
        //forkJoinPool.
    }
}
