package com.atguigu.test04;

import org.junit.Test;

/*
8、字符串的方法们
（1）构造器：...
（2）常用方法系列1：
A：String concat(..)：字符串拼接
B：String intern()：把字符串的结果放到常量池
C：boolean equals(..)：区分大小写的字符串内容比较
D：boolean equalsIgnoreCase(..）:不区分大小写的字符串内容比较
E：int compareTo(。。)：区分大小写比较字符串的大小
F：int compareToIgnoreCase(。。)：不区分大小写比较字符串的大小
G：int length()：字符串长度
        length：
    数组名.length  没有()，因为数组来说，length是属性
    字符串.length() 有()，因为对于字符串来说，length()是方法
H：boolean isEmpty()：判断字符串是否为空字符串
I：String toUpperCase()：把当前字符串的字符全部转为大写
J：String tolowerCase()：把当前字符串的字符全部转为小写
K：String trim()：去掉字符串前后的空白符

 */
public class TestStringMethod {
    @Test
    public void test03(){
        String str1 = " hello world";
        String str2 = str1.trim();//因为str1字符串的前面有空白符，所以对str1有修改，返回新对象
        System.out.println(str1 == str2);//false
    }

    @Test
    public void test02(){
        String str1 = "hello world";
        String str2 = str1.trim();//因为str1字符串的前后没有空白符，所以对str1没有修改，返回原对象
        System.out.println(str1 == str2);//true
    }

    @Test
    public void test01(){
        String str = "   hello   world   ";
        str = str.trim();//字符串对象不可变，一旦修改返回新对象，需要重新接收

        System.out.println("[" + str + "]");
    }
}
