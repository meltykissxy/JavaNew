package test02;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

/*
三、文件IO流
1、文件IO流分为4个：
FileInputStream：以字节为单位从文件读取数据
FileOutputStream：以字节为单位把数据输出到文件
FileReader：以字符为单位从文件读取数据
FileWriter：以字符为单位把数据输出到文件

2、InputStream方法
（1） int read()：一次读取1个字节，返回的是读取的字节值
（2）int read(byte[] data)：一次读取多个字节，最多读取data.length个字节，返回实际读取的字节的总数，如果流已经没有数据了，到达流末尾了，还得的话就返回-1
                        上面读取的数据存到data中，从data[0]开始存储
（3）int read(byte[] data, int offset, int count)：一次读取多个字节，最多读取count个，返回实际读取的字节的总数，如果流已经没有数据了，到达流末尾了，还得的话就返回-1
                    上面读取的数据存到data中，从data[offset]开始存储

 */
public class TestFileInputStream {
    @Test
    public void test05() throws IOException {
        //(1)创建IO流
        FileInputStream fis = new FileInputStream("D:\\Download\\img\\单身狗.jpg");//读取图片的数据没问题，但是无法再控制台显示图片

        //(2)调用read方法
        byte[] data = new byte[10];
        //循环读取，并且重复使用data数组
        int len;
        while((len = fis.read(data)) != -1){
            System.out.println(Arrays.toString(data));//看不懂，不是符号我们的阅读习惯

        }

        //(3)关闭
        fis.close();
    }

    @Test
    public void test04() throws IOException {
        //(1)创建IO流
        FileInputStream fis = new FileInputStream("D:/1.txt");

        //(2)调用read方法
        byte[] data = new byte[10];
        //循环读取，并且重复使用data数组
        int len;
        while((len = fis.read(data)) != -1){
//            System.out.println(Arrays.toString(data));//看不懂，不是符号我们的阅读习惯
            //我想要在控制台显示字符的内容
            //byte[] --> String
            System.out.println(new String(data,0, len));
        }

        //(3)关闭
        fis.close();
    }

    @Test
    public void test03() throws IOException {
        //(1)创建IO流
        FileInputStream fis = new FileInputStream("D:/1.txt");

        //(2)调用read方法
        byte[] data = new byte[10];
        //循环读取，并且重复使用data数组
        int len;
        while((len = fis.read(data)) != -1){
            System.out.println(Arrays.toString(data));
        }

        //(3)关闭
        fis.close();
    }

    @Test
    public void test02() throws IOException {
        //(1)创建IO流
        FileInputStream fis = new FileInputStream("D:/1.txt");

        //(2)调用read方法
        byte[] data = new byte[10];
        int len = fis.read(data);//返回6
        System.out.println("len = " +len);
        System.out.println(Arrays.toString(data));

        System.out.println(fis.read(data));//读完了，已经到达流末尾了，再读，就返回-1

        //(3)关闭
        fis.close();
    }

    @Test
    public void test01() throws IOException {//FileNotFoundException是IOException的子类
        //演示FileInputStream的使用
        //需求：用这个IO流，从d:/1.txt读取内容
        //(1)创建IO流
        FileInputStream fis = new FileInputStream("D:/1.txt");

        //(2)调用read方法
        System.out.println(fis.read());
        System.out.println(fis.read());
        System.out.println(fis.read());
        System.out.println(fis.read());
        System.out.println(fis.read());
        System.out.println(fis.read());
        System.out.println(fis.read());

        //(3)关闭
        fis.close();
    }
}
