package com.example.demo.juc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MapTest {
    public static void main(String[] args) {
        //加载因子，初始化容量； 工作中不直接使用HashMap，一般都是高并发场景
        //Map<String,Object> map = new HashMap<>(); 并发修改异常
        //1、解决方案 Map<String,Object> map = Collections.synchronizedMap(new HashMap<>());
        ConcurrentMap<String,Object> map = new ConcurrentHashMap();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,5));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }
}
