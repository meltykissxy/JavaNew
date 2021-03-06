package com.atguigu.test03;

import org.junit.Test;

/*
3、字符串对象的个数问题？（面试题）
 */
public class TestString3 {
    @Test
    public void test05() {
        String str = String.valueOf("hello");
        //上面的代码创建了几个字符串对象？1个
    }

    @Test
    public void test04() {
        String str1 = new String("hello");
        String str2 = new String("hello");
        //上面的代码创建了几个字符串对象？3个，1个是常量，2个在堆
    }


    @Test
    public void test03() {
        String str = new String("hello");
        //上面的代码创建了几个字符串对象？2个，1个是常量，1个堆中
    }

    @Test
    public void test02(){
        String str1 = "hello";
        String str2 = "hello";
        //上面的代码创建了几个字符串对象？  1个  "hello"是共享同一个
        //str1和str2指向同一个对象
    }

    @Test
    public void test01(){
        String str = "hello";//创建了一个字符串对象
    }
}
