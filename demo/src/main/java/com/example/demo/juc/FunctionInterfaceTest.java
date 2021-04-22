package com.example.demo.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/*
* 函数式接口：只有一个方法的接口
* @FunctionalInterface
*
* java.util.function：四大函数式接口
 * Consumer、Function、Predicate、Supplier
* 只要是函数式接口，就可以使用lamda表达式简化
*
* */
public class FunctionInterfaceTest {
    public static void main(String[] args) {
        List<String> list=new ArrayList();
        //消费者类型函数式接口
        list.forEach(Object::notify);

        Function function = new Function<String,String>() {
            @Override
            public String apply(String s) {
                return s;
            }
        };
        System.out.println(function.apply("133"));

        Function function2 = (str)->{return str;};
        System.out.println(function2.apply(true));

        Function function1 = (Function<String, String>) s -> s+"product";
        System.out.println(function1.apply("135"));


        //判定型接口
        Predicate predicate = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.isEmpty();
            }
        };
        System.out.println(predicate.test(".2."));

        Predicate<String> predicate1 = (s)->s.isEmpty();

        System.out.println(predicate1.test(".2."));

        //消费型接口，只有输入，没有返回
        System.out.println("消费型接口测试：：：");
        Consumer consumer= new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        consumer.accept("test1");

        Consumer<String> consumer1=(str)->{
            System.out.println(str+"0000");
        };
        consumer1.accept("test1");

        //供给型接口，只有返回，没有输入
        Supplier supplier=new Supplier<Integer>() {
            @Override
            public Integer get() {
                return 111;
            }
        };
        System.out.println(supplier.get());
    }
}
