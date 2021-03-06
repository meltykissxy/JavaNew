package test07;

public class TestTicketProblem6 {
    public static void main(String[] args) {
        Demo6 d1 = new Demo6("窗口一");
        Demo6 d2 = new Demo6("窗口二");
        Demo6 d3 = new Demo6("窗口三");

        d1.start();
        d2.start();
        d3.start();
    }
}

class Demo6 extends Thread {
    private static int i = 1000;//静态变量，上面的d1,d2,d3是同一个类的对象，i是同一份，此时出现了线程安全问题
    private static Object lock = new Object();

    public Demo6(String name) {
        super(name);
    }

    public void run() {
        while(i>=1){
            synchronized (lock) {//三个Demo5的对象使用的lock是同一个
                if(i>=1) {//进来之后再次判断条件
                    i--;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(getName() + "卖出一张票，剩余：" + i);
                }
            }
        }
        System.out.println(getName() + "发现没有票了");
    }
}