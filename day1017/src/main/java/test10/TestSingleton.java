package test10;

import org.junit.Test;

/*
五、单例设计模式
1、设计模式：对同一类型的问题的解决方案。是之前的程序员总结出来的代码经验，是代码的模式。
2、单例设计模式
（1）什么情况会用到单例设计模式？
当你的项目中，某个类对象在整个系统中只允许有一个对象（实例对象）时，就需要用到单例。
例如：线程池对象，数据库连接池，手机中的时钟对象，军事系统中的总司令对象....
     Spring容器对象....

（2）如何实现单例呢？
方式一：饿汉式单例模式
    无论你是否用到这个类的对象，只要你用这个类，我就在类初始化，给你new好这个对象。
方式二：懒汉式单例模式
    你用这个类时，如果只是用这个类的静态的方法等，那么说明你不需要对象，我就不创建。
    当你确定要获取这个类的对象时，我再给你创建。

    懒汉式：延迟创建对象。

3、饿汉式
(1)枚举式
enum Single1{
    INSTANCE
}

（2）模拟枚举式
相当于JDK1.5之前声明枚举

class Single2{
    public static final Single2 INSTANCE = new Single2();

    private Single2(){//构造器私有化，外面不能直接new对象

    }
}

（3）把唯一的静态的常量对象私有化
class Single3{
    private static final Single3 INSTANCE = new Single3();
    private Single3(){//构造器私有化，外面不能直接new对象

    }

    //因为INSTANCE是private的，那么外面无法直接访问，需要提供一个get方法

    public static Single3 getInstance() {
        return INSTANCE;
    }
}

4、懒汉式
（1）加同步的懒汉式
（2）内部类的懒汉式

 */
public class TestSingleton {
    @Test
    public void test01(){
        Single1 s1 = Single1.INSTANCE;
        Single1 s2 = Single1.INSTANCE;
        System.out.println(s1 == s2);

        //如果在Single1中编写了其他方法，就可以通过对象.方法进行使用
    }

    @Test
    public void test02(){
        Single2 s1 = Single2.INSTANCE;
        Single2 s2 = Single2.INSTANCE;

        System.out.println(s1 == s2);

        //如果在Single2中编写了其他方法，就可以通过对象.方法进行使用
    }

    @Test
    public void test03(){
        Single3 s1= Single3.getInstance();
        Single3 s2= Single3.getInstance();
        System.out.println(s1 == s2);

        //如果在Single2中编写了其他方法，就可以通过对象.方法进行使用
    }

    @Test
    public void test04(){
        Single4 s1 = Single4.getInstance();
        Single4 s2 = Single4.getInstance();
        System.out.println(s1 == s2);
    }

    Single4 s1;
    Single4 s2;
    @Test
    public void test05(){
        Thread t1 = new Thread() {
            public void run() {
                s1 = Single4.getInstance();
            }
        };
        t1.start();

       Thread t2= new Thread(){
            public void run(){
                s2 = Single4.getInstance();
            }
        };
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s1 == s2);
    }

    @Test
    public void test06(){
        Single5.method();//只是用外部类的静态方法
                        //因为外部类的method方法中，没有用到Inner,所以不会导致Inner的初始化

        Single5 s1 = Single5.getInstance();
        Single5 s2 = Single5.getInstance();

        System.out.println(s1 == s2);
    }
}
enum Single1{
    INSTANCE
}
class Single2{
    public static final Single2 INSTANCE = new Single2();

    private Single2(){//构造器私有化，外面不能直接new对象

    }
}

class Single3{
    private static final Single3 INSTANCE = new Single3();
    private Single3(){//构造器私有化，外面不能直接new对象

    }

    //因为INSTANCE是private的，那么外面无法直接访问，需要提供一个get方法

    public static Single3 getInstance() {
        return INSTANCE;
    }
}

class Single4{
    private static Single4 instance;//不着急创建对象

    private Single4(){//构造器私有化，外面不能直接new对象

    }

    //有线程安全问题
    /*public static Single4 getInstance(){
//        return new Single4();//每次调用getInstance()都会new一个对象，不是单例了
        if(instance == null){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new Single4();
        }
        return instance;
    }*/

    //可以解决线程安全问题，但是不够perfect
    /*public synchronized static Single4 getInstance(){
        if(instance == null){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new Single4();
        }
        return instance;
    }*/
    public static Single4 getInstance(){
        if(instance == null){
            synchronized (Single4.class) {//Single4.class当前类的Class对象
                                        //静态方法中不允许this
                if(instance == null) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    instance = new Single4();
                }
            }
        }
        return instance;
    }
}

//懒汉式
class Single5{
    private Single5(){//构造器私有化

    }

    static{
        System.out.println("外部类的静态代码块");
    }
    private static class Inner{
        private static final Single5 INSTANCE = new Single5();
        static{
            System.out.println("静态内部类的静态代码块");
        }
    }

    public static Single5 getInstance(){
        return Inner.INSTANCE;
    }

    public static void method(){
        System.out.println("外部类的静态方法");
    }
}