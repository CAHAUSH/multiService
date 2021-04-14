package com.example.demo.juc;

import java.util.concurrent.TimeUnit;

public class EightPhenomenonOfLock3 {
    public static void main(String[] args) throws InterruptedException {
        //
        Phone4 phone = new Phone4();
        new Thread(()->{
            try {
                phone.sendSms();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(()->{
            phone.call();
        },"B").start();

    }
}

class Phone4{
    //锁的是Class模板
    public synchronized static void sendSms() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("sendSms");
    }

    //锁的是调用者（对象）
    public synchronized void call(){
        System.out.println("call");
    }

}
