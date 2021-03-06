package test02.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Iterator;

public class TestServer {
    //online：在线的客户端
    private static HashSet<Socket> online = new HashSet<>();

    public static void main(String[] args) throws IOException {
        //1、开启服务器
        ServerSocket server = new ServerSocket(8888);
        
        //2、接收很多客户端的连接
        while(true){
            Socket socket = server.accept();
            String clientIp = socket.getInetAddress().getHostAddress();
            System.out.println(clientIp+"登录成功！");

            //把连接成功的客户端的socket存储online中去
            online.add(socket);

            //给每一个客户端都单独启动一个线程
            MessageThread mt = new MessageThread(socket);
            mt.start();
        }
    }

    private static class MessageThread extends Thread{
        private Socket socket;
        private String ip;

        public MessageThread(Socket socket) {
            this.socket = socket;
        }

        public void run(){
            //加入一件事：当前客户端线程开始工作后，告诉其他客户端，我上线了
            ip = socket.getInetAddress().getHostAddress();
            sentToOther(ip+"上线了");

            //第一件事情：接收本客户端的消息
            //第二件事情：把该消息，通过其他客户端的socket发出去
            try {
                InputStream inputStream = socket.getInputStream();
                //这里聊天，用文本处理，按行处理
                InputStreamReader isr = new InputStreamReader(inputStream);//目的：把字节流转为字符流
                BufferedReader br = new BufferedReader(isr);

                while(true){
                    String message = br.readLine();

                    //把message通过其他客户端的socket发出去
                    sentToOther(ip+"说：" + message);

                    //如果客户端给我的指令是"stop"，就结束
                    if("stop".equalsIgnoreCase(message)){
                        //通过其他客户端的socket发一条，xx下线了
                        sentToOther(ip + "下线了");
                        online.remove(socket);
                        break;
                    }
                }
            } catch (IOException e) {
//            e.printStackTrace();
                sentToOther(ip +"掉线了");
                online.remove(socket);
            }
        }

        //方法：通过其他客户端的socket发生xx消息的方法
        private void sentToOther(String message){
//            for (Socket client : online) {//foreach中，不能调用集合remove方法
            Iterator<Socket> iterator = online.iterator();
            while(iterator.hasNext()){
                Socket client = iterator.next();
                try {
                    //得到某个client的输出流，给该客户端转发消息
                    OutputStream outputStream = client.getOutputStream();
                    PrintStream ps = new PrintStream(outputStream);

                    ps.println(message);
                } catch (IOException e) {
//                    e.printStackTrace();
                    //如果异常，说明client掉线了，或者退出了
                    //从online移除它
//                    online.remove(client);//不能直接调用集合的remove
                    iterator.remove();//必须调用迭代器的remove方法
                }
            }
        }

    }
}
