package test03;

/*
问题升级：
   王老板开饭店挣到钱了，顾客也越来越多了，他忙不过来了，
   他雇了一个厨师：陈头，还雇了一个服务员：美丽

   （1）出现了负数
   （2）出现了卡死

   解决：
   （1）把wait的条件从if改为while
   （2）把notify修改为notifyAll
 */
public class TestComunicate2 {
    public static void main(String[] args) {
        //1、先准备工作台对象
        Workbench workbench = new Workbench();

        //2、启动四个线程
        Cook cook = new Cook("王老板",workbench);
        Cook cook2 = new Cook("陈头",workbench);
        Waiter waiter = new Waiter("翠花",workbench);
        Waiter waiter2 = new Waiter("美丽",workbench);

        cook.start();
        cook2.start();
        waiter.start();
        waiter2.start();
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
                Thread.sleep(1000);//这里加时间，是为了不要put一结束，就立刻进入下一次循环
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
                Thread.sleep(1000);//这里加时间，是为了不要put一结束，就立刻进入下一次循环
                //否则下次抢到CPU还是自己的概率就太高了
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

//平台
class Workbench{
    private static final int MAX_COUNT = 1;//这里把10修改为1，目的是使得问题暴露的更快一点
    private int total;//记录平台上面一共有几盘菜

    //由厨师调用
    //非静态方法，同步方法的默认锁是this
    //这里的this是否可以作为锁对象？（1）类型Workbench没问题（2）是同一个this吗？是，厨师和服务员线程用的是同一个Workbench的对象
    public synchronized void put(){
//        if(total >= MAX_COUNT){
        while(total >= MAX_COUNT){
            try {
//            当前线程要等待
                wait();//锁对象是this，this可以省略
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        total++;
        System.out.println(Thread.currentThread().getName()+ "放了一盘菜，剩余：" + total);

//        notify();//唤醒现在正在等待的一个线程  省略了this.
        notifyAll();//唤醒现在正在等待的所有和我是同一个锁对象的线程  省略了this.
    }

    //由服务员，媳妇调用
    public synchronized void take(){
//        if(total <=0){
        while(total <=0){
            try {
//            当前线程要等待
                wait();//锁对象是this，this可以省略
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        total--;
        System.out.println(Thread.currentThread().getName()+ "取了一盘菜，剩余：" + total);

//        notify();//唤醒现在正在等待的一个线程  省略了this.
        notifyAll();//唤醒现在正在等待的所有和我是同一个锁对象的线程  省略了this.
    }
}
