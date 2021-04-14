package com.example.demo.juc;

public class ProductConsumer {
    public static void main(String[] args) {
        DataContainer data = new DataContainer();
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

class DataContainer{
    private int num = 0;
    private int waitnum = 0;
    public synchronized void increment() throws InterruptedException {
        //if会造成虚假唤醒，应该使用while代替
        //if(num != 0){
        while (num != 0){
            //线程等待
            System.out.println(Thread.currentThread().getName()+"-->等待:"+(++waitnum));
            this.wait();
        }
        num++;
        System.out.println(Thread.currentThread().getName()+"-->"+num);
        this.notifyAll();
    }
    public synchronized void decrement() throws InterruptedException {
        while (num == 0){
            //线程等待
            this.wait();
        }
        num--;
        System.out.println(Thread.currentThread().getName()+"-->"+num);
        //Thread.sleep(3000);
        this.notifyAll();
    }
}
