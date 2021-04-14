package com.example.demo.thread;

public class TestThreadDeadlock {
    public static void main(String[] args) {
        Makeup makeup1 = new Makeup(0);
        Makeup makeup2 = new Makeup(1);
        new Thread(makeup1,"董卿").start();
        new Thread(makeup2,"李思思").start();
    }
}

class Mirror{

}

class Lipstick{

}

class Makeup implements Runnable{
    /*static Mirror mirror = new Mirror();
    static Lipstick lipstick = new Lipstick();*/
    static String mirror="我是镜子";
    static String lipstick="我是口红";
    private int choice = 0;
    Makeup(int choice){
        this.choice = choice;
    }

    @Override
    public void run() {
        if(choice == 0) {
            //1.先获取口红
            synchronized (lipstick) {
                System.out.println(Thread.currentThread().getName() + "获取口红");
                try {
                    Thread.sleep(1000);
                    synchronized (mirror) {
                        System.out.println(Thread.currentThread().getName() + "获取镜子");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }else{
            //1.先获取镜子
            synchronized (mirror) {
                System.out.println(Thread.currentThread().getName() + "获取镜子");
                try {
                    //Thread.sleep(1000);
                    synchronized (lipstick) {
                        System.out.println(Thread.currentThread().getName() + "获取口红");
                    }
                } catch (Exception e/*InterruptedException e*/) {
                    e.printStackTrace();
                }
            }
        }
    }
}
