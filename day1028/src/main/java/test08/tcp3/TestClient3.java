package test08.tcp3;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TestClient3 {
    public static void main(String[] args)throws Exception {
        //1、连接服务器
        Socket socket = new Socket("192.168.14.88",8989);

        //2、从键盘输入单词，给服务器发送，并请接收服务器返回的消息
        Scanner input = new Scanner(System.in);

        OutputStream outputStream = socket.getOutputStream();
        InputStream inputStream = socket.getInputStream();

        //为了区别单词与单词，我们决定使用按行处理消息
        PrintStream ps = new PrintStream(outputStream);//目的是为了调用println
        InputStreamReader isr = new InputStreamReader(inputStream);//此处没有编码问题，只是用它转变流的类型
        BufferedReader br = new BufferedReader(isr);//密度是为了调用readLine

        while(true){
            System.out.print("请输入单词：");
            String content = input.next();

            if("stop".equalsIgnoreCase(content)){
                break;
            }

            //给服务器发送过去
            ps.println(content);

            //接收服务器返回的结果
            System.out.println("服务器返回的内容：" + br.readLine());
        }

        //3、关闭
        ps.close();
        outputStream.close();
        br.close();
        isr.close();
        inputStream.close();
        input.close();
        socket.close();

    }
}
