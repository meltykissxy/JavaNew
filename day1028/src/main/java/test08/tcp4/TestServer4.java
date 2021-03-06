package test08.tcp4;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/*
7、基于TCP协议的网络编程：示例四
需求：
   （1）客户端指定一个文件上传给服务器
   （2）服务器接收文件之后，存放到upload文件夹中
   （3）服务器接收完文件之后，返回“文件接收完毕”
   （4）支持多个客户端同时上传文件
 */
public class TestServer4 {
    public static void main(String[] args)throws Exception {
        //(1)开启服务器
        ServerSocket server = new ServerSocket(9999);
        int count = 0;

        //（2）等待客户端连接
        while (true) {
            Socket socket = server.accept();//这句代码运行一次，接收一个客户端，产生一个socket对象
            count++;
            System.out.println("第" + count + "个客户端连接成功");
            InetAddress inetAddress = socket.getInetAddress();
            System.out.println("该客户端的IP地址：" + inetAddress.getHostAddress());

            //启动一个线程，维护该客户端的通信
            FileUploadThread ft = new FileUploadThread(socket);
            ft.start();
        }
    }
}
class FileUploadThread extends Thread{
    private Socket socket;

    public FileUploadThread(Socket socket) {
        this.socket = socket;
    }

    public void run(){
        try {
            //接收客户端的文件，保存到upload文件夹中，返回一句话“文件上传完毕”
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            PrintStream ps = new PrintStream(outputStream);

            //如果只是用InputStream，不够强大，因为无法区分：（1）文件名.后缀名（2）文件的内容
            //DataInputStream或ObjectInputStream，即可以接收byte[]数据，有可以readUTF()专门接收字符串
            //根据这个思路，客户端先  把文件名.后缀名传过来，用writeUTF(文件名.后缀名)传过来，我这边用readUTF()读取
            //剩下的内容，write(byte[])传文件内容，这边用read(byte[])接收文件内容
            DataInputStream dis = new DataInputStream(inputStream);
            String fileName = dis.readUTF();

            //如何处理文件名可能重名问题？
            //（1）思路一：每个人有自己的独立的存储空间（收费）
            //（2）思路二：生成唯一的文件名，可以使用时间戳 + 原文件名.后缀名  等方式
            long timeMillis = System.currentTimeMillis();
            FileOutputStream fos = new FileOutputStream("upload/" + timeMillis+ fileName);

            //接收文件内容，并且保存到upload文件夹中
            byte[] data = new byte[1024];
            int len;
            while((len = dis.read(data)) !=-1){
                fos.write(data, 0 ,len);
            }

            ps.println("文件上传完毕");

            ps.close();
            outputStream.close();
            fos.close();
            dis.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}