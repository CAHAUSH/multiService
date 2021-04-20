package com.example.demo.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/*
* ReentrantReadWriteLock
* 读写锁
* 写 - 写 互斥
* 读 - 写 互斥
* 读 - 读 共享
* */
public class ReadWriteLockTest {
    public static void main(String[] args) {
        MyCaheLock myCahe = new MyCaheLock();
        for (int i = 1; i <= 5; i++) {
            final int temp=i;
            new Thread(()->{
                myCahe.put(temp+"",temp+"");
            },String.valueOf(i)).start();
        }
        for (int i = 1; i <= 5; i++) {
            final int temp=i;
            new Thread(()->{
                myCahe.get(temp+"");
            },String.valueOf(i)).start();
        }
    }
}

/*
 * 自定义缓存
 * */
class MyCahe{
    private volatile Map<String,Object> map = new HashMap<>();
    //读
    public void get(String key){
        System.out.println(Thread.currentThread().getName()+"读取"+key);
        map.get(key);
        System.out.println(Thread.currentThread().getName()+"读取完毕");
    }

    //写
    public void put(String key,Object value){
        System.out.println(Thread.currentThread().getName()+"写入"+key);
        map.put(key,value);
        System.out.println(Thread.currentThread().getName()+"写入完毕");
    }
}

/*
 * 自定义缓存
 * */
class MyCaheLock{
    private volatile Map<String,Object> map = new HashMap<>();
    //普通锁
    private Lock lock =new ReentrantLock();
    //读写锁
    private ReadWriteLock readWriteLock=new ReentrantReadWriteLock();
    //读
    public void get(String key){
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"读取"+key);
            map.get(key);
            System.out.println(Thread.currentThread().getName()+"读取完毕");
        }finally {
            readWriteLock.readLock().unlock();
        }
    }

    //写，写入的时候只有一个县城写
    public void put(String key,Object value){
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"写入"+key);
            map.put(key,value);
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName()+"写入完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }
}

