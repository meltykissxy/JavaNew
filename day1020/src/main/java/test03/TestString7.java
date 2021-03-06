package com.atguigu.test03;

import org.junit.Test;

/*
7、空字符串的判断
（1）什么是空字符串？
（3）如何判断是否是空字符串？
A：if("".equals(str))
B：if(str != null && str.equals(""))
C: if(str != null && str.isEmpty())
D：if(str != null && str.length()==0)


 */
public class TestString7 {
    @Test
    public void test02(){
        testEmpty("");
        testEmpty(null);
        testEmpty(" ");
    }

    public void testEmpty(String str){
        //判断str是否是空字符串
        /*if(str == null){
            System.out.println("没字符串对象，是空值");
        }*/
/*        if(str.equals("")){//不安全
            System.out.println("空字符串");
        }*/
/*        if("".equals(str)){
            System.out.println("空字符串");
        }else{
            System.out.println("不是");
        }*/

/*        if(str != null && str.equals("")){//安全
            System.out.println("空字符串");
        }*/
        /*if(str != null && str.isEmpty()){//安全
            System.out.println("空字符串");
        }*/
        if(str != null && str.length()==0){//安全
            System.out.println("空字符串");
        }
    }


    @Test
    public void test01(){
        String str1;//对于局部变量来说，未初始化
        String str2 = null;//空值，没有对象
        String str3 = "";
        String str4 = new String();
        String str5 = new String("");
        String str6 = " ";//不是

        //上面哪些是空字符串？ str3和str4、str5是空字符串对象
    }
}
