package test08.tcp2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/*
5、基于TCP协议的网络编程：示例二
需求：
   （1）客户端连接服务器之后，从键盘输入单词、词语等，给服务器发送。
        直到输入的是stop为主
    （2）服务器收到一个单词、词语之后，就把它“反转”，例如：1234，反转4321，并且返回给客户端
 */
public class TestServer2 {
    public static void main(String[] args) throws Exception{
        //1、开启服务器
        ServerSocket server = new ServerSocket(8989);

        //2、等待客户端连接
        Socket socket = server.accept();

        //3、接收客户端的词语，反转，返回
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        //既然是处理单词、词语，那么说明是纯文本，使用字符流更方便
        //为了处理方便，我们考虑一个单词、词语一行，即按行处理     BufferedReader类中有readLine() 或Scanner 类中有nextLine()
        //socket中只能获取字节流，BufferedReader是字符流，怎么办？  学过InputStreamReader 字节流->字符流
        InputStreamReader isr = new InputStreamReader(inputStream);//此处没有编码问题，只是用它转变流的类型
        BufferedReader br = new BufferedReader(isr);

        //输出给对方时，也要考虑对象是否能够按行读取，使用BufferedWriter（newLine），PrintStream（println）
        PrintStream ps = new PrintStream(outputStream);//目的是为了调用println
        String word;
        //循环读取单词
        while((word = br.readLine()) != null){
            //反转word中的单词，String类中没有反转的方法，StringBuilder和StringBuffer中有reverse方法
            StringBuilder s = new StringBuilder(word);
            s.reverse();

            //返回给客户端
            ps.println(s.toString());
        }

        //4、关闭
        ps.close();
        outputStream.close();
        br.close();
        isr.close();
        inputStream.close();
        socket.close();
        server.close();
    }
}
