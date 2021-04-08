package com.example.demo.thread;

import java.util.concurrent.*;

//线程创建
public class TestCallable implements Callable<Boolean> {
    private int ticketNums = 10;
    @Override
    public Boolean call() throws Exception {
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
        return true;
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestCallable t1 = new TestCallable();
        TestCallable t2 = new TestCallable();
        //创建执行服务
        ExecutorService service = Executors.newFixedThreadPool(3);
        //提交执行
        Future<Boolean> r1 = service.submit(t1);
        Future<Boolean> r2 = service.submit(t2);
        //获取结果
        boolean rs1 = r1.get();
        boolean rs2 = r2.get();
        System.out.println("返回值："+rs1);
        //关闭服务
        service.shutdown();
    }
}
