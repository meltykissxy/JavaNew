package test01;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/*
6、方法们（系列3）
（1）String getParent()
（2）File getParentFile()

以下两个方法适用于file对象代表的是文件夹，才有意义。因为文件本身没有下一级。
（3）String[] list()
（4）File[] listFiles()
 */
public class TestFile3 {
    //需求：删除一个非空目录d:/temp
    @Test
    public void test07() {
        File dir = new File("d:/temp");
        //dir.delete();//失败，但不报异常，因为temp非空
        forceDeleteDir(dir);
    }

    public void forceDeleteDir(File file){
        if(!file.exists()){//如果file对象代表的目录不存在，那么不用操作
            return;
        }
        if(file.isFile()){//如果file对象代表的是一个文件，那么直接干掉
            file.delete();
            return;
        }

        //走到这里，说明file代表一个存在的文件夹
        //先把file的下一级干掉，再干掉file自己
        File[] files = file.listFiles();
        if(files != null) {
            for (File sub : files) {
                //sub是file的下一级
                forceDeleteDir(sub);
            }
        }
        file.delete();//删除file自己
    }


    //需求：获取d:/尚硅谷_200921大数据_柴林燕_JavaSE文件夹的大小
    @Test
    public void test06(){
        File dir = new File("d:/尚硅谷_200921大数据_柴林燕_JavaSE");
//        System.out.println("文件夹大小：" + dir.length());//错误的，结果不确定

        System.out.println("文件夹大小：" + sum(dir));//1884194918
    }

    public long sum(File file){
        if(!file.exists()){//如果file对象代表的目录不存在，那么没有大小
            return 0;
        }
        if(file.isFile()){//如果file对象代表的是一个文件，那么直接返回文件的大小
            return file.length();
        }

        //走到这里，说明file代表一个存在的文件夹
        //累计file的所有下一级的大小的总和
        long he = 0;
        File[] files = file.listFiles();
        for (File sub : files) {
            //sub是file的下一级
            he += sum(sub);//sum(sub)方法调用，得到sub的大小
        }
        return he;
    }


    //需求：看d:/尚硅谷_200921大数据_柴林燕_JavaSE文件夹的下一级，如果下一级还是一个文件夹，再把子文件夹的下一级也显示出来
    @Test
    public void test05() {
        File dir = new File("d:/尚硅谷_200921大数据_柴林燕_JavaSE");
        listAllSub(dir);
    }

    public void listAllSub(File file){
        if(file.isFile()){//如果file对象代表的是一个文件，那么没有下一级
            return;
        }
        if(!file.exists()){//如果file对象代表的目录不存在，那么没有下一级
            return;
        }

        File[] files = file.listFiles();
        for (File sub : files) {
            //sub可能是文件，也可能是文件夹
            listAllSub(sub);
            System.out.println(sub);
        }

    }

    @Test
    public void test04(){
        File dir = new File("d:/尚硅谷_200921大数据_柴林燕_JavaSE");

        //查看d:/temp中的下一级文件或文件夹
        String[] list = dir.list();
        for (String s : list) {
            System.out.println(s);
        }
    }

    @Test
    public void test03(){
        File dir = new File("d:/temp");

        //查看d:/temp中的下一级文件或文件夹
        String[] list = dir.list();
        for (String s : list) {
            System.out.println(s);
        }
    }


    @Test
    public void test02() throws IOException {
        File file = new File("D:\\temp\\1.txt");
        //file对象代表的是  1.txt
        //现在想要在硬盘中创建1.txt，但是现在temp不存在
//        file.createNewFile();//java.io.IOException: 系统找不到指定的路径。因为d:/temp不存在

        //先判断它的父目录是否存在，如果不存在，先创建父目录，然后创建文件
//        String parent = file.getParent();
        File parentFile = file.getParentFile();
        if(!parentFile.exists()){
            parentFile.mkdirs();
        }
        file.createNewFile();
    }

    @Test
    public void test01(){
        File file = new File("D:\\temp\\1.txt");
        //file对象代表的是  1.txt
        //获取1.txt文件的上一级：D:\temp，它的对象

        String parent = file.getParent();
        System.out.println(parent);

        File parentFile = file.getParentFile();
        System.out.println(parentFile);
    }
}
