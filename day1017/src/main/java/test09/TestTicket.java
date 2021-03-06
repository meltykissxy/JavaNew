package test09;

/*
6、遇到多线程问题，一般如何编写代码？
（1）抽取资源类
（2）声明线程类
把资源类的对象传给线程类
（3）对操作资源类的方法，或者是代码块加锁
 */
public class TestTicket {
    public static void main(String[] args) {
        Ticket ticket  = new Ticket();

        //三个线程，通过使用同一个ticket对象，来实现共享数据
        Window w1 = new Window("窗口一",ticket);
        Window w2 = new Window("窗口二",ticket);
        Window w3 = new Window("窗口三",ticket);

        w1.start();
        w2.start();
        w3.start();
    }
}

class Window extends Thread{
    private Ticket ticket;

    public Window(String name, Ticket ticket) {
        super(name);
        this.ticket = ticket;
    }

    public void run(){
        while(ticket.getTotal()>0){
            ticket.sale();
        }
    }
}

class Ticket{
    private int total = 1000;

    /*public synchronized   void sale(){//非静态方法默认的锁对象是this，这里就是Ticket的对象
        if(total>0) {
            total--;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "卖出一张票，剩余：" + total);
        }else{
            System.out.println("没票了");
        }
    }*/

    public void sale(){
        synchronized (this) {//同步代码块
            if (total > 0) {
                total--;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "卖出一张票，剩余：" + total);
            } else {
                System.out.println("没票了");
            }
        }
    }

    public int getTotal() {
        return total;
    }
}