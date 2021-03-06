package com.atguigu.test03;

import org.junit.Test;

/*
4、字符串对象如何存储？
 */
public class TestString4 {
    @Test
    public void test07(){
        char[] value = {'h','e','l','l','o'};
        String str = new String(value);
    }

    @Test
    public void test06(){
        String str = new String("hello");
    }
    @Test
    public void test05(){
        String str = "hello";
    }

    @Test
    public void test04(){
        String str = new String();//一个在堆，一个在常量池中""
    }

    @Test
    public void test03(){
        String str = "";//空字符串，底层是char[]，是一个长度为0的char[]数组对象
    }

    @Test
    public void test02(){
        String str = null;
    }

    @Test
    public void test01(){
        String str;
    }
}
