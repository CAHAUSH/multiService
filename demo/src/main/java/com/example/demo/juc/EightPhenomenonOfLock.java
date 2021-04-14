package com.example.demo.juc;

import java.util.concurrent.TimeUnit;

public class EightPhenomenonOfLock {
    public static void main(String[] args) throws InterruptedException {
        //两个对象，两个调用者，有两把锁
        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread(()->{
            try {
                phone.sendSms();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(()->{
            phone2.call();
        },"B").start();

        new Thread(()->{
            phone.hello();
        },"C").start();
    }
}

class Phone{
    //synchronized 锁的对象是方法的调用者
    //两个方法用的是同一个锁，谁先拿到谁先执行
    public synchronized void sendSms() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("sendSms");
    }

    public synchronized void call(){
        System.out.println("call");
    }

    //没有同步方法，不受锁的影响
    public void hello(){
        System.out.println("hello");
    }
}
