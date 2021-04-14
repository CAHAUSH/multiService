package com.example.demo.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //new Thread(new FutureTask(new myThread())).start();
        FutureTask futureTask = new FutureTask(new myThread());
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }
}

class myThread implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println("call()");
        return "123";
    }
}
