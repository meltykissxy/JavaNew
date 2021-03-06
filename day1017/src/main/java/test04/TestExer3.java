package test04;

/*
案例：编写龟兔赛跑多线程程序，设赛跑长度为30米
兔子的速度是10米每秒，兔子每跑完10米休眠的时间10秒
乌龟的速度是1米每秒，乌龟每跑完10米的休眠时间是1秒
要求：要等兔子和乌龟的线程结束，主线程（裁判）才能公布最后的结果。
 */
public class TestExer3 {
    public static void main(String[] args) {
        //裁判
        Rabbit r = new Rabbit();
        Turtle t = new Turtle();

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
class Rabbit extends Thread{
    private long time;
    public void run(){
        long start = System.currentTimeMillis(); //获取时间值，现在距离1970-1-1 0.0.0 0的毫秒值
        for (int i=1; i<=30; i++){//i单位是米
            try {
                //兔子的速度是10米每秒  模拟，  1米用时100毫秒
                Thread.sleep(100);
                System.out.println("兔子已经跑了" + i + "米");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                //兔子每跑完10米休眠的时间10秒
                if(i==10 || i==20){
                    System.out.println("兔子正在休息....");
                    //模拟休息10秒
                    Thread.sleep(10000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("兔子到达终点.");
        long end = System.currentTimeMillis(); //获取时间值，现在距离1970-1-1 0.0.0 0的毫秒值
        time =  end - start;
    }

    public long getTime() {
        return time;
    }
}
class Turtle extends Thread{
    private long time;
    public void run(){
        long start = System.currentTimeMillis();
        for (int i=1; i<=30; i++){//i单位是米
            try {
                //乌龟的速度是1米每秒
                Thread.sleep(1000);
                System.out.println("乌龟已经跑了" + i + "米");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                //乌龟每跑完10米的休眠时间是1秒
                if(i==10 || i==20){
                    System.out.println("乌龟正在休息....");
                    //模拟休息1秒
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("乌龟到达终点.");
        long end = System.currentTimeMillis();
        time = end - start;
    }

    public long getTime() {
        return time;
    }
}