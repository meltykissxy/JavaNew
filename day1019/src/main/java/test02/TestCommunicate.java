package test02;

/*
第9章 多线程
一、几个概念
二、启动线程的几种方式
三、线程类Thread的API
四、线程安全问题
五、单例
六、线程通信
线程通信也称为等待与唤醒机制。

1、它是用来解决什么问题的？或者说，什么情况下要用到线程通信，等待与唤醒机制？
当我们的代码/需求，符号生产者与消费者问题时，就需要考虑使用线程通信，等待与唤醒机制。
其实，解决生产者与消费者问题的代码解决方案，也是一种设计模式，称为生产者与消费者设计模式。

生产者与消费者问题？
当我们一个（一些）线程负责给数据缓冲区（变量、集合等容器、文件等）填充/增加数据；
另一个（一些）线程负责从数据缓冲区提取/消耗数据；
这个时候，会有哪些问题呢？
（1）线程安全问题：
    多个线程、同时使用同一个数据缓冲区，有的“写”，有的“读”。
    如何解决？A：synchronized  B：Lock  加锁
（2）  数据缓冲区可能出现空或者满的问题
    当数据缓冲区“空”时， 负责从数据缓冲区取数据的线程就不能正常运行，只能“等待”；
            等到什么时候？等到存数据的线程往里添加了数据之后，并且“唤醒”我，我再继续。
    如果据缓冲区的空间是有限的，并且已“满”，负责往数据缓冲区填充数据的线程就不能继续运行，只能“等待”；
            等到什么时候？等到取数据的线程往外消耗了数据之后，并且“唤醒”我，我再继续。

    如何解决？线程通信，等待与唤醒机制。

2、如何实现线程通信，等待与唤醒机制？
JavaSE阶段，配合synchronized用，是Object类中两个系列的方法：wait（等待）和notify（唤醒和通知）
juc阶段锁是Lock，配合它实现等待与唤醒机制的是其他API。

java.lang.Object类
（1）public final void wait() throws InterruptedException
    public final void wait(long timeout) throws InterruptedException
    public final void wait(long timeout,int nanos) throws InterruptedException

    因为wait()方法抛出了InterruptedException编译时异常，那么在使用wait方法时，就要求try...catch或者接着throws


（2）public final void notify()
    public final void notifyAll()

    特别强调：wait和notify/notifyAll方法必须由  当前线程的“锁”对象调用，不能由别的对象调用。
            否则就会报IllegalMonitorStateException异常。

3、示例
    王阳从尚硅谷毕业了，做开发3年了，挣了一点钱，就不想做开发了，因为怕掉头发。
    3年过程中找了个媳妇。他俩决定开一个夫妻店的小饭馆。

    王阳负责在厨房炒菜，媳妇负责招待客人。
    后厨与前厅之间有一个窗口，可以传递菜。窗口那儿有一个平台，最多可以放10盘菜。


 */
public class TestCommunicate {
    public static void main(String[] args) {
        //1、先准备工作台对象
        Workbench workbench = new Workbench();

        //2、启动两个线程
        Cook cook = new Cook("王老板",workbench);
        Waiter waiter = new Waiter("翠花",workbench);

        cook.start();
        waiter.start();
    }
}
class Cook extends Thread{
    private Workbench  workbench ;

    public Cook(String name, Workbench workbench) {
        super(name);
        this.workbench = workbench;
    }

    public void run(){
        while(true){
            workbench.put();
            try {
                Thread.sleep(10);//这里加时间，是为了不要put一结束，就立刻进入下一次循环
                                        //否则下次抢到CPU还是自己的概率就太高了
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class Waiter extends Thread{
    private Workbench  workbench;

    public Waiter(String name, Workbench workbench) {
        super(name);
        this.workbench = workbench;
    }

    public void run(){
        while(true){
            workbench.take();
            try {
                Thread.sleep(10);//这里加时间，是为了不要put一结束，就立刻进入下一次循环
                                    //否则下次抢到CPU还是自己的概率就太高了
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

//平台
class Workbench{
    private static final int MAX_COUNT = 10;
    private int total;//记录平台上面一共有几盘菜

    //由厨师调用
    //非静态方法，同步方法的默认锁是this
    //这里的this是否可以作为锁对象？（1）类型Workbench没问题（2）是同一个this吗？是，厨师和服务员线程用的是同一个Workbench的对象
    public synchronized void put(){
        if(total >= MAX_COUNT){
            try {
//            当前线程要等待
                wait();//锁对象是this，this可以省略
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        total++;
        System.out.println(Thread.currentThread().getName()+ "放了一盘菜，剩余：" + total);

        notify();//唤醒现在正在等待的一个线程  省略了this.
    }

    //由服务员，媳妇调用
    public synchronized void take(){
        if(total <=0){
            try {
//            当前线程要等待
                wait();//锁对象是this，this可以省略
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Thread.sleep(1000);//加入时间，使得程序运行不会太快，出现刷屏，看不清效果
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        total--;
        System.out.println(Thread.currentThread().getName()+ "取了一盘菜，剩余：" + total);

        notify();//唤醒现在正在等待的一个线程  省略了this.
    }
}