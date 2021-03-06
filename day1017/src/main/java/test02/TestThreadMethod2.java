package test02;

/*
2、方法们
（1）public void start()：启动线程的作用
     注意：start()被调用，不代表run方法立刻被执行，至于它对应的run()什么时候调用，线程调度器说了算
（2）public void run()：必须重写，线程要干的事
（3）public static void sleep(long millis) throws InterruptedException
    public static void sleep(long millis, int nanos)throws InterruptedException
（4） public final String getName() ：获取线程名称
     public final void setName(String name)：设置线程名称
（5）public final void setPriority(int newPriority)：设置线程的优先级
    public final int getPriority()：获取线程的优先级
    优先级有范围：1-10之间
    Thread类中有几个常量：MAX_PRIORITY = 10;
                        MIN_PRIORITY = 1;
                        NORM_PRIORITY = 5;
    优先级高的线程，会获得更多机会来抢夺CPU，但是不代表它一定就先抢到。
（6）public final void setDaemon(boolean on)：设置某个线程为守护线程
    守护线程它不会单独存在，它一般都是协助，辅助其他线程工作的，
    例如：GC线程就是守护线程。

    如果JVM中非守护线程都结束了，那么守护线程会自动结束。
（7）public static Thread currentThread()：获取当前代码由哪个线程执行，即获取当前线程对象

（8）public static void yield()：暂停当前正在执行的线程对象，并执行其他线程。
    当前线程暂停后，下次很有可能还是它自己抢到CPU。
    如果其他线程优先级比较高，那么被其他线程抢走的概率会增大，但也不是百分百。

（9）public final void join()throws InterruptedException
    public final void join(long millis)throws InterruptedException
    public final void join(long millis, int nanos)throws InterruptedException

（10）@Deprecated
public final void stop()：已过时
 */
public class TestThreadMethod2 {
    public static void main(String[] args) {
        /*System.out.println("hello");

        try {
            Thread.sleep(2000);//sleep抛出了编译时异常，我们必须throws或try...catch
                                    //单位是毫秒，1秒 = 1000毫秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("java");*/

        System.out.println("----------------------------------");
        /*OtherThread t = new OtherThread();
//        t.setPriority(100);//IllegalArgumentException
        t.setPriority(1);
        t.start();

        DemoThread d = new DemoThread();
        d.setPriority(10);
        d.start();

        System.out.println("t线程的优先级：" + t.getPriority());*/

        System.out.println("----------------------------------");

        /*MyDaemon daemon = new MyDaemon();
        daemon.setDaemon(true);//让daemon称为守护线程
        daemon.start();

        //主线程，打印1-100
        for (int i=1; i<=100; i++){
            System.out.println("main:" + i);
        }*/

//        System.out.println(Thread.currentThread());

        System.out.println("----------------------------------");
        /*PrintNumber p = new PrintNumber();
        p.setPriority(10);
        p.start();

        for (int i=1; i<=10; i++){
            if(i==2){
                Thread.yield();//暂停当前线程，
            }
            System.out.println("main:" + i);
        }*/

        System.out.println("------------------------------");
        PrintNumber p = new PrintNumber("线程1");
        p.start();

        PrintNumber p2 = new PrintNumber("线程2");
        p2.start();

        for (int i=1; i<=10; i++){
            try {
                if(i==2){
                    p.join();//join方法抛出编译时异常，必须try...catch或throws
                            //p线程插队，p线程插到了main线程的前面，
                            //main必须等到p线程结束才会有机会了。
                            //p.join()代码是被main线程执行的，所以是main线程被阻塞，和其他线程无关。
                  //  p.join(1000);//p线程只是加塞1000毫秒，就自动解除了，main就可以和p一起抢了
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("main:" + i);
        }
    }
}

class OtherThread extends Thread{
    public void run(){
        for (int i=0; i<100; i++) {
            System.out.println("other");
        }
    }
}
class DemoThread extends Thread{
    public void run(){
        for (int i=0; i<100; i++) {
            System.out.println("demo");
        }
    }
}
class MyDaemon extends Thread{
    public void run(){
        while(true){
            System.out.println("我守护你");
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class PrintNumber extends Thread{
    public PrintNumber(String name) {
        super(name);
    }

    public void run(){
        for(int i=100; i<=110; i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getName() + ":" + i);
        }
    }
}