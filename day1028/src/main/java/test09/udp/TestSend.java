package test09.udp;

import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
8、基于UDP协议的网络编程：示例一
（1）发送方，给多个接收方，发送一句话：“明天周四考试”
（2）接收方，接收消息，打印
 */
public class TestSend {
    public static void main(String[] args) throws Exception{
        //(1)创建Socket
        DatagramSocket ds = new DatagramSocket();//不需要指定端口号和IP地址
                                                //端口号有系统自动分配，IP就是本机

        //(2)准备发送的数据
        String str = "明天周四考试";
        byte[] data = str.getBytes();

        //（3）把数据打包，发送
        //DatagramPacket(byte[] buf, int length, InetAddress address, int port)
        //参数1：要发送的数据的字节数组，
        //参数2：数据的长度
        //参数3：接收方ip地址，好比包裹上面的收件人地址
        //参数4：接收方端口号，好比裹上面的电话号码
        for (int i=0; i<10; i++) {
            byte[] address = {(byte)192,(byte)168,14,(byte)(88+i)};
            InetAddress ip = InetAddress.getByAddress(address);
            DatagramPacket dp = new DatagramPacket(data, data.length, ip, 8989);

            //发送，必须通过socket
            ds.send(dp);
            System.out.println("发送完"+(i+1)+"个包裹");
        }


        //(4）关闭
        ds.close();
    }
}
