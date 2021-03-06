package test07;

/*
（2）java.net.Socket：套接字
是两台机器间通信的端点。网络通信其实就是Socket间的通信。
无论是服务器端还是客户端，也不管是发送方还是接收方，都必须有一个Socket对象，才能进行网络通信.
又分为：
A：流套接字：用于TCP协议编程
    ServerSocket：只用于服务器端，作用是等待并与客户端建立连接用的Socket。
    Socket：既用以服务器端，又用于客户端，使用真正的传输数据用的Socket。
B：数据报套接字：用于UDP协议编程
    DatagramSocket：不管是服务器端还是客户端，用的类型一样。
 */
public class TestSocket {
}
