package test07;

public class TestTicketProblem3 {
    public static void main(String[] args) {
        Demo3 d1 = new Demo3("窗口一");
        Demo3 d2 = new Demo3("窗口二");
        Demo3 d3 = new Demo3("窗口三");

        d1.start();
        d2.start();
        d3.start();
    }
}
class Demo3 extends Thread{
    private static int i = 10;//静态变量，上面的d1,d2,d3是同一个类的对象，i是同一份，此时出现了线程安全问题
    public Demo3(String name) {
        super(name);
    }

    public void run(){
        for(; i>=1; i--){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getName() + "卖出一张票，剩余：" + i);
        }
        System.out.println(getName()+"发现没有票了");
    }
}