package test07;

public class TestTicketProblem2 {
    public static void main(String[] args) {
        Demo2 d1 = new Demo2("窗口一");
        Demo2 d2 = new Demo2("窗口二");
        Demo2 d3= new Demo2("窗口三");

        d1.start();
        d2.start();
        d3.start();
    }
}
class Demo2 extends Thread{
    private int i = 10;//实例变量，但是上面的d1,d2,d3是三个对象，i是三个对象独立的
    public Demo2(String name) {
        super(name);
    }

    public void run(){
        for(i=10; i>=1; i--){
            System.out.println(getName() + "卖出一张票，剩余：" + i);
        }
        System.out.println(getName()+"发现没有票了");
    }
}