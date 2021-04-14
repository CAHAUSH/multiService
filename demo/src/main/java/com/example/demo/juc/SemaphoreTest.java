package com.example.demo.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

//信号量。并发限流，限制最大的线程数
public class SemaphoreTest
{
    public static void main(String[] args) {
        //按列：停车位， 限流：3个线程。（6个车抢3个车位）
        Semaphore semaphore=new Semaphore(3);
        for (int i = 1; i < 6; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"-->抢到了车位");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName()+"-->离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            }).start();
        }
    }
}
