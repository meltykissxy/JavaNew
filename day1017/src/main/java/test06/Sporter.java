package test06;

public class Sporter extends Thread {
    private long meterTime;//跑每米用时
    private long restTime;//跑完10米休息的时间
    private long time;//从跑到停的时间
    private int distance;//当前运动员一共跑了几米
    private final int MAX_DISTANCE = 30;
    private volatile static boolean flag = true;//true表示跑

    //有参构造
    public Sporter(String name, long meterTime, long restTime) {
        super(name);
        this.meterTime = meterTime;
        this.restTime = restTime;
    }

    public void run() {
        long start = System.currentTimeMillis(); //获取时间值，现在距离1970-1-1 0.0.0 0的毫秒值
        while(distance < MAX_DISTANCE && flag){
            try {
                Thread.sleep(meterTime);
                distance++;
                System.out.println(getName() + "已经跑了" + distance + "米");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                if (distance == 10 || distance == 20) {
                    System.out.println(getName() + "正在休息....");
                    Thread.sleep(restTime);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if(distance == MAX_DISTANCE){
            flag = false;
            System.out.println(getName() + "到达终点.");
        }

        long end = System.currentTimeMillis(); //获取时间值，现在距离1970-1-1 0.0.0 0的毫秒值
        time = end - start;
        System.out.println(getName() + "停止跑");
    }

    public long getTime() {
        return time;
    }

    public static boolean isFlag() {
        return flag;
    }

    public int getDistance() {
        return distance;
    }
}
