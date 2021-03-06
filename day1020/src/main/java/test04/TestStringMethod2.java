package com.atguigu.test04;

import org.junit.Test;

import java.util.Scanner;

/*
（3）常用方法系列2：和查找有关
A：boolean contains(xx)：是否包含
B：int indexOf(xx)
C：int lastIndexOf(xx)
 */
public class TestStringMethod2 {
    @Test
    public void test02(){
        String str = "hello java atguigu java chai java";
        //查找java的下标

        System.out.println(str.indexOf("java"));//6
        System.out.println(str.lastIndexOf("java"));//29
    }

    @Test
    public void test01(){
        Scanner input = new Scanner(System.in);
        System.out.print("请输入一个字符串：");
        String str = input.next();

        //判断这个字符串中是否有a字符
        if(str.contains("a")){
            System.out.println("包含");
        }else {
            System.out.println("不包含");
        }

        //判断这个字符串中是否有ab字符
        if(str.contains("ab")){
            System.out.println("包含");
        }else {
            System.out.println("不包含");
        }
    }
}
