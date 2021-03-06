package test02;

/*
三、java.lang.Thread类的API学习
1、构造器们
Thread()
Thread(Runnable target)：必须传入一个Runnable接口的实现类，最终调用的是Runnable接口的实现类的run()
Thread(Runnable target, String name)：可以给线程命名
Thread(String name)：可以给线程命名

2、方法们
（1）public void start()：启动线程的作用
     注意：start()被调用，不代表run方法立刻被执行，至于它对应的run()什么时候调用，线程调度器说了算
（2）public void run()：必须重写，线程要干的事
（3）
 */
public class TestThreadMethod {
    public static void main(String[] args) {
        RunnableImpl r = new RunnableImpl();

        //Thread(Runnable target, String name)
//        Thread t = new Thread(r, "线程1");

        //Thread(Runnable target)
        Thread t = new Thread(r);
        t.start();

        System.out.println(t.getName());//获取线程名字

        System.out.println("----------------------");
//        ThreadSub ts = new ThreadSub();//无参构造
        ThreadSub ts = new ThreadSub("线程2");//有参构造
        ts.start();

        System.out.println(ts.getName());
    }
}
class RunnableImpl implements  Runnable{
    public void run(){
        System.out.println("run");
    }
}

class ThreadSub extends Thread{
    public ThreadSub(){
        super();//Thread()
    }

    public ThreadSub(String name){
        super(name);//Thread(String name)
    }
    public void run(){
        System.out.println("thread");
    }
}