package com.atguigu.test01;

import org.junit.Test;

/*
（7）常用方法系列6：和正则匹配有关

什么是正则？
正则：不是Java中特有的语法，它是独立于任意一种编程语言的语法。
        在Java中，C#，python，javascript，sql等中都可以用。

简单了解：
正则：指某种文本的规则，
        例如：密码   必须有大写字母小写字母数字组成，长度在6-12之间
             手机号码：11位数字
                    严格一点：130,131,132,133,134，135,136，137,138,139
                             145,147
                             150,151,152,153,155,156,157,158,159
                             180,181,182,183,185,186,187,188,189

String类中有判断某个字符串是否符合xx规则的方法？
public boolean matches(String regex)：告知此字符串是否匹配给定的正则表达式。


 */
public class TestStringMethod6 {


    @Test
    public void test06(){
        //密码要求：必须有大写字母，小写字母，数字组成，6-16位
        String str1 = "Cly892";
        String str2 = "li56n5";//没有大写字母
        String str3 = "CLY885";//没有小写字母
        String str4 = "8c6L7agag";
        System.out.println(str1.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[A-Za-z0-9]{6,16}$"));//true
        //.*任意字符出现0次或多次
        System.out.println(str2.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[A-Za-z0-9]{6,16}$"));//false
        System.out.println(str3.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[A-Za-z0-9]{6,16}$"));//false
        System.out.println(str4.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[A-Za-z0-9]{6,16}$"));//false
    }


    @Test
    public void test04(){
        //密码要求：必须有大写字母，小写字母，数字组成，6位
        String str1 = "Cly892";
        String str2 = "li56n5";//没有大写字母
        String str3 = "CLY885";//没有小写字母
        String str4 = "8c6L7agag";//长度不合适
        System.out.println(str1.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[A-Za-z0-9]{6}$"));//true
        //.*任意字符出现0次或多次
        System.out.println(str2.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[A-Za-z0-9]{6}$"));//false
        System.out.println(str3.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[A-Za-z0-9]{6}$"));//false
        System.out.println(str4.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])[A-Za-z0-9]{6}$"));//true
    }

    @Test
    public void test03(){
        //验证用户名和密码，要求第一个字必须为字母，一共6~16位字母数字下划线组成
        String str = "";
        System.out.println(str.matches("^[a-zA-Z]\\w{5,15}$"));
    }

    @Test
    public void test02(){
        String str = "16986754258";

        //判断上面的字符串是否是一个合法的手机号码
        System.out.println(str.matches("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$"));
    }

    @Test
    public void test01(){
        String str1 = "ag34tahg5qtq";
        String str2 = "123";

        //判断该字符串中是否是纯数字组成的字符串，即只能有0-9之间的数字组成
        System.out.println(str1.matches("\\d+"));//正则中\d表示数字，但是在Java程序中\有转义的意思，如果要表示普通的\，需要转义
        System.out.println(str2.matches("\\d+"));//正则中\d表示数字，但是在Java程序中\有转义的意思，如果要表示普通的\，需要转义

        //判断该字符串中是否有数字出现
        System.out.println(str1.matches(".*\\d+.*"));//.*任意字符出现0次或多次， \\d+表示数字出现1次或多次
    }
}
