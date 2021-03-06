package test02;

import java.io.*;

public class FilesUtils {
    public static void main(String[] args) throws IOException {
//        copy1("d:/1.txt", "d:/2.txt");

        copy2("d:/1.txt", "d:/2.txt");
        copy2("d:/1.jpg", "d:/2.jpg");
        //读取文件时，文件不存在会报错
        //写文件时，文件不存在会自动创建
    }

    //假设是用于纯文本文件的复制，那么可以使用FileReader和FileWriter，局限性是很明显
    public static void copy1(String srcFile, String destFile) throws IOException {
        //(1)选择和创建IO流的对象
        FileReader fr = new FileReader(srcFile);
        FileWriter fw = new FileWriter(destFile);

        //（2）调用读和写的方法
        char[] data = new char[10];
        int len;
        while((len = fr.read(data)) != -1){
            fw.write(data, 0, len);
        }

        //（3）关闭
        fr.close();
        fw.close();
    }

    //既可以用于复制纯文本文件，也可以用于复制其他类型的文件，
    //使用FileInputStream和FileOutputStream
    public static void copy2(String srcFile, String destFile) throws IOException {
        //(1)选择和创建IO流的对象
        FileInputStream fis = new FileInputStream(srcFile);
        FileOutputStream fos = new FileOutputStream(destFile);

        //（2）调用读和写的方法
        byte[] data = new byte[10];
        int len;
        while((len = fis.read(data)) != -1){
            fos.write(data, 0, len);
        }

        //（3）关闭
        fis.close();
        fos.close();
    }
}
