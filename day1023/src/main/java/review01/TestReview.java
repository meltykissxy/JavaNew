package com.atguigu.review01;

/*
补充：
boolean startsWith(xx)：判断某个字符串是否以xxx开头
boolean endsWith(xx)：判断某个字符串是否以xxx结尾
 */
public class TestReview {
    public static void main(String[] args) {
        String fileName = "Hello.java";

        //判断该文件是否是Java的源文件
        //Java的源文件的特点是以：.java结尾
        System.out.println(fileName.endsWith(".java"));

        String name = "张三";
        //判断某个用户是否是姓张
        System.out.println(name.startsWith("张"));
    }
}
