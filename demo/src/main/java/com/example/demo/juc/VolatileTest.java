package com.example.demo.juc;

import java.util.concurrent.TimeUnit;
/*
* Volatile
* 1、可见性
* 2、不保证原子性
* */
public class VolatileTest {
    //volatile 使得num的修改对线程A是可见的，可以结束死循环
    private volatile static int num=0;
    public static void main(String[] args) throws InterruptedException {
        //线程不知道主线程已经把 num=1；因此当前线程不会停止执行。死循环
        //对 主内存的 num 是不可见的。
        new Thread(()->{
            while (num==0){

            }
            System.out.println("----------"+num);
        },"A").start();

        TimeUnit.SECONDS.sleep(1);

        num=1;
        System.out.println(num);
    }
}
