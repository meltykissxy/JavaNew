package com.atguigu.test05;

/*
四、异常
1、什么是异常？
异常就是不正常。
当程序正常情况下是可以正确运行的，但是有时候会遇到一些特殊的情况，导致我们的程序无法正常运行，发生异常。

不是异常的：
（1）语法错误
（2）逻辑错误

是异常：
编译通过，运行有时候是正常，但是有时候会报错，
报错的原因可能是因为（1）参数传递错误，即非法参数（2）用户的硬盘已满（3）网络中断
不是我们代码的设计者控制问题，可能是调用者的问题，可能是外界的问题。

2、Java中表示异常？
Java一切皆对象，Java中的异常也是用对象表示。
每一个异常或错误的发生，JVM都会给你new一个对应类型的对象“抛出”。
如果你的程序可以“捕获”这个对象，并且处理，那么程序继续运行；
如果你的程序无法“捕获”这个对象，那么程序就“挂了”。

 */
public class TestException {
    public static void main(String[] args) {
//        System.out.println("a = " + a);//不是异常，是语法错误

        System.out.println(sum(1,1));

        System.out.println(divide(9,4));//2
        System.out.println(divide(9,0));//ArithmeticException: / by zero
    }

    /**
     * 方法求和
     * @param a int
     * @param b int
     * @return int 返回a+b
     */
    public static int sum(int a ,int b){
        return a*b;//不是异常，这个叫做逻辑错误，Bug，永远也得不到正确的结果
    }

    /**
     * 求两个整数的商
     * @param a int 被除数
     * @param b int 除数
     * @return int 返回a/b
     */
    public static int divide(int a, int b){
        return a/b;
    }
}
