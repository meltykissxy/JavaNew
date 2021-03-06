package com.atguigu.test01;

import org.junit.Test;

/*

（8）常用方法系列7：和替换有关
public String replace(char oldChar,char newChar)：一个新字符替换一个旧字符
public String replace(CharSequence target,CharSequence replacement)：一个新的字符序列替换旧的字符序列
    CharSequence：字符序列，它是String,StringBuffer,StringBuilder的父接口

                上面两个方法是不支持正则
public String replaceAll(String regex,String replacement)：可以支持正则
public String replaceFirst(String regex,,String replacement)：可以支持正则
 */
public class TestStringMethod8 {
    @Test
    public void test05(){
        String str = "3qth6w6wjh4q4332";

        //把str中的数字去掉
        str = str.replaceFirst("\\d","");//只能去掉第一个
        System.out.println(str);
    }

    @Test
    public void test04(){
        String str = "3qth6w6wjh4q4332";

        //把str中的数字去掉
        str = str.replaceAll("\\d","");
        System.out.println(str);
    }

    @Test
    public void test03(){
        String str = "中国共产党是执政党，中国共产党是伟大的党";

        str = str.replaceAll("共产党","***");//字符串对象不可变，修改需要重新接收

        System.out.println(str);
    }

    @Test
    public void test02(){
        String str = "中国共产党是执政党，中国共产党是伟大的党";

        str = str.replaceFirst("共产党","***");//字符串对象不可变，修改需要重新接收

        System.out.println(str);
    }

    @Test
    public void test01(){
        String str = "中国共产党是执政党，中国共产党是伟大的党";

        str = str.replace("共产党","***");//字符串对象不可变，修改需要重新接收

        System.out.println(str);


    }
}
