package com.example.demo.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/*
* BlockingQueue 阻塞队列，，4组API
*
* */
public class BlockingQueueTest {
    public static void main(String[] args) throws InterruptedException {
        String a="ENC:8923kiwfrlwe";
        a.substring(0);
        StringBuffer st = new StringBuffer();
        //Collection：
        //-Set
        //-List
        //-Queue： BlockingQueue
        System.out.println("抛出异常=================================");
        BlockingQueueTest.test();
        System.out.println("不抛出异常===============================");
        BlockingQueueTest.test1();
        System.out.println("阻塞等待=================================");
        BlockingQueueTest.test2();
        System.out.println("阻塞超时等待=============================");
        BlockingQueueTest.test3();
    }

    public static void test(){
        ArrayBlockingQueue queue = new ArrayBlockingQueue(3);
        System.out.println(queue.add("a"));
        System.out.println(queue.add("b"));
        System.out.println(queue.add("c"));

        //返回队首元素
        System.out.println(queue.element());

        //超出队列容量，抛出异常：java.lang.IllegalStateException: Queue full
        //System.out.println(queue.add("d"));

        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        //队列为空时，抛出异常：java.util.NoSuchElementException
        //System.out.println(queue.remove());
    }

    public static void test1(){
        ArrayBlockingQueue queue = new ArrayBlockingQueue(3);
        System.out.println(queue.offer("a"));
        System.out.println(queue.offer("b"));
        System.out.println(queue.offer("c"));

        //返回队首元素
        System.out.println("返回队首元素"+queue.peek());

        //出队列容量，不抛出异常，返回false
        System.out.println(queue.offer("d"));

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        //队列为空时，返回null
        System.out.println(queue.poll());

    }

    //阻塞等待
    public static void test2() throws InterruptedException {
        ArrayBlockingQueue queue = new ArrayBlockingQueue(3);
        queue.put("a");
        queue.put("a");
        queue.put("a");
        //超出队列容量，程序一直等待、阻塞
        //queue.put("a");
    }

    //阻塞，超时等待
    public static void test3() throws InterruptedException {
        ArrayBlockingQueue queue = new ArrayBlockingQueue(3);
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");
        //超出容量，等待2秒，退出等待
        queue.offer("c",2, TimeUnit.SECONDS);

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        //队列为空，等待3秒，退出等待，返回null
        System.out.println(queue.poll(3,TimeUnit.SECONDS));
    }
}
