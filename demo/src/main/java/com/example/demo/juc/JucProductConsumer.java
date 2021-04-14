package com.example.demo.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class JucProductConsumer {
    public static void main(String[] args) {
        DataContainer1 data = new DataContainer1();
        new Thread(()->{
            try {
                for (int i = 0; i < 10; i++) {
                    data.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();
        new Thread(()->{
            try {
                for (int i = 0; i < 10; i++) {
                    data.decrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();
        new Thread(()->{
            try {
                for (int i = 0; i < 10; i++) {
                    data.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"C").start();
        new Thread(()->{
            try {
                for (int i = 0; i < 10; i++) {
                    data.decrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"D").start();
    }
}

class DataContainer1{
    private int num = 0;
    private int waitnum = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    public void increment() throws InterruptedException {
        /*condition.await();//等待
        condition.signalAll();//唤醒*/

        //if会造成虚假唤醒，应该使用while代替
        //if(num != 0){
        lock.lock();
        try{
            while (num != 0){
                //线程等待
                //System.out.println(Thread.currentThread().getName()+"-->等待:"+(++waitnum));
                condition.await();
            }
            num++;
            System.out.println(Thread.currentThread().getName()+"-->"+num);
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }
    public void decrement() throws InterruptedException {
        lock.lock();
        try{
            while (num == 0){
                //线程等待
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName()+"-->"+num);
            //Thread.sleep(3000);
            condition.signalAll();
        }finally {
            lock.unlock();
        }

    }
}
