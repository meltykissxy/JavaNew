package test01;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/*
5、方法们（系列二）
(1) boolean createNewFile()  ：创建一个空文件
(2)boolean mkdir() ：创建一个文件夹/目录
        失败了不会报异常
（3）boolean mkdirs() ：如果某个目录/文件夹的父目录不存在，一并创建
（4） boolean delete() ：可以删除文件和空目录，不能使用delete一步到位删除非空目录
        思考：如何删除非空目录呢？
            先把aa中的文件或文件夹都删除，然后才能删除aa本身。
（5）boolean renameTo(File dest) ：重命名
 */
public class TestFile2 {
    @Test
    public void test07()  {
        File file = new File("d:/aa/bb");
        File other = new File("d:/aa/bbbbb");
        file.renameTo(other);
    }

    @Test
    public void test06()  {
        File file = new File("d:/aa");
        file.delete();//这里想要删除的是aa
    }

    @Test
    public void test05()  {
        File file = new File("d:/aa/bb/cc");
        file.delete();//这里删除的是cc
    }

    @Test
    public void test04() throws IOException {
        File file = new File("d:/1.txt");
        file.delete();

    }
    @Test
    public void test03()  {
        File file = new File("d:/aa/bb/cc");
        file.mkdirs();
        //本来的目的是创建cc文件夹，如果此时aa,bb不存在，也一并创建
    }

    @Test
    public void test02()  {
        File file = new File("d:/temp");
        file.mkdir();
    }

    @Test
    public void test01() throws IOException {
        File file = new File("d:/1.txt");
        file.createNewFile();
    }
}
