package com.example.demo.juc;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class UnsafeCollection {
    //线程不安全集合类
    //java.util.ConcurrentModificationException ，并发修改异常，
    public static void main(String[] args) {
        List<String> list= Arrays.asList("1","2","3");
        list.forEach(System.out::println);

        //ArrayList线程不安全，java.util.ConcurrentModificationException
        //List<String> list1=new ArrayList<>();
        //1、解决方案 使用Vector解决
        //List<String> list1=new Vector<>();
        //2、解决方案 使用Collections
        //List<String> list1 = Collections.synchronizedList(new ArrayList<>());
        //3、JUC并发安全类
        //CopyOnWrite写入时复制 COW 优化策略（比Vector执行效率高）
        //在写入的时候，避免覆盖
        List<String> list1 = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                list1.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(list1);
            }).start();
        }
    }
}
