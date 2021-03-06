package test08;

public class TestRunnable {
    public static void main(String[] args) {
        Demo7 d = new Demo7();

        Thread t1 = new Thread(d,"窗口一");
        Thread t2 = new Thread(d,"窗口二");
        Thread t3 = new Thread(d,"窗口三");

        t1.start();
        t2.start();
        t3.start();
    }
}

class Demo7 implements Runnable{
    private int total = 1000;//非静态  ，因为上面只创建了一个Demo7的对象

    public void run(){
        while(total>0){
            synchronized (this) {//这里选择this，可以，因为只有一个Demo7的对象
                if(total>0) {
                    total--;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "卖出一张票，剩余：" + total);
                }
            }
        }
    }
}