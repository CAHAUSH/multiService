package com.example.demo.juc;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

public class SetTest {
    public static void main(String[] args) {
        //HashSet：java.util.ConcurrentModificationException
        //并发修改异常 Set<String> set = new HashSet<>();
        //1、解决方案 Set<String> set = Collections.synchronizedSet(new HashSet<>());
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(set);
            },String.valueOf(i)).start();
        }

        //HashSet的底层？？ 就是一个Hashmap，key值不允许重复的特性
    }
}
