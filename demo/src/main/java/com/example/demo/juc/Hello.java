package com.example.demo.juc;

public class Hello {
    /*1、并发和并行
    * 并发：多个线程操作同一个资源
    * 单核cpu：多个线程交替执行
    * 并行：多个线程同时执行
    * 多核CPU：多个线程执行
    *
    *
    * 并发编程的本质：充分利用CPU的资源
    *
    *
    *2、wait / sleep区别
    * 1）来自不同的类
    * wait--》Object；  sleep---》Thread
    * 2）关于锁的释放
    * wait--》释放锁；  sleep---》不会释放锁
    * 3）使用的范围不同
    * wait--》必须在同步代码块中；  sleep---》在任何地方
    * 4）是否需要捕获异常
    *wait--》不需要；  sleep---》需要捕获
    *
    *3、LOCK锁（重点）
    *
    *
    *
    *
    *
    * */
    public static void main(String[] args) {
        //1、获取CPU核数
        //CPU密集型， IO密集型？？
        System.out.println(Runtime.getRuntime().availableProcessors());

    }
}
