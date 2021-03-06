package test02;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/*
4、Reader方法
（1）int read()：一次读取1个字符，返回的是读取的字符的编码值，如果到达流末尾返回-1
（2）int read(char[] data)：一次读取多个字符，最多一次读取data.length个，，如果到达流末尾返回-1
                读取的数据存到data中，从data[0]开始存储
（3）int read(char[] data,int offset, int count)：一次读取多个字符，最多一次读取count个，，如果到达流末尾返回-1
                读取的数据存到data中，从data[offset]开始存储
 */
public class TestFileReader {
    @Test
    public void test3() throws IOException {
        //需求：从d:/1.txt读取，
        //这回要用FileReader读取，因为1.txt是纯文本文件，可以直接使用字符流
        //(1)创建IO流，FileReader
        FileReader fr = new FileReader("d:/1.txt");

        //（2）调用读取的方法
        char[] data = new char[10];
        int len;
        while((len = fr.read(data)) != -1){
//            System.out.println(Arrays.toString(data));
            //char[] --> String
            System.out.println(new String(data, 0, len));
        }

        //（3）关闭
        fr.close();
    }

    @Test
    public void test2() throws IOException {
        //需求：从d:/1.txt读取，
        //这回要用FileReader读取，因为1.txt是纯文本文件，可以直接使用字符流
        //(1)创建IO流，FileReader
        FileReader fr = new FileReader("d:/1.txt");

        //（2）调用读取的方法
        char[] data = new char[10];
        System.out.println(fr.read(data));//2 表示读取了2个字符
        System.out.println(Arrays.toString(data));

        //（3）关闭
        fr.close();
    }

    @Test
    public void test() throws IOException {
        //需求：从d:/1.txt读取，
        //这回要用FileReader读取，因为1.txt是纯文本文件，可以直接使用字符流
        //(1)创建IO流，FileReader
        FileReader fr = new FileReader("d:/1.txt");

        //（2）调用读取的方法
        System.out.println(fr.read());//20013，它是“中”字对应编码值

        //（3）关闭
        fr.close();
    }
}
