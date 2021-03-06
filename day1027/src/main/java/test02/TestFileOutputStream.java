package test02;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/*
3、OutputStream方法
（1）void write(int b)：一次写一个字节
（2）void write(byte[] data)：一次写多个字节，把data字节数组全部输出
（3）void write(byte[] data, int offset, int count)：一次写多个字节，从data[offset]开始，一共写count个
 */
public class TestFileOutputStream {
    @Test
    public void test02() throws IOException {
        String str = "world";//把字符串写到d:/1.txt中去

        //(1)创建IO流，FileOutputStream
        FileOutputStream fos = new FileOutputStream("d:/1.txt",true);//true表示在文件现有内容基础上追加新的内容

        //(2)调用write方法
        //把String==>byte[]
        fos.write(str.getBytes());

        //（3）关闭
        fos.close();
    }

    @Test
    public void test01() throws IOException {
        String str = "hello";//把字符串写到d:/1.txt中去

        //(1)创建IO流，FileOutputStream
        FileOutputStream fos = new FileOutputStream("d:/1.txt");

        //(2)调用write方法
        //把String==>byte[]
        fos.write(str.getBytes());

        //（3）关闭
        fos.close();
    }
}
