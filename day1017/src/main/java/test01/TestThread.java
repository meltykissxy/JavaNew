package com.atguigu.test01;

/*
二、Java中如何编写多线程程序？
1、JVM本身就是一个多线程程序
（1）main线程
（2）GC线程
（3）异常监听和处理线程

2、如何开启以上线程以外的线程，和main线程同时运行。
一共有四种方式：
JavaSE阶段有两种：
（1）继承Thread类
（2）实现Runnable接口
后面还有两种：
（3）实现Callable接口
（4）线程池

3、继承Thread类的方式
java.lang.Thread类，线程类。
步骤：
（1）编写一个类去继承Thread类
（2）必须重写父类的一个方法：
    public void run(){
        //该线程要完成的任务代码
    }
（3）创建自定义线程类的对象
（4）启动线程，调用线程对象的start()方法
 */
public class TestThread {

    public static void main(String[] args) {
        MyThread my = new MyThread();
//        my.run();//不要手动调用run()，如果这么干，就不是多线程程序
        my.start(); //从Thread父类继承的
                    //该方法的意义是告知JVM/操作系统，我们又启动了一个线程，你可以和main线程一起运行。
                    //这里的run方法，是由JVM帮我们调用了，不用我们手动调用。

        //在main中，打印1-100之间的奇数
        for (int i = 1; i <=100 ; i+=2) {
            System.out.println("main:" + i);
        }
    }
}


class MyThread extends Thread{
    @Override
    public void run() {
        //打印1-100之间的偶数
        for (int i = 2; i <=100 ; i+=2) {
            System.out.println(i);//run()也不见得是一口气执行完，CPU可以能在任意一次过程中，发生切换
        }
    }
}
