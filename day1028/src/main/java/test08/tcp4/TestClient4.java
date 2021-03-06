package test08.tcp4;

import javax.jws.soap.SOAPBinding;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TestClient4 {
    public static void main(String[] args) throws Exception{
        //1、连接服务器
        Socket socket = new Socket("192.168.14.88",9999);

        //2、指定要上传的文件
        //目前没有学习过html，也没讲图形化界面，只能通过键盘输入（在控制台输入文件的路径名）
        Scanner input = new Scanner(System.in);
        System.out.print("请指定你要上传的文件的完整路径名：");
        String filepath = input.nextLine();
        //D:\Download\img\单身狗.jpg

        File file = new File(filepath);
        String filename = file.getName();

        //3、给服务器发送文件名
        OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(outputStream);
        dos.writeUTF(filename);

        //4、发送文件内容
        FileInputStream fis = new FileInputStream(filepath);
        BufferedInputStream bis = new BufferedInputStream(fis);
        byte[] data = new byte[1024];
        int len;
        while((len = bis.read(data)) != -1){
            dos.write(data, 0 , len);
        }
        socket.shutdownOutput();//告知服务器我的文件内容已经上传完毕，不再发送

        //5、接收服务器结果
        InputStream inputStream = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(isr);
        System.out.println("结果" + br.readLine());

        br.close();
        isr.close();
        inputStream.close();
        bis.close();
        fis.close();
        dos.close();
        outputStream.close();
        input.close();
    }
}
