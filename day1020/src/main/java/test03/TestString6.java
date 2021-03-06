package com.atguigu.test03;

import org.junit.Test;

import java.util.Date;

/*
6、字符串对象的比较
（1）==：比较两个字符串的地址
只有是两个指向常量池中的对象的  地址比较才会是true，其他都是false

（2）boolean equals(Object obj)
Objejct类中默认的equals，等价于==，
String类中对equals方法进行了重写。
    重写：先比较两个对象的地址，然后看类型，看字符串的长度，再看每一个字符的内容

（3）public boolean equalsIgnoreCase(String anotherString)：忽略大小写比较内容

（4）int compareTo(Object obj)
String类实现了java.lang.Comparable接口

（5）int compareToIgnoreCase(另一个字符串）
 */
public class TestString6 {
    @Test
    public void test03() {
        String str1 = "hello";
        String str2 = "HELLO";

        System.out.println(str1.compareTo(str2));//32  正  表示str1 > str2
        System.out.println(str1.compareToIgnoreCase(str2));//0  表示str1 = str2
    }

    @Test
    public void test02(){
        String str1 = "hello";
        String str2 = "HELLO";
        System.out.println(str1 == str2);//false
        System.out.println(str1.equals(str2));//false
        System.out.println(str1.equalsIgnoreCase(str2));//true
    }

    @Test
    public void test01(){
        String str1 = "hello";
        String str2 = "hello";
        String str3 = new String("hello");

        System.out.println(str1 == str2);//true
        System.out.println(str1 == str3);//false

        System.out.println(str1.equals(str2));//true
        System.out.println(str1.equals(str3));//true

        System.out.println("hello".equals(new Date()));//可以调用，因为equals方法的形参是Object，结果是false
    }
}
