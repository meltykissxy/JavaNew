package test01;

import java.io.File;
import java.io.FileFilter;

/*
补充演示一个方法：
File[] listFiles(FileFilter filter)

昨天：
File[] listFiles()

能够获取下一级的File对象，它必须是代表一个文件夹/目录。
 */
public class TestReview {
    public static void main(String[] args) {
        File dir = new File("d:/尚硅谷_200921大数据_柴林燕_JavaSE");

        File[] files = dir.listFiles();
        for (File sub : files) {
            System.out.println(sub);
        }

        System.out.println("----------------------------------");
        //File[] listFiles(FileFilter filter)
        //Filter：过滤，筛选，是一个接口
        File[] ppts = dir.listFiles(new FileFilter() {
            //accept：接受
            @Override
            public boolean accept(File pathname) {//形参pathname代表每一个被过滤的文件或文件夹的路径名
                return pathname.getName().contains("ppt") && pathname.isDirectory();
            }
        });

        for (File ppt : ppts) {
            System.out.println(ppt);
        }
    }
}
