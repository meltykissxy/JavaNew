package com.atguigu.test03;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/*
2、创建字符串对象
（1）字符串常量对象：直接""引起来
（2）构造器
public String()
public String(String original)
public String(char[] value)
public String(char[] value, int offset,int count)
。。。。
String(byte[] bytes)
String(byte[] bytes, Charset charset)
。。。。
String(StringBuffer buffer)
String(StringBuilder builder)
（3）static String valueOf(数据类型)

（4）任意类型的数据与字符串进行“+”，结果都是字符串；
     任意对象.toString()结果都返回一个字符串。
 */
public class TestString2 {
    @Test
    public void test07(){
        int a = 123;
        String str = "";
        String result = str + a;
        System.out.println(result);
    }

    @Test
    public void test06(){
        String s1 = String.valueOf(123);
        System.out.println(s1);

        String s2 = String.valueOf(123);
        System.out.println(s2);

        System.out.println(s1 == s2);//false
    }


    @Test
    public void test05() throws UnsupportedEncodingException {
        byte[] arr = {-27, -80, -102, -25, -95, -123, -24, -80, -73};//使用UTF-8编码
        String str = new String(arr);
        System.out.println(str);//尚硅谷

        String str2 = new String(arr,"GBK");//使用GBK解码是错误
        System.out.println(str2);//乱码
    }

    @Test
    public void test04(){
        byte[] arr = {97,98,99,100};
        String str = new String(arr);
        System.out.println(str);//abcd
    }

    @Test
    public void test03(){
        char[] arr = {'h','e','l','l','o'};
        String str = new String(arr);
        System.out.println(arr);

        String str2 = new String(arr,0, 3);
        System.out.println(str2);
    }
    @Test
    public void test02(){
        String str = new String("hello");//两个，"hello"，又new了一个
    }

    @Test
    public void test01(){
        String str = "hello";//方式一，字符串常量
    }
}
