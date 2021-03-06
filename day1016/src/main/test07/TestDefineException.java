package com.atguigu.test07;

/*
10、自定义异常
（1）为什么要自定义异常？
系统中（JRE的核心类库中）已经给我们提供了大量的异常类型，
但是有时候我们仍然觉得不够用。

因为异常类型的名字非常重要，我们发生xx异常时，往往就是根据异常的名字来断定问题的。
比如：
    NullPointerException：空指针异常
    FileNotFoundException：文件不存在异常

在我们的项目中，一些异常是以为不满足我们的xx业务逻辑条件发生的，那么我们希望通过名字更好的区分。

（2）举例
银行账号类：BankAccount

（3）如何自定义异常呢？
A：必须继承Throwable或它的子类
因为：
 只有当对象是此类（或其子类之一）的实例时，才能通过 Java 虚拟机或者 Java throw 语句抛出。
 类似地，只有此类或其子类之一才可以是 catch 子句中的参数类型。

 如果是继承RuntimeException或它的子类，那么当前自定义异常就是运行时异常。
     如果我们throw 运行时异常，编译器不会强制要求我们加throws 或 try...catch
 如果是基础Throwable或Exception，只要是RuntimeException系列以外的，那么当前自定义异常就是编译时异常。
    如果我们throw 编译时异常，编译器会强制要求我们加throws 或 try...catch

B：建议自定义异常类型保留两个构造器
    a：无参构造
    b：带message初始化功能的构造器

C：异常名字非常重要，见名知意

（4）自定义异常如何抛出
必须使用throw 手动抛出。
 */
public class TestDefineException {
    public static void main(String[] args) {
        BankAccount b = new BankAccount("11111",0);
        try {
            b.withdraw(1000);
        } catch (MoneyNotEnoughException e) {
            System.out.println(e.getMessage());//e.getMessage从父类继承的
        }
    }
}
//自定义异常
class MoneyNotEnoughException extends Exception{
    public MoneyNotEnoughException() {
    }

    public MoneyNotEnoughException(String message) {
        super(message);
    }
}


class BankAccount{
    private String id;//账号
    private double balance;

    public BankAccount() {
    }

    public BankAccount(String id, double balance) {
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

    public void withdraw(double money) throws MoneyNotEnoughException {
        if(money < 0){
            //IllegalArgumentException是一个运行时异常
            throw new IllegalArgumentException("取款金额不能为负数");//代替return语句结束当前方法
        }
        if(money > balance){
            throw new MoneyNotEnoughException("余额不足");
        }
        balance -= money;
    }
}
