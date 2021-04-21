package com.example.demo.juc;

import java.util.concurrent.*;

/*
* 线程池3大方法
* 线程池7大参数
*ThreadPoolExecutor(int corePoolSize,  核心线程数
                              int maximumPoolSize,  最大线程数
                              long keepAliveTime,    超时没有调用就会释放
                              TimeUnit unit,         时间单位
                              BlockingQueue<Runnable> workQueue,  阻塞队列，排队请求，最大21亿
                              ThreadFactory threadFactory,   线程工厂
                              RejectedExecutionHandler handler) {   拒绝策略
* */
public class ThreadPoolTest {
    public static void main(String[] args) {
        //以下三种方式，不推荐使用，尽量不要使用； 使用ThreadPoolExecutor来代替创建
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        ExecutorService threadPoolFixed = Executors.newFixedThreadPool(5);
        ExecutorService threadPoolCached = Executors.newCachedThreadPool();
        try{
            for (int i = 0; i < 10; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"-->executing");
                });
            }

            for (int i = 0; i < 10; i++) {
                threadPoolFixed.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"-->execut fixed");
                });
            }

            for (int i = 0; i < 10; i++) {
                threadPoolCached.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"-->execut cached");
                });
            }
        }finally {
            threadPool.shutdown();
            threadPoolFixed.shutdown();
            threadPoolCached.shutdown();
        }

        //自定义线程池，手动创建线程池的方式
        //最大承载：deque + maxsize
        ExecutorService threadPoolUserDefine = new ThreadPoolExecutor(2,5,3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());//拒绝策略，共4种策略

        try {
            for (int i = 1; i <=8 ; i++) {
                threadPoolUserDefine.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"ok");
                });
            }
        }
        finally {
            threadPoolUserDefine.shutdown();
        }
    }
}

    /**线程池7大参数
     * Creates a new {@code ThreadPoolExecutor} with the given initial
     * parameters.
     *
     * @param corePoolSize the number of threads to keep in the pool, even
     *        if they are idle, unless {@code allowCoreThreadTimeOut} is set
     * @param maximumPoolSize the maximum number of threads to allow in the
     *        pool
     * @param keepAliveTime when the number of threads is greater than
     *        the core, this is the maximum time that excess idle threads
     *        will wait for new tasks before terminating.
     * @param unit the time unit for the {@code keepAliveTime} argument
     * @param workQueue the queue to use for holding tasks before they are
     *        executed.  This queue will hold only the {@code Runnable}
     *        tasks submitted by the {@code execute} method.
     * @param threadFactory the factory to use when the executor
     *        creates a new thread
     * @param handler the handler to use when execution is blocked
     *        because the thread bounds and queue capacities are reached
     * @throws IllegalArgumentException if one of the following holds:<br>
     *         {@code corePoolSize < 0}<br>
     *         {@code keepAliveTime < 0}<br>
     *         {@code maximumPoolSize <= 0}<br>
     *         {@code maximumPoolSize < corePoolSize}
     * @throws NullPointerException if {@code workQueue}
     *         or {@code threadFactory} or {@code handler} is null
     *
    public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler) {
        if (corePoolSize < 0 ||
                maximumPoolSize <= 0 ||
                maximumPoolSize < corePoolSize ||
                keepAliveTime < 0)
            throw new IllegalArgumentException();
        if (workQueue == null || threadFactory == null || handler == null)
            throw new NullPointerException();
        this.acc = System.getSecurityManager() == null ?
                null :
                AccessController.getContext();
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.workQueue = workQueue;
        this.keepAliveTime = unit.toNanos(keepAliveTime);
        this.threadFactory = threadFactory;
        this.handler = handler;
    }*/
