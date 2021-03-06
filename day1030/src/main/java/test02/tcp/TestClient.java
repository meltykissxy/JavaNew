package test02.tcp;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TestClient {
    public static void main(String[] args) throws IOException {
        //1、连接服务器
        Socket socket = new Socket("192.168.14.82",8888);

        //2、启动发送消息的线程
        SendThread st = new SendThread(socket);
        st.start();

        //3、启动收消息线程
        ReceiverThread rt = new ReceiverThread(socket);
        rt.start();

        try {
            st.join();//发消息线程结束了，main线程继续
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        rt.exit();//让收消息线程结束

        try {
            rt.join();//等我们的 收消息线程彻底结束了，socket再关闭
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //关闭socket
        socket.close();
    }
}
//发送消息的线程
class SendThread extends Thread{
    private Socket socket;

    public SendThread(Socket socket) {
        this.socket = socket;
    }

    public void run(){
        try {
            //获取给服务器发消息的输出流
            OutputStream outputStream = socket.getOutputStream();
            //聊天的话，按行处理方便一点。（不带图片等）
            PrintStream ps = new PrintStream(outputStream);

            //需要键盘输入
            Scanner input = new Scanner(System.in);
            while(true){
                System.out.print("请输入你要发送的消息：");
                String message = input.nextLine();

                //给服务器发送过去
                ps.println(message);

                //假设这里是输入stop结束
                if("stop".equalsIgnoreCase(message)){
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//接收消息的线程
class ReceiverThread extends Thread{
    private Socket socket;
    private boolean flag = true;

    public ReceiverThread(Socket socket) {
        this.socket = socket;
    }

    public void run(){
        try {
            //获取从服务器接收消息的输入流对象
            InputStream inputStream = socket.getInputStream();
            //这里聊天，用文本处理，按行处理
            InputStreamReader isr = new InputStreamReader(inputStream);//目的：把字节流转为字符流
            BufferedReader br = new BufferedReader(isr);

            while(flag){
                String message = br.readLine();
                System.out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //调用这个方法，可以修改flag的值
/*    public void setFlag(boolean flag) {
        this.flag = flag;
    }*/

    public void exit(){
        flag = false;
    }
}