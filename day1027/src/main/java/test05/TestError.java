package test05;

import java.io.FileInputStream;
import java.io.IOException;

public class TestError {
    public static void main(String[] args) throws IOException {
        //java.io.FileNotFoundException: d:\atguigu (拒绝访问。)
        //d:/atguigu是文件夹，不能直接读数据，必须定位到文件才能读数据
        FileInputStream fis = new FileInputStream("d:/atguigu");
        System.out.println(fis.read());
        fis.close();
    }
}
