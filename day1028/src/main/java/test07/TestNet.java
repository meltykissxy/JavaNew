package test07;

import java.net.InetAddress;
import java.net.UnknownHostException;

/*
重要性：
面向对象（>=*****）
集合（*****）
IO（***）
网络编程（*）

第15章 网络编程（非重点）
讲它的目的：（1）为了了解后面的框架，项目的运行的底层的原理和过程（2）顺便复习一下之前学习过的这些多线程、集合、IO等的API
1、软件的结构
C/S：Client客户端，Server服务器端
      需要用户单独安装客户端，然后才能与服务器交互，比如：qq，微信，播放器
B/S：Browser浏览器，Server服务器端
     通过浏览器访问网页，与服务器进行交互，比如：淘宝，京东，新浪等

无论哪种，都离开网络通信。除非是单击版的。

2、网络通信必须有三要素：
（1）IP地址：
作用：定位到某一个主机，无论是服务器端还是客户端，都有自己的IP地址，相当于主机在网络中的唯一标识。
形式：IPV4和IPV6
   IPV4：是一个32位的二进制数，通常被分为4个字节，表示成`a.b.c.d` 的形式，例如`192.168.14.88`
            每一个整数的范围是：0-255
   IPV6：采用128位地址长度，每16个字节一组，分成8组十六进制数，表示成`ABCD:EF01:2345:6789:ABCD:EF01:2345:6789`

   因为随着连接进入网络的设备越来越多，那么大家要唯一的IP就不够用了，需要扩展IP。
   IPV6的宣传语：让世界上每一粒沙子都有自己的IP。

网址中好像没看到IP地址。其实，网址中包含域名。例如：www.atguigu.com，www.baidu.com
域名其实也是对应一个IP地址，不同的域名对应什么IP地址，由域名解析器来进行解析。

用户通常使用的是域名，因为好记。

（2）端口号
作用：在某台主机中定位到某个具体的应用程序
    因为一台主机上，有很多应用程序与网络通信，网卡接收到数据之后，传给哪个应用程序，要靠端口号识别。
表示形式：0~65535
    公认端口：0-1023，范围是被预先定义的服务通信占用，如：HTTP（80），FTP（21），Telnet（23）
    注册端口：1024~49151。分配给用户进程或应用程序。如：Tomcat（8080），MySQL（3306），Oracle（1521）。
    动态/ 私有端口：49152~65535

    注意：同一台主机上，不能同时有两个应用程序使用同一个端口号。

（3）网络协议
作用：在计算机网络中，这些连接和通信的规则被称为网络通信协议，它对数据的传输格式、传输速率、传输步骤等做了统一规定，通信双方必须同时遵守才能完成数据交换。
经典代表：
TCP协议：(Transmission Control Protocol）传输控制协议，面向连接的，可靠的，基于字节流的通信协议。
    面向连接的：在数据传输之前，发送方和接收方必须建立连接，确定连接没问题之后才会正式发送消息。
                如果发现丢了数据包，会让对方重新传。
              建立连接会有一个三次握手的过程：
                    客户端会发出一些请求数据给服务器，
                    服务器收到后，会返回响应的信息，这个时候，客户端就可以确认它给服务器发送数据的线路是没问题；
                    客户端会再次给服务器返回对应信息，服务器也可以确认它给客户端发送数据的线路是没问题；

                    接下去才会正式开始通信。

              断开连接时，还有一个四次挥手的过程：
                    客户端先告知服务器要断开了，
                    服务器就做好准备，不再接收这个客户端的消息，但是会通知客户端，我接下来把剩下的没有传完的数据都给你；
                    服务器等发送完之后，再次告知客户端可以断开了；
                    客户端收到后，彻底与服务器断开；

              优势：安全可靠，适用于大数据的传输
              缺点：麻烦，慢
UDP协议：User Datagram Protocol用户数据报协议，非面向连接的，不可靠的，基于用户数据报的传输协议
            发送方再给接收方传输数据之前，不会去确认对方是否在线，直接发送。如果对方正好在，接收成功，如果对方不在，这个数据报就废弃。
            一般用于：广告批量推送、实时通话，视频会议等。
            如果中间逗乐数据报，不会重传。

            优势：快
            问题：每一个数据报，有大小限制，64K以内。

 */
public class TestNet {
    public static void main(String[] args) throws UnknownHostException {
        System.out.println(InetAddress.getByName("www.baidu.com"));
        //www.baidu.com/182.61.200.7
    }
}
