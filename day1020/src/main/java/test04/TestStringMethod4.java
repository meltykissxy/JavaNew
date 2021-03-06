package com.atguigu.test04;

import org.junit.Test;

import java.util.Scanner;

/*
（5）常用方法系列4：和字符或字符数组有关
A：char charAt(下标)
B：char[] toCharArray()
C：String(char[] value)：返回指定数组中表示该字符序列的 String。
String(char[] value, int offset, int count)：返回指定数组中表示该字符序列的 String。
D：
static String copyValueOf(char[] data)： 返回指定数组中表示该字符序列的 String
static String copyValueOf(char[] data, int offset, int count)：返回指定数组中表示该字符序列的 String
static String valueOf(char[] data, int offset, int count) ： 返回指定数组中表示该字符序列的 String
static String valueOf(char[] data)  ：返回指定数组中表示该字符序列的 String
 */
public class TestStringMethod4 {
    @Test
    public void test02(){
        String str = "hello";

        char[] array = str.toCharArray();//返回了一个新数组
    }

    @Test
    public void test01(){
        Scanner input = new Scanner(System.in);
        System.out.print("请输入性别：");
        char gender = input.next().charAt(0);
        /*
        input.next()：的返回值类型是String对象
          Scanner类的    public String next()
        charAt(0)：是字符串类的方法
         */

        System.out.println(gender);
    }
}
