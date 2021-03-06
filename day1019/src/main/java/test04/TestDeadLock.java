package test04;

/*
八、死锁（了解）
1、什么情况下会发生死锁？
当两个和多个线程，互相持有对象想要的资源（锁对象），不放手，就会出现死锁的状态。

2、演示
举例：
    王阳有个非常漂亮的女朋友，有一条被绑架了，绑匪要求付500万的钱，放人。

3、如何避免？
尽量的不要出现，嵌套的同步块或同步方法的调用。
如果出现了嵌套调用，可能发生死锁。要有另外的机制来去检查，如果发生死锁了，就interrupt它们，或者报异常，然后重新运行。

 */
public class TestDeadLock {

    public static void main(String[] args) {
        Object girl = new Object();
        Object money = new Object();

        Boy boy = new Boy(girl, money);
        BangFei bangFei = new BangFei(girl, money);
        boy.start();
        bangFei.start();
    }
}

class Boy extends Thread{
    private Object girl;
    private Object money;

    public Boy(Object girl, Object money) {
        this.girl = girl;
        this.money = money;
    }

    public void run(){
        synchronized (money) {
            System.out.println("你先放人，我给你500万");
            synchronized (girl) {
                System.out.println("接到人之后，给了对方500万 或 报警");
            }
        }


    }
}
class BangFei extends Thread{
    private Object girl;
    private Object money;

    public BangFei(Object girl, Object money) {
        this.girl = girl;
        this.money = money;
    }

    public void run(){
        synchronized (girl){
            System.out.println("你先给我500万，我放人");
            synchronized (money) {
                System.out.println("收到方500万，放人");
            }
        }

    }
}
