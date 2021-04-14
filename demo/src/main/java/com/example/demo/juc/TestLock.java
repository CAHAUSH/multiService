package com.example.demo.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock {
    public static void main(String[] args) {
        //多线程操作同一个类
        Ticket ticket=new Ticket();
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }).start();
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }).start();
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }).start();
    }
}

class Ticket{
    private int num = 30;
    Lock lock = new ReentrantLock();
    /*1.公平锁
    *
    * 2.非公平锁，可以插队（默认）
    * */

    public void sale(){
        lock.lock();
        try{
            if(num>0)
                System.out.println(Thread.currentThread().getName()+"卖出了"+num--+"票");
        }finally {
            lock.unlock();
        }
    }
}
