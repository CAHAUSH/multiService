package com.example.demo.juc;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

//同步队列，没有容量，只能存储一个元素;; put了一个元素，必须先take拿出来，否则无法继续put元素
public class SynchronousQueueTest {
    public static void main(String[] args) {
        SynchronousQueue<String> objects = new SynchronousQueue<String>();
        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"put1");
                objects.put("1");
                System.out.println(Thread.currentThread().getName()+"put2");
                objects.put("2");
                System.out.println(Thread.currentThread().getName()+"put3");
                objects.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"T1").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName()+objects.take());
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName()+objects.take());
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName()+objects.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"T1").start();
    }
}
