package test07;

import org.junit.jupiter.api.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/*
3、相关的API
（1）java.net.InetAddress类：此类表示互联网协议 (IP) 地址。
    InetAddress类没有对外使用的构造器，不能直接创建它的对象的。
    只能调用它的静态方法，来获取它的对象。

    InetAddress.getLocalHost()：获取本地主机的IP地址等信息
    InetAddress.getByName("www.baidu.com")：获取指定主机的IP地址等信息
    InetAddress.getByAddress(byte[])：根据IP的字节数组获取主机信息
 */
public class TestInetAddress {
    @Test
    public void test3() throws UnknownHostException {
        //在网络中，ip地址的整数值范围是：0-255，无符号
        //但是Java中的byte范围:-128~127
        byte[] address = {(byte)192,(byte)168,14,88};
        InetAddress byAddress = InetAddress.getByAddress(address);
        System.out.println(byAddress);///192.168.14.88
    }

    @Test
    public void test2() throws UnknownHostException {
        InetAddress baidu = InetAddress.getByName("www.baidu.com");
        System.out.println(baidu);//主机名/IP地址  //www.baidu.com/182.61.200.7
    }

    @Test
    public void test() throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost);//主机名/IP地址
    }
}
