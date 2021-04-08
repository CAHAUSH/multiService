package com.example.demo.thread;

public class TestThread03 implements Runnable{
    private static String winner;
    @Override
    public void run() {
        for (int i = 0; i <= 1000; i++) {
            boolean flag = gameover(i);
            if(Thread.currentThread().getName().equals("兔子") && i%400==0){
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(flag){
                break;
            }
            System.out.println(Thread.currentThread().getName()+"跑了"+i+"步");
        }
    }

    private boolean gameover(int steps){
        if (winner!=null){
            return true;
        }else {
            if(steps==1000){
                winner = Thread.currentThread().getName();
                System.out.println("winner is "+winner);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        TestThread03 testThread03 = new TestThread03();
        new Thread(testThread03,"兔子").start();
        new Thread(testThread03,"乌龟").start();
    }
}
