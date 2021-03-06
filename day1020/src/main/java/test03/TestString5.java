package com.atguigu.test03;

import org.junit.Test;

/*
5、字符串的拼接
（1）+
  如果是两个字符串常量池中字符串常量+，结果还是在字符串常量池中，还是常量。
  如果是变量 + 字符串常量 ，或者是变量 +变量，结果在堆
  如果是指向堆中的常量 +  xx，结果在堆

（2）concat(另一个字符串)：结果一定在堆中

（3）intern()：把字符串对象放入常量池

 */
public class TestString5 {
    @Test
    public void test06(){
        String str1 = "hello";//str1是变量
        String str2 = "world";
        String str3 = "helloworld";

        String str4 = (str1 + str2).intern();//结果在常量池
        String str5 = (str1 + "world").intern();//结果在常量池
        String str6 = "hello" + "world";//结果在常量池

        System.out.println(str3 == str4);//true  只要结果调用intern()方法，结果一定在常量池
        System.out.println(str3 == str5);//true
        System.out.println(str3 == str6);//true
    }

    @Test
    public void test05(){
        String str1 = "hello";
        String str2 = "world";
        String str3 = "helloworld";
        String str4 = "hello".concat("world");
        String str5 = str1.concat("world");

        System.out.println(str3 == str4);//false  concat结果一定在堆
        System.out.println(str3 == str5);//false  concat结果一定在堆
    }

    @Test
    public void test03(){
        final String str1 = new String("hello");//str1是常量，但是它指向堆中的对象
        final String str2 = new String("world");//str2是常量，但是它指向堆中的对象
        String str3 = "helloworld";

        String str4 = str1 + str2;//结果在堆  常量+常量， 但是指向堆中的常量 + 指向堆中的常量
        String str5 = str1 + "world";//结果在堆   常量+常量， 但是指向堆中的常量 + 指向常量池中的常量
        String str6 = "hello" + "world";//结果在常量池  常量+常量  并且是 字符串常量池中的常量 + 字符串常量池中的常量

        System.out.println(str3 == str4);//false
        System.out.println(str3 == str5);//false
        System.out.println(str3 == str6);//true
    }


    @Test
    public void test02(){
       final String str1 = "hello";//str1是常量，记录了常量池中的某个字符串的地址
       final String str2 = "world";//str2是常量，记录了常量池中的某个字符串的地址
        String str3 = "helloworld";

        String str4 = str1 + str2;//结果在常量池    常量+常量  并且是 字符串常量池中的常量 + 字符串常量池中的常量
        String str5 = str1 + "world";//结果在常量池  常量+常量  并且是 字符串常量池中的常量 + 字符串常量池中的常量
        String str6 = "hello" + "world";//结果在常量池  常量+常量  并且是 字符串常量池中的常量 + 字符串常量池中的常量

        System.out.println(str3 == str4);//true
        System.out.println(str3 == str5);//true
        System.out.println(str3 == str6);//true
    }

    @Test
    public void test01(){
        String str1 = "hello";//str1是变量，指向了常量池中的字符串
        String str2 = "world";//str2是变量，指向了常量池中的字符串
        String str3 = "helloworld";//str3是变量，指向了常量池中的字符串

        String str4 = str1 + str2;//结果在堆   变量 + 变量
        String str5 = str1 + "world";//结果在堆  变量 + 常量
        String str6 = "hello" + "world";//结果在常量池  常量+常量，并且是字符池中常量 + 字符串池中的常量

        System.out.println(str3 == str4);//false
        System.out.println(str3 == str5);//false
        System.out.println(str3 == str6);//true
    }
}
