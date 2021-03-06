package test09.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class TestReceiver {
    public static void main(String[] args) throws Exception{
        //(1)创建Socket
        DatagramSocket ds = new DatagramSocket(8989);//需要指定监听的端口号

        //(2)准备DatagramPacket对象来接收数据
//        DatagramPacket(byte[] buf, int length)构造 DatagramPacket，用来接收长度为 length 的数据包。
         byte[] data = new byte[1024];
        DatagramPacket dp = new DatagramPacket(data, data.length);

        //(3）接收包裹
        ds.receive(dp);
        //打印数据
        System.out.println(new String(data, 0, dp.getLength()));

        //(4)关闭
        ds.close();
    }
}
