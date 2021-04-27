package com.example.demo.juc;

import java.util.Arrays;
import java.util.List;

/*
* Stream流式计算
*
*
*
* */
public class StreamTest {
    public static void main(String[] args) {
        User user1=new User(1,"a",21);
        User user2=new User(2,"b",22);
        User user3=new User(3,"c",23);
        User user4=new User(4,"d",24);
        User user5=new User(5,"e",25);
        User user6=new User(6,"f",26);
        List<User> users= Arrays.asList(user1,user2,user3,user4,user5,user6);
        users.stream().filter(user -> user.getId()%2==0)
                .filter(user -> user.getAge()>=23)
                .map(user -> user.getName().toUpperCase())
                .forEach(System.out::println);
        users.forEach(t-> System.out.println(t.getAge()));

    }
}

class User{
    private int id;
    private String name;
    private int age;

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
