package com.atguigu.test01;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/*
（6）常用方法系列5：和编码与解码有关
编码：把字符转为字节

A：public byte[] getBytes()：使用平台的默认字符集将此 String 编码为 byte 序列，并将结果存储到一个新的 byte 数组中。
B：public byte[] getBytes(String charsetName) throws UnsupportedEncodingException：使用指定的字符集将此 String 编码为 byte 序列，并将结果存储到一个新的 byte 数组中。

解码：把字节转为字符
A：public String(byte[] bytes)：通过使用平台的默认字符集解码指定的 byte 数组，构造一个新的 String。
B：public String(byte[] bytes,String charsetName) throws UnsupportedEncodingException：通过使用指定的 charset 解码指定的 byte 数组，构造一个新的 String。
c：public String(byte[] bytes,int offset,int length,String charsetName)throws UnsupportedEncodingException

乱码：
（1）编码与解码的字符集对不上
（2）字节数据多或少了，导致匹配有问题

常见的编码：
（1）ISO8859-1：不支持中文，欧洲国家标准，支持拉丁语系列
        编码后，每一个字符对应1个字节
（2）GBK和GB2312：支持中文
        编码后，每一个字符对应1-2个字节，原来ASCII范围的字符用1个字节，其余用2个字节
（3）UTF-8：支持中文
        编码后，每一个字符对应1-4个字节，原来ASCII范围的字符用1个字节，其余用2-4个字节
 */
public class TestStringMethod5 {
    @Test
    public void test06() throws UnsupportedEncodingException {
        System.out.println("尚".getBytes("ISO8859-1").length);//1
        System.out.println("尚".getBytes("GBK").length);//2
        System.out.println("尚".getBytes("UTF-8").length);//3

        System.out.println(new String("尚".getBytes("ISO8859-1"),"ISO8859-1"));//乱码
        System.out.println(new String("尚".getBytes("GBK"),"GBK"));//尚
        System.out.println(new String("尚".getBytes("UTF-8"),"UTF-8"));//尚
    }


    @Test
    public void test05() throws UnsupportedEncodingException {
        byte[] data = {-50, -46, -80, -82, -55, -48, -71, -24, -71, -56};
        /*
        11001110
        11010010
        10110000
        10101110
        11001001
        11010000
        10111001
        11101000
        10111001
        11001000
         */
        String str = new String(data,1,5,"GBK");//-46, -80, -82, -55, -48
        System.out.println(str);
    }

    @Test
    public void test04() throws UnsupportedEncodingException {
        byte[] data = {-50, -46, -80, -82, -55, -48, -71, -24, -71, -56};
        /*
        11001110
        11010010
        10110000
        10101110
        11001001
        11010000
        10111001
        11101000
        10111001
        11001000
         */
        String str = new String(data,"GBK");
        System.out.println(str);
    }

    @Test
    public void test03(){
        byte[] data = {-50, -46, -80, -82, -55, -48, -71, -24, -71, -56};
        /*
        11001110
        11010010
        10110000
        10101110
        11001001
        11010000
        10111001
        11101000
        10111001
        11001000
         */
        String str = new String(data);//平台默认的字符集  UTF-8
        System.out.println(str);
    }


    @Test
    public void test02() throws UnsupportedEncodingException {
        String str = "我爱尚硅谷";
        //对字符串进行编码
        byte[] bytes = str.getBytes("GBK");
        System.out.println(Arrays.toString(bytes));//[-50, -46, -80, -82, -55, -48, -71, -24, -71, -56]
        for (int i = 0; i < bytes.length; i++) {
            System.out.println(Integer.toBinaryString(bytes[i]).substring(24));
        }
        System.out.println(bytes.length);//15
    }
    @Test
    public void test01(){
        String str = "我爱尚硅谷";
        //对字符串进行编码
        byte[] bytes = str.getBytes();//平台默认的是UTF-8
        System.out.println(Arrays.toString(bytes));//[-26, -120, -111, -25, -120, -79, -27, -80, -102, -25, -95, -123, -24, -80, -73]
        for (int i = 0; i < bytes.length; i++) {
            System.out.println(Integer.toBinaryString(bytes[i]).substring(24));
        }
        /*
        11100110
        10001000
        10010001
        11100111
        10001000
        10110001
        11100101
        10110000
        10011010
        11100111
        10100001
        10000101
        11101000
        10110000
        10110111
         */
        System.out.println(bytes.length);//15
    }
}

