package com.atguigu.test05;

/*
3、Java的异常的类型？ 或者Java的异常体系结构？
（1）根类型：java.lang.Throwable
Throwable 类是 Java 语言中所有错误或异常的超类。
A：只有当对象是此类（或其子类之一）的实例时，才能通过 Java 虚拟机或者 Java throw 语句抛出。
B：只有此类或其子类之一才可以是 catch 子句中的参数类型。

（2）两个非常重要的子类
A：Error
    Error 是 Throwable 的子类，用于指示合理的应用程序不应该试图捕获的严重问题。
    例如：VirtualMachineError虚拟机错误：
            StackOverflowError：栈内存溢出
            OutOfMemoryError：简称OOM，堆内存溢出
    如果程序允许遇到Error，应该停下来，修改代码，升级硬件。

B：Exception
    Exception 类及其子类是 Throwable 的一种形式，它指出了合理的应用程序想要捕获的条件。
    例如：
        NullPointerException：空指针
        ArrayIndexOutOfBoundsException：数组下标越界
        ClassCastException：类型转换异常

（3）针对Exception还可以再分：
A：RuntimeException及其子类：运行时异常，或者称为非受检异常
    在编译阶段，编译器不会提示我们xx代码可能发生异常，当运行时可能就发生异常。
    例如：
        NullPointerException：空指针
        ArrayIndexOutOfBoundsException：数组下标越界
        ClassCastException：类型转换异常
B：编译时异常，或者称为受检异常：除了运行时异常，剩下的都是编译时异常，包括Exception类型本身
    在编译阶段，编译器就会提醒我们xx代码可能发生异常，要求我们必须编写try...catch或throws等代码处理，否则编译不通过。
    例如：Exception：异常类型
        InterruptedException：中断异常
        IOException：IO异常
        SQLException：SQL异常
        ....

 */
public class TestExceptionType {
    public static void main(String[] args) throws InterruptedException {
        int[] arr = {1,2,3};
//        System.out.println(arr[5]);

        Object obj = new Object();
//        String str = (String) obj;

        Thread.sleep(1000);

    }
}
