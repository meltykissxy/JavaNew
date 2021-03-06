package test03;

import org.junit.jupiter.api.Test;

public class TestClassInitializeLater {
    public static void main(String[] args) {
        System.out.println(A.a);//使用了A类的静态常量

        A[] arr = new A[5];//使用A类声明数组，并且创建数组对象
        System.out.println(arr.length);//因为A[]是一种新的类型
                                    //此时并没有用A创建对象，用的是A[]类型创建对象
                                    //A[] arr = new A[5];只是说，数组的长度为5，将来用来存储A的对象
                                    //arr[下标]元素中都是null
        B.method();//通过子类调用父类的静态方法
                    //因为在B类加载(load和link）的过程中，已经可以确定它的父类是谁了，
                //method()可以在A中类中找到，就不需要把B类也初始化
    }

    @Test
    public void play01() {
        B b = new B();
        // A类初始化
        // B类初始化
    }
    @Test
    public void play02() {
        B b = new B();
        A a = new A();
        // A类初始化
        // B类初始化
    }

    @Test
    public void play03() {
        System.out.println(A.a);
    }
    @Test
    public void play04() {
        System.out.println(B.a);
    }
    @Test
    public void play05() {
        A.method();
        // A类初始化
        // A类的静态方法
    }
}

class A {
    public static final int a = 10; //常量是在类加载的link过程中就完成了，不需要到clinit

    public static void method(){//静态方法会继承到子类中，但是子类不能重写
        System.out.println("A类的静态方法");
    }

    static {
        System.out.println("A类初始化");
    }
}

class B extends A {
    static{
        System.out.println("B类初始化");
    }
}