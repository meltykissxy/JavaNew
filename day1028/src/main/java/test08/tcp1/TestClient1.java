package test08.tcp1;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TestClient1 {
    public static void main(String[] args) throws Exception{
        //(1)主动连接服务器
        Socket socket = new Socket("192.168.14.88",8888);

        //（2）客户端要给服务器发送数据，需要输出流
        OutputStream outputStream = socket.getOutputStream();
        String str = "hello";
        outputStream.write(str.getBytes());
        socket.shutdownOutput();//只关闭输出通道

        ///（3）接收服务器返回的消息，需要输入流
        InputStream inputStream = socket.getInputStream();
        byte[] data = new byte[1024];
        int len;
        while((len = inputStream.read(data)) != -1){
            System.out.println(new String(data,0,len));
        }

        //（4）断开
        outputStream.close();
        inputStream.close();
        socket.close();
    }
}
