package test02;

import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;

/*
5、Writer方法
（1）write(int c)：一次写一个字符
（2）write(char[] data)：一次写一个char[]，整个char[]的所有字符
（3）write(char[] data,int offset,int count)：一次写多个字符，从data数组的[offset]开始，count个
（4）write(String str)；直接写字符串，因为String底层就是char[]
（5）write(String str, int offset, int count)：一次写多个字符，从str数组的[offset]开始，count个
 */
public class TestFileWriter {
    @Test
    public void test2() throws IOException {
        String str = "好的";

        //把str中的内容写到d:/1.txt
        //(1)创建IO流
        FileWriter fw = new FileWriter("d:/1.txt",true);//true，追加

        //(2)调用write方法
        fw.write(str);

        //(3)关闭
        fw.close();
    }

    @Test
    public void test() throws IOException {
        String str = "今天的IO流的类型会比较多，大家要看清楚每一个IO流的作用";

        //把str中的内容写到d:/1.txt
        //(1)创建IO流
        FileWriter fw = new FileWriter("d:/1.txt");

        //(2)调用write方法
        fw.write(str);

        //(3)关闭
        fw.close();
    }
}

