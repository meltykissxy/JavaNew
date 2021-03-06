package test01;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/*
第十四章  File类与IO流
一、java.io.File类
1、File类：文件和目录路径名的抽象表示形式。
==>（1）File类的对象可能表示的是一个文件，也可能表示的是一个目录（文件夹）
   （2）我们在程序中要表示一个文件或目录，是通过“路径名”进行描述的
   例如：D:\尚硅谷_200921大数据_柴林燕_JavaSE
        D:\尚硅谷_200921大数据_柴林燕_JavaSE\API\Java Platform SE 8.chm
        文件是带后缀名（扩展名）

2、路径名中每一级的分隔符
（1）\：原来只有windows识别，一开始windows不识别/，现在windows也识别/
（2）/：其他类型的操作系统或网络网址通用的/（推荐使用(2)这种）
如果想要跨平台无障碍，File也提供了一个常量来表示分隔符：File.separator

3、如何创建File对象
（1）File(String pathname)
（2）File(String parent, String child)
（3）File(File parent, String child)

new对象只是在JVM的堆中创建了一个对象，开辟了一块空间准备用来存储该file对象对应的文件或目录的相关描述信息。
但是不代表对应的 硬盘/网络中确确实实有这个文件或目录存在，可能是不存在。
如果不存在，file对象对应的相关的成员变量（描述信息）就是默认值；
如果存在，file对象对应的相关的成员变量（描述信息）就是真实值；

4、方法们
（1）public boolean exists()：是否存在
（2）public long length()：返回文件的大小，单位是字节，不能直接通过这个方法一步到位获取一个目录的大小
            思考：如果需要得到一个目录的大小，那么怎么办呢？
                要计算该目录中所有文件的大小的总和
（3）public boolean isFile()：判断是否是文件，只有文件存在，并且确实是一个文件，才会返回true，否则都是false
（4）public boolean isDirectory() ：判断是否是目录，只有目录存在，并且确实是一个目录，才会返回true，否则都是false

（5）public String getName()：获取文件或目录的名称
（6）public String getPath()：获取文件或目录的构造路径名
（7）public String getAbsolutePath()：获取文件或目录的绝对路径名
        如果在构造器中指定的就是文件或目录的绝对路径，那么getPath和getAbsolutePath结果一样
        如果在构造器中指定的就是文件或目录的相对路径，那么getPath和getAbsolutePath结果不一样，相对路径是相对于当前的模块或项目
 （8）public String getCanonicalPath()：获取文件或目录的规范路径名
        如果在构造器中指定的就是文件或目录的路径本身就是规范的，那么getCanonicalPath()和getAbsolutePath结果一样
        如果在构造器中指定的就是文件或目录的路径本身是不规范的，那么getCanonicalPath()和getAbsolutePath结果不一样
                那么getCanonicalPath()会对路径中类似于“../"等进行解析，使得路径称为规范的路径
 */
public class TestFile {
    @Test
    public void test10() throws IOException {
        File file = new File("D:/尚硅谷_200921大数据_柴林燕_JavaSE/API/Java Platform SE 8.chm");
        System.out.println("文件构造路径名：" + file.getPath());//D:/尚硅谷_200921大数据_柴林燕_JavaSE/API/Java Platform SE 8.chm
        System.out.println("文件绝对路径名：" + file.getAbsolutePath());//D:/尚硅谷_200921大数据_柴林燕_JavaSE/API/Java Platform SE 8.chm
        System.out.println("文件规范路径名：" + file.getCanonicalPath());//D:/尚硅谷_200921大数据_柴林燕_JavaSE/API/Java Platform SE 8.chm

        File otherFile = new File("d:/1.txt");
        System.out.println("文件构造路径名：" + otherFile.getPath());//d:/1.txt  虽然这个文件不存在，但是因为在构造器中指定了文件名，所以path属性在构造器中初始化
        System.out.println("文件绝对路径名：" + otherFile.getAbsolutePath());//d:/1.txt
        System.out.println("文件规范路径名：" + otherFile.getCanonicalPath());//d:/1.txt

        File file2 = new File("2.txt");//相对路径
        System.out.println("文件构造路径名：" + file2.getPath());//2.txt
        System.out.println("文件绝对路径名：" + file2.getAbsolutePath());
        //D:\atguigu\javaee\JavaSE20200921\code\idea\20200921BigData\day1027_teacher_code\2.txt
        System.out.println("文件规范路径名：" + file2.getCanonicalPath());
        //D:\atguigu\javaee\JavaSE20200921\code\idea\20200921BigData\day1027_teacher_code\2.txt

        File file3 = new File("../../../3.txt");
        System.out.println("文件构造路径名：" + file3.getPath());//..\..\..\3.txt
        System.out.println("文件绝对路径名：" + file3.getAbsolutePath());
        //D:\atguigu\javaee\JavaSE20200921\code\idea\20200921BigData\day1027_teacher_code\..\..\..\3.txt
        System.out.println("文件规范路径名：" + file3.getCanonicalPath());
        //D:\atguigu\javaee\JavaSE20200921\code\3.txt
    }

    @Test
    public void test09() {
        File file = new File("D:/尚硅谷_200921大数据_柴林燕_JavaSE/API/Java Platform SE 8.chm");
        System.out.println("文件路径名：" + file.getPath());//D:/尚硅谷_200921大数据_柴林燕_JavaSE/API/Java Platform SE 8.chm

        File dir = new File("D:/尚硅谷_200921大数据_柴林燕_JavaSE/API");
        System.out.println("目录路径名：" + dir.getPath());//D:/尚硅谷_200921大数据_柴林燕_JavaSE/API

        File otherFile = new File("d:/1.txt");
        System.out.println("文件路径名：" + otherFile.getPath());//d:/1.txt  虽然这个文件不存在，但是因为在构造器中指定了文件名，所以path属性在构造器中初始化
    }

    @Test
    public void test08() {
        File file = new File("D:/尚硅谷_200921大数据_柴林燕_JavaSE/API/Java Platform SE 8.chm");
        System.out.println("文件名：" + file.getName());//Java Platform SE 8.chm

        File dir = new File("D:/尚硅谷_200921大数据_柴林燕_JavaSE/API");
        System.out.println("目录名：" + dir.getName());//API

        File otherFile = new File("d:/1.txt");
        System.out.println("文件名：" + otherFile.getName());//1.txt  虽然这个文件不存在，但是因为在构造器中指定了文件名，所以name属性在构造器中初始化
    }

    @Test
    public void test07(){
        File dir = new File("D:/尚硅谷_200921大数据_柴林燕_JavaSE/API");
        System.out.println(dir.exists());//true
        System.out.println(dir.isDirectory());//true
        System.out.println(dir.length());//4096  ??
    }

    @Test
    public void test06(){
        File file = new File("D:/尚硅谷_200921大数据_柴林燕_JavaSE/API/Java Platform SE 8.chm");
        System.out.println(file.isFile());//true
        System.out.println(file.isDirectory());//false

        File otherFile = new File("d:/1.txt");
        System.out.println(otherFile.isFile());//false
        System.out.println(otherFile.isDirectory());//false
    }

    @Test
    public void test05(){
        File file = new File("D:/尚硅谷_200921大数据_柴林燕_JavaSE/API/Java Platform SE 8.chm");
        System.out.println(file.length());//38754724字节

        File otherFile = new File("d:/1.txt");
        System.out.println(otherFile.length());//0字节
    }

    @Test
    public void test04(){
        File file = new File("D:/尚硅谷_200921大数据_柴林燕_JavaSE/API/Java Platform SE 8.chm");
        System.out.println(file.exists());//true

        File otherFile = new File("d:/1.txt");
        System.out.println(otherFile.exists());//false
    }

    @Test
    public void test03(){
        File parent = new File("D:/尚硅谷_200921大数据_柴林燕_JavaSE/API");
        File file = new File(parent,"Java Platform SE 8.chm");
    }

    @Test
    public void test02(){
        File file = new File("D:/尚硅谷_200921大数据_柴林燕_JavaSE/API","Java Platform SE 8.chm");
    }

    @Test
    public void test01(){
        //方式一：使用"\"，要注意使用两个\\
        File file1 = new File("D:\\尚硅谷_200921大数据_柴林燕_JavaSE\\API\\Java Platform SE 8.chm");
        //方式二：使用“/"
        File file2 = new File("D:/尚硅谷_200921大数据_柴林燕_JavaSE/API/Java Platform SE 8.chm");
        //方式三：使用
        File file3 = new File("D:" +File.separator+"尚硅谷_200921大数据_柴林燕_JavaSE" +File.separator+"API" +File.separator+"Java Platform SE 8.chm");
    }

}
