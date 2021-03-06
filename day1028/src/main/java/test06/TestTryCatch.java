package test06;

import org.junit.jupiter.api.Test;

import java.io.*;

/*
十、IO相关的异常处理
用原来的try...catch...finally可以实现合适的异常处理和关闭，但是比较麻烦。
现在要学习在JDK1.7之后引入的一种新的try..catch形式。

语法格式：
    try(
        IO流等资源对象的声明
        ){
            业务逻辑处理代码
     }catch(异常类型 e){
        异常处理
     }

  （1）   ()中的资源对象有一个要求，必须是实现了Closeable接口的，表示可以关闭的资源对象。
  （2） 在()中声明的可关闭资源对象，就不需要我们程序员手动进行close了，它会在用完之后，自动关闭。
 */
public class TestTryCatch {
    @Test
    public void test02(){
        try(
            FileInputStream fis = new FileInputStream("d:/1.txt");
            InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
            BufferedReader br = new BufferedReader(isr);

            FileOutputStream fos = new FileOutputStream("d:/2.txt");
            OutputStreamWriter osw = new OutputStreamWriter(fos,"UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);
        ) {
            String data;
            while ((data = br.readLine()) != null) {
                bw.write(data);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test01(){
        //（1）这些变量从try中提取出来声明，因为finally中还要用这些变量
        //（2）fis等，要初始化为null，因为如果没有=null，对于finally中使用来说，可能会未初始化
        //因为有可能  fis = new FileInputStream("d:/1.txt");就发生异常，那么剩下的try中的代码 都不走了，那么即所有变量都没有初始化
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            fis = new FileInputStream("d:/1.txt");
            isr = new InputStreamReader(fis,"UTF-8");
            br = new BufferedReader(isr);

            fos = new FileOutputStream("d:/2.txt");
            osw = new OutputStreamWriter(fos,"UTF-8");
            bw = new BufferedWriter(osw);

            String data;
            while((data = br.readLine()) != null){
                bw.write(data);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //（3）为什么不一起try，因为如果bw.close();异常了，后面就没有关闭彻底
            /*try {
                bw.close();
                osw.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }*/

            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                osw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                isr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
