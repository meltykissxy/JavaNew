package com.atguigu.test07;

/*
8、抛出异常的关键字：throw
（1）作用：程序员手动抛出异常、

即Java中的异常对象有两种可能性：
A：JVM帮我们抛出的
B：程序自己用throw语句抛出的

说明：只有当对象是此类（或其子类之一）的实例时，才能通过 Java 虚拟机或者 Java throw 语句抛出。
    即能够被JVM抛出的，或我们throw语句抛出的对象的类型必须是异常类型。

（2）无论是我们    Java 虚拟机 或者 Java throw 语句 抛出的，在程序中都要使用
A：try...catch
B：throws
之一处理它们。

我们程序中虽然可以throws，把异常抛出调用者，但是最终肯定要在某一级进行try...catch，否则程序就挂了。

（3）用法
throw 异常对象;
throw new 异常对象(...);

（4）什么情况下会自动throw？
A：自定义异常，必须用throw语句抛出
B：在方法中，某些参数不符合xx条件时，我们主动抛出系统异常

目的：都得改变方法的运行轨迹，代替原来的return语句，结束当前方法，返回给调用者一个异常信息。

 */
public class TestThrow {
    public static void main(String[] args) {
        Account a = new Account("11111",1000);
        try {
            a.withdraw(500);
            System.out.println(a);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            a.withdraw(2000);
            System.out.println(a);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

class Account{
    private String id;//账号
    private double balance;

    public Account() {
    }

    public Account(String id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", balance=" + balance +
                '}';
    }

    public void withdraw(double money) throws Exception {
        if(money < 0){
            //IllegalArgumentException是一个运行时异常
//            throw new IllegalArgumentException("取款金额不能为负数");//代替return语句结束当前方法
            throw new Exception("取款金额不能为负数");
        }
        if(money > balance){
            throw new IllegalArgumentException("余额不足");
        }
        balance -= money;
    }
}
