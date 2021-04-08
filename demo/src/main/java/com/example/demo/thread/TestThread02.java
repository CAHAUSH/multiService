package com.example.demo.thread;

import java.util.TreeMap;

/*多个线程同时操作一个对象
  抢火车票
* 存在问题：
* 1）多个人同时抢到了同一张票。多个线程访问同一个资源，线程不安全。
*
* */
public class TestThread02 implements Runnable{
    private int ticketNums = 10;
    @Override
    public void run() {
        while (true){
            if(ticketNums<=0){
                break;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"-->拿到了第"+ticketNums--+"张票");
        }
    }

    public static void main(String[] args) {
        TestThread02 t1 = new TestThread02();
        new Thread(t1,"小明").start();
        new Thread(t1,"小红").start();
        new Thread(t1,"大兵").start();
    }
}
