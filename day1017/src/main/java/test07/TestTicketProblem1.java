package test07;

/*
四、线程安全问题
1、什么情况下会发生线程安全问题？
当多个线程访问同一个资源（变量、文件...），
并且对它的访问是有读操作和写操作，
这个时候会出现线程安全问题。
即当前线程在对某个资源使用过程中，读取的，可能是中途被其他线程修改过的值。

举例：
    王阳
    陈涛
    陈含笑
    它们住在一起，只有一个卫生间。
    这个卫生间就是共享数据。
    早上，大家闹铃都是7.30，同时起床，大家都去抢这个卫生间。
    规定：每个人只能用1分钟。
    王阳进去了，1分钟之后没用完，陈涛进去了，就尴尬。这个就是线程安全问题。

代码中：
    卖票问题。
    同时三个窗口卖票。票数（车票，电影票...）一共10张。

2、如何实现共享数据？
JVM的内存有些是可以共享的，有些是不能共享。
不能共享的：
(1)栈中（Java虚拟机栈，还是本地方法栈）
(2)程序计数器

可以共享的：
（1）方法区
    如果是方法区的话，必须是同一个类型。
（2）堆
    如果是堆的话，必须是同一个对象。

3、如何解决线程安全问题？
生活中的卫生间问题如何解决尴尬情况？ 加锁
在代码中也是这个思路，对资源的访问代码要加锁。

形式一：同步代码块
synchronized(锁对象){
    需要加锁的代码；这段代码的运行期间不受其他线程的干扰。独占这段代码，我们结束，其他线程进不来。
}

形式二：同步方法
【修饰符】 synchronized 返回值类型  方法名(【形参列表】)【throws 异常列表】{
}

同步方法的锁对象的选择是被动的：
（1）非静态方法：this
（2）静态方法：当前类的Class对象
    每一个类被加载到方法区之后，会用一个Class对象来表示这个类型，只要是同一个类型，Class就是同一个。

4、谁可以作为锁对象
（1）任意类型的对象都可以作为锁对象
不能使用基本数据类型的值，只能使用对象。
（2）要保证大家（有竞争关系的线程）使用的是同一个锁对象

5、锁的代码范围
（1）太大，导致其他线程没机会
（2）太小，安全问题解决不了
原则：锁一次任务代码
 */
public class TestTicketProblem1 {
    public static void main(String[] args) {
        Demo1 d1 = new Demo1("窗口一");
        Demo1 d2 = new Demo1("窗口二");
        Demo1 d3= new Demo1("窗口三");

        d1.start();
        d2.start();
        d3.start();
    }
}

class Demo1 extends Thread{
    public Demo1(String name) {
        super(name);
    }

    public void run(){
        for(int i=10; i>=1; i--){//i是局部变量，在栈中，每一个线程都是独立的，出现了3个窗口分别卖10张票
            System.out.println(getName() + "卖出一张票，剩余：" + i);
        }
        System.out.println(getName()+"发现没有票了");
    }
}