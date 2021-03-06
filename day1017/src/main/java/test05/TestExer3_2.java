package test05;

public class TestExer3_2 {
    public static void main(String[] args) {
        //裁判
        Racer r = new Racer("兔子",100,10000);
        Racer t = new Racer("乌龟",1000,1000);

        r.start();
        t.start();

        //让r和t线程都结束了，才能继续main
        try {
            r.join();//r线程加塞了，兔子线程加塞了，main线程必须等兔子线程结束才能继续
            //r线程和t线程是“同时”运行的线程，还可以抢
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            t.join();//这句代码必须等到兔子线程结束才能运行，因为上面r.join()把main阻塞了，main不会往下执行，知道r结束
            //t线程有可能比r线程先结束，如果t线程先结束了，等main线程恢复以后，t.join相当于没有意义，因为它无法阻塞main线程
            //t线程比r线程后结束，如果t线程后结束，等r线程结束后，main线程继续，遇到了t.join，main又被t线程阻塞，直到t线程结束为止
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("兔子用时：" + r.getTime());
        System.out.println("乌龟用时：" + t.getTime());
        if(r.getTime() < t.getTime()){
            System.out.println("兔子赢");
        }else if(r.getTime() > t.getTime()){
            System.out.println("乌龟赢");
        }else{
            System.out.println("平局");
        }
    }
}

class Racer extends Thread{
    private long meterTime;
    private long restTime;
    private long time;

    //有参构造
    public Racer(String name, long meterTime, long restTime) {
        super(name);
        this.meterTime = meterTime;
        this.restTime = restTime;
    }

    public void run(){
        long start = System.currentTimeMillis(); //获取时间值，现在距离1970-1-1 0.0.0 0的毫秒值
        for (int i=1; i<=30; i++){//i单位是米
            try {
                Thread.sleep(meterTime);
                System.out.println(getName() + "已经跑了" + i + "米");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                if(i==10 || i==20){
                    System.out.println(getName() +"正在休息....");
                    Thread.sleep(restTime);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(getName() + "到达终点.");
        long end = System.currentTimeMillis(); //获取时间值，现在距离1970-1-1 0.0.0 0的毫秒值
        time =  end - start;
    }

    public long getTime() {
        return time;
    }
}