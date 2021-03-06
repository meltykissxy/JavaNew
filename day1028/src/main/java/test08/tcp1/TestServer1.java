package test08.tcp1;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
4、基于TCP协议的网络编程：示例一
需求：
（1）客户端去连接服务器，
（2）给服务器发送一个字符串："hello"
（3）服务器收到客户端的消息后，返回："hi”
（4）断开了
 */
public class TestServer1 {
    public static void main(String[] args) throws Exception{
        //(1)服务器先开启监听，等着客户端的连接
        ServerSocket server = new ServerSocket(8888);
        //ServerSocket不负责传输数据，只负责建立连接

        //(2)等待并接受客户端的连接
        Socket socket = server.accept();
        //socket才是真正传输数据用的

        System.out.println("连接成功");

        //（3）服务器端要接收数据
        //需要输入流
        InputStream inputStream = socket.getInputStream();
        byte[] data = new byte[1024];
        int len;
        while((len = inputStream.read(data)) != -1){
            System.out.println(new String(data,0,len));
        }

        //（4）回复消息，需要输出流
        OutputStream outputStream = socket.getOutputStream();
        String str = "hi";
        outputStream.write(str.getBytes());

        //（5）断开
        outputStream.close();
        inputStream.close();
        socket.close();
        server.close();
    }
}
