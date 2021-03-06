package com.atguigu.test04;

import org.junit.Test;

/*
（4）常用方法系列3：和截取有关
public String substring(int beginIndex)：从[beginIndex]截取到最后
public String substring(int beginIndex,  int endIndex)：截取[beginIndex, endIndex)
 */
public class TestStringMethod3 {
    @Test
    public void test01(){
        String fileName = "HelloWorld.class";

        //取出字节码文件的名称，不要后缀名
        int beginIndex = 0;
        int endIndex = fileName.indexOf(".");
        String substring = fileName.substring(beginIndex, endIndex);
        System.out.println(substring);

        //截取后缀名
        String ext = fileName.substring(endIndex);
        System.out.println(ext);
    }
}
