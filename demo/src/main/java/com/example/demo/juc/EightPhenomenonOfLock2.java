package com.example.demo.juc;

import java.util.concurrent.TimeUnit;

public class EightPhenomenonOfLock2 {
    public static void main(String[] args) throws InterruptedException {
        //synchronized 锁的对象是方法的调用者
        //静态方法类加载时就存在了，只有一份
        // 锁的是Class ，会先调用sendmsg后调用call
        Phone3 phone = new Phone3();
        Phone3 phone2 = new Phone3();
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

class Phone3{
    //synchronized 锁的对象是方法的调用者
    //静态方法类加载时就存在了，只有一份
    // 锁的是Class
    public synchronized static void sendSms() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("sendSms");
    }

    public synchronized static void call(){
        System.out.println("call");
    }

    //没有同步方法，不受锁的影响
    public void hello(){
        System.out.println("hello");
    }
}
