package com.atguigu.test01;

/*
4、实现Runnable接口的方式
java.lang.Runnable接口
步骤：
（1）编写自定义线程类去实现Runnable接口
（2）必须重写/实现接口的抽象方法
    public void run(){
    }
（3）创建自定义线程类的对象
（4）创建一个Thread类的对象
（5）调用Thread类的对象.start()


查看源码：Thread类
    @Override
    public void run() {
        if (target != null) {
            target.run();
        }
    }
 */
public class TestRunnable {
    public static void main(String[] args) {
        MyRunnable my = new MyRunnable();
//        my.start();//错误，MyRunnable类没有start方法，Runnable接口中也没有start方法

        //这里用到了Thread类的public Thread(Runnable target) 构造器，my就是给target赋值
        Thread t = new Thread(my);//把my传入Thread
                                //CPU在调度t对象的run方法时，它会去调用my的run方法
        t.start();

        //在main中，打印1-100之间的奇数
        for (int i = 1; i <=100 ; i+=2) {
            System.out.println("main:" + i);
        }
    }
}

class MyRunnable implements Runnable{
    //实现接口的方法的快捷键：Ctrl + I
    @Override
    public void run() {
        //打印1-100之间的偶数
        for (int i = 2; i <=100 ; i+=2) {
            System.out.println(i);//run()也不见得是一口气执行完，CPU可以能在任意一次过程中，发生切换
        }
    }
}