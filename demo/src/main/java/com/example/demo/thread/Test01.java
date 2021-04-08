package com.example.demo.thread;

/*线程的创建
* 推荐使用runable接口来实现
*
* */
public class Test01 extends Thread{
    public static void main(String[] args) {
        //Thread 子线程
        Thread01 t01 =new Thread01();
        t01.start();

        //runable 子线程
        Thread02 t02 = new Thread02();
        t02.setName("run-02:");
        new Thread(t02).start();
        Thread02 t03 = new Thread02();
        t03.setName("run-03:");
        new Thread(t03).start();

        for(int i=0;i<200;i++){
            System.out.println("主线程***"+i);
        }
    }
}

//Thread实现线程创建
class Thread01 extends Thread{
    @Override
    public void run() {
        for(int i=0;i<200;i++){
            System.out.println("thread子线程==="+i);
        }
    }
}

//Runnable实现线程创建
class Thread02 implements Runnable{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for(int i=0;i<200;i++){
            System.out.println(this.name+i);
        }
    }
}

class User{
    private int id ;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

