package com.atguigu.test03;

import org.junit.Test;

/*
五、字符串：java.lang.String（非常重要）
1、字符串的特点
（1）String类是final修饰，表示它没有子类
（2）Java 程序中的所有字符串字面值（如 "abc" ）都作为此类的实例实现。
==> 在程序中出现""引起来的，都是它的对象。
（3）字符串是常量；它们的值在创建之后不能更改。
==> 字符串对象不可变
    面试题：方法的传参，如果某个形参类型是String类，形参修改和实参无关
（4）因为 String 对象是不可变的，所以可以共享。
==> 共享的字符串在字符串常量池中

字符串常量池在哪里？
一般回答都是方法区。
如果要细致一点：JDK1.7之前在方法区，JDK1.7在堆的一块特殊区域，JDK1.8挪到了元空间(meta-space)
    元空间是独立于JVM内存的。
    为什么使用元空间？（1）字符串常量池中的内容基本上是比较固定
                    （2）程序中的字符串常量的量还是很大
                    从JVM的内存中移除字符串常量池，腾出空间给JVM其他东西使用。
                    元空间在JVM外，但是也是占无论内存的。
（5）字符串缓冲区支持可变的字符串。
==> 如果你有需求，要用可变的字符串，请用StringBuffer和StringBuilder。

（6）字符串的底层使用用char[]存储一串字符。
String str = "abc"; ==> 底层：char[] value={'a','b','c'};

说明：在JDK1.9之前，String底层都是char[]数组，JDK1.9之后，改为byte[]数组。
 为什么？  一个char占2个字节。但是我们大部分程序中的字符串都是有ASCII码表中的字符构成，
            例如：hello，每一个字符的编码都在[0,127]范围内，其实用1个字节就够了。
            所以，它认为用byte[]会比用char[]节省空间。


面试题/思考题：为什么字符串对象不可变？
（1）String类是final，我们不能扩展和继承String
（2）看源码，String内部：private final char value[]; 用来存储字符串的一串字符
   这个char[]数组是final修饰，意味着我们不能修改value的地址，即不能扩容它。
   这个char[]数组是private修饰，意味着我们无法直接操作value数组。
（3）看源码：发现String的很多方法，涉及到对字符串内容修改的方法，都是返回一个“新”字符串对象，
   除非你没有修改字符串的内容，返回原字符串对象。
 */
public class TestString {
    @Test
    public void test04(){
        String s1 = new String("hello");
        String s2 = new String("hello");
        System.out.println(s1 == s2);//false，虽然都是hello，堆中的对象，new两次就是两个独立的对象
    }
    @Test
    public void test03(){
        String s1 = "hello";
        String s2 = new String("hello");
        System.out.println(s1 == s2);//false，虽然都是hello，一个在字符串常量池，一个在堆
    }
    @Test
    public void test02(){
        String s1 = "hello";
        String s2 = "hello";
        System.out.println(s1 == s2);//true，都是hello，共享了
    }

    @Test
    public void test01(){
        System.out.println("hello");//打印了一个字符串对象

    }
}
