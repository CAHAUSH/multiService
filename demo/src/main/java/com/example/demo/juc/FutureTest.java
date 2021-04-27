package com.example.demo.juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/*
* 异步调用
* */
public class FutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //没有返回值的异步回调
        CompletableFuture<Void> future = CompletableFuture.runAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"runAsyn=>void");
        });

        System.out.println("00000000");

        future.get();

        //没有返回值的异步回调
        CompletableFuture<String> futureReturn = CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "1024";
        });

        System.out.println("1111111");

        System.out.println(futureReturn.get());

        futureReturn.whenComplete((t,u)->{
            System.out.println("t-->"+t);
            System.out.println("u-->"+u);
        }).exceptionally((e)->{
            System.out.println(e.getMessage());
            return "500";
        });
    }
}
