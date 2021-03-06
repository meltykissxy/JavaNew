package test03;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Scanner;

/*
四、缓冲IO流
1、类型
BufferedInputStream：字节输入缓冲流，负责给InputStream系列的IO流增加缓冲功能
BufferedOutputStream：字节输出缓冲流，负责给OutputStream系列的IO流增加缓冲功能
BufferedReader：字符输入缓冲流，负责给Reader系列的IO流增加缓冲功能
BufferedWriter：字符输出缓冲流，负责给Writer系列的IO流增加缓冲功能

2、演示

3、使用缓冲IO流的目的：提高读/写的效率

4、另外补充
BufferedReader和BufferedWriter除了增加缓冲功能以外，因为他们增加了：
BufferedReader：readLine()，按行读
BufferedWriter：newLine()，输出换行符

5、注意
close()和flush()

如果你后面还要用这个输出流（特别是缓冲流），暂时不想关闭，但是有想要数据及时出去，就先调用flush()。
如果你后面已经不再使用这个输出流了，请调用close()。

 */
public class TestBuffered {
    @Test
    public void test04() throws IOException {
        String str = "hello";

        FileOutputStream fos = new FileOutputStream("d:/1.txt");
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        bos.write(str.getBytes());

        //没写close
        //"hello"写出去，写到了缓冲区中，缓冲区可以存储8192个字节，没满，如果你没有close，就没有刷新缓冲区，数据还在缓冲区
        //要么close，要么flush
        bos.flush();

        //就算flush，最后严格来说，还是应该close，没有close的话，资源没有彻底释放
    }

    @Test
    public void test03() throws IOException {
        //写数据到一个纯文本文件
        //从键盘输入很多句话，写到"d:/1.txt
        Scanner input = new Scanner(System.in);
        FileWriter fw = new FileWriter("d:/1.txt");
        BufferedWriter bw = new BufferedWriter(fw);

        while(true){
            System.out.print("请输入你要保持的话：");
            String content = input.nextLine();

            bw.write(content);
            bw.newLine();//输出换行符

            System.out.print("是否结束？y/n");
            char confirm = input.nextLine().charAt(0);
            if(confirm == 'y'){
                break;
            }
        }

        //关闭
        bw.close();
        fw.close();
    }


    @Test
    public void test02() throws IOException {
        //读取一个纯文本文件
        FileReader fr = new FileReader("d:/1.txt");
        BufferedReader br = new BufferedReader(fr);

        String line;
        while((line = br.readLine()) != null){
            System.out.println(line);
        }

        //关闭
        br.close();
        fr.close();
    }

    @Test
    public void test01() throws IOException {
        //还是复制一个文件
        copy2("D:\\software\\IDEA\\ideaIU-Ultimate-2019.2.3.exe", "d:\\idea.exe");
//        copy3("D:\\software\\IDEA\\ideaIU-Ultimate-2019.2.3.exe", "d:\\idea.exe");
    }

    public static void copy2(String srcFile, String destFile) throws IOException {
        long start = System.currentTimeMillis();
        //(1)选择和创建IO流的对象
        FileInputStream fis = new FileInputStream(srcFile);
        FileOutputStream fos = new FileOutputStream(destFile);

        //（2）调用读和写的方法
        byte[] data = new byte[1024];
        int len;
        while((len = fis.read(data)) != -1){
            fos.write(data, 0, len);
        }

        //（3）关闭
        fis.close();
        fos.close();

        long end = System.currentTimeMillis();
        System.out.println("复制的时间：" + (end-start));//8001
    }

    public static void copy3(String srcFile, String destFile) throws IOException {
        long start = System.currentTimeMillis();
        //(1)选择和创建IO流的对象
        FileInputStream fis = new FileInputStream(srcFile);
        BufferedInputStream bis = new BufferedInputStream(fis);

        FileOutputStream fos = new FileOutputStream(destFile);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        //数据：srcFile文件 --> fis --> bis -->  data --> bos --> fos --> destFile

        //（2）调用读和写的方法
        byte[] data = new byte[1024];
        int len;
        while((len = bis.read(data)) != -1){
            bos.write(data, 0, len);
        }

        //（3）关闭
        bis.close();
        bos.close();
        fis.close();
        fos.close();

        long end = System.currentTimeMillis();
        System.out.println("复制的时间：" + (end-start));//1578
    }
}
