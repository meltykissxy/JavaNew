package test04;

import org.junit.Test;

import java.io.*;

/*
五、转换IO流：编码与解码IO流
1、什么情况下使用转换IO流（编码与解码IO流）
当我们读或写数据时，编码与当前程序，或者源文件与目标文件编码不一致时，可以考虑使用转换IO流。

2、流的类型
InputStreamReader：在读取数据的过程中，解码，并且把字节流转为字符流
            从流的类型看，包装的是字节流，InputStreamReader字符流
                数据是从字节-->字符方式
OutputStreamWriter：在输出数据的过程中间，编码，
         从流类型看，包装的是字节流，OutputStreamWriter本身是字符流
                数据是从字符-->字节方式

3、注意：IO流的关闭顺序
关闭的顺序与创建的顺序是相反的

4、提示
如果缓冲流在前   其他字节输入流 --> BufferedInputStream --> InputStreamReader  字符方式
如果缓冲流在后   其他字节输入流 --> InputStreamReader --> BufferedReader   字符方式

如果缓冲流在前   其他字节输出流 --> BufferedOutputStream -> OutputStreamWriter  字符方式
如果缓冲流在后   其他字节输出流 --> OutputStreamWriter -> BufferedWriter   字符方式

 */
public class TestTransfer {
    public void copy6(String srcFile, String srcCharsetName, String destFile, String destCharsetName)throws IOException{
        //比喻穿衣服和脱衣服的过程  fis是先穿的，再bis，最后是isr，好比fis是内衣，bis是毛衣，isr是外套
        FileInputStream fis = new FileInputStream(srcFile);
        InputStreamReader isr = new InputStreamReader(fis,srcCharsetName);
        BufferedReader br = new BufferedReader(isr);
        //srcFile ==> fis ==> 解码操作 ==> isr==>br ==>data

        FileOutputStream fos = new FileOutputStream(destFile);
        OutputStreamWriter osw = new OutputStreamWriter(fos,destCharsetName);
        BufferedWriter bw = new BufferedWriter(osw);
        //data==>bw==>osw==>编码操作==>fos==>destFile

        String data;
        while((data = br.readLine()) != null){
            bw.write(data);
            bw.newLine();
        }

        //输入流和输出流，先关谁都可以
        bw.close();
        osw.close();
        fos.close();

        //脱衣服
        br.close();
        isr.close();
        fis.close();
    }

    public void copy5(String srcFile, String srcCharsetName, String destFile, String destCharsetName)throws IOException{
        InputStreamReader isr = new InputStreamReader(new BufferedInputStream(new FileInputStream(srcFile)),srcCharsetName);
        OutputStreamWriter osw = new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(destFile)),destCharsetName);

        char[] data = new char[1024];
        int len;
        while((len = isr.read(data))!=-1){
            osw.write(data,0, len);
        }

        osw.close();
        isr.close();
    }

    @Test
    public void test06()throws IOException{
        copy4("d:/1.txt","UTF-8","d:/2.txt","GBK");
    }
    //能够高效的复制文本文件，并且在复制过程中，还能指定编码
    //srcFile：源文件路径名
    //srcCharsetName：源文件编码方式
    //destFile：目标文件路径名
    //destCharsetName：目标文件编码方式
    public void copy4(String srcFile, String srcCharsetName, String destFile, String destCharsetName)throws IOException{
        //比喻穿衣服和脱衣服的过程  fis是先穿的，再bis，最后是isr，好比fis是内衣，bis是毛衣，isr是外套
        FileInputStream fis = new FileInputStream(srcFile);
        BufferedInputStream bis = new BufferedInputStream(fis);
        InputStreamReader isr = new InputStreamReader(bis,srcCharsetName);
        //srcFile ==> fis ==> bis ==>解码操作==> isr==>data

        FileOutputStream fos = new FileOutputStream(destFile);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        OutputStreamWriter osw = new OutputStreamWriter(bos,destCharsetName);
        //data==>osw==>编码操作==>bos==>fos

        char[] data = new char[1024];
        int len;
        while((len = isr.read(data))!=-1){
            osw.write(data,0, len);
        }

        //输入流和输出流，先关谁都可以
        try {
            osw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //脱衣服
        //fis是内衣，bis是毛衣，isr是外套
        isr.close();
        bis.close();
        fis.close();
    }


    @Test
    public void test05()throws IOException{
        //复制文件：d:/1.txt是UTF-8编码的文件，d:/2.txt是GBK编码的文件
        FileInputStream fis = new FileInputStream("d:/1.txt");
//        InputStreamReader isr = new InputStreamReader(fis);//这里不用指定编码，没指定，默认使用平台，平台当前就是UTF-8
        InputStreamReader isr = new InputStreamReader(fis,"UTF-8");//这里手动指定UTF-8也可以

        FileOutputStream fos = new FileOutputStream("d:/2.txt",true);
        OutputStreamWriter osw = new OutputStreamWriter(fos,"GBK");
        //d:/1.txt ==> fis==>字节->解码->字符-->isr ==> data ==> osw==>(字符)  编码（GBK）  字节==>fos==>d:/2.txt

        char[] data = new char[1024];
        int len;
        while((len=isr.read(data))!=-1){
            osw.write(data,0,len);
        }

        isr.close();
        osw.close();
        fis.close();
        fos.close();
    }

    @Test
    public void test04() throws IOException {
        //从d:/2.txt是GBK编码的文件读取数据
        //当前程序是UTF-8
        FileInputStream fis = new FileInputStream("d:/2.txt");//FileInputStream读取过程中，不涉及编码文件，只是原样读取字节
        InputStreamReader isr = new InputStreamReader(fis,"GBK");
        //数据：d:/2.txt ==>(字节原样过来）--> fis （字节）-->解码-->（字符）  isr-->程序data

        char[] data = new char[1024];
        int len;
        while((len=isr.read(data))!=-1){
            System.out.println(new String(data, 0, len));
        }

        isr.close();
        fis.close();
    }

    @Test
    public void test03() throws IOException {
        //从d:/2.txt是GBK编码的文件读取数据
        //当前程序是UTF-8
        FileInputStream fr = new FileInputStream("d:/2.txt");//FileInputStream读取过程中，不涉及编码文件，只是原样读取字节
        byte[] data = new byte[1024];
        int len;
        while((len=fr.read(data))!=-1){
            System.out.println(new String(data, 0, len,"GBK"));//在构建字符串时解码
        }

        fr.close();
    }

    @Test
    public void test02() throws IOException {
        //从d:/2.txt是GBK编码的文件读取数据
        //当前程序是UTF-8
        FileReader fr = new FileReader("d:/2.txt");//FileReader默认在读取过程中，就是用平台的（当前程序）的编码方式进行解码
        char[] data = new char[1024];
        int len;
        while((len=fr.read(data))!=-1){
            System.out.println(new String(data, 0, len));
        }

        fr.close();
    }

    @Test
    public void test01() throws IOException {
        //复制文件：d:/1.txt是UTF-8编码的文件，d:/2.txt是GBK编码的文件
        FileInputStream fis = new FileInputStream("d:/1.txt");
        FileOutputStream fos = new FileOutputStream("d:/2.txt",true);

        byte[] data = new byte[1024];
        int len;
        while((len=fis.read(data))!=-1){
            fos.write(data,0,len);
        }

        fis.close();
        fos.close();
    }

}
