package com.example.demo.juc;

import java.util.concurrent.CountDownLatch;

//计数器（减法）
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        //必须要执行指定任务的时候来使用
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i <6 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"--》GO OUT");
                countDownLatch.countDown();//数量减一
            }).start();
        }
        countDownLatch.await();//等待计数器归零，再向下执行后续代码
        System.out.println("关门");
    }
}
