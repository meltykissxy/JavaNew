package test05;


import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.PrintStream;

/*
九、重新认识PrintStream等，（了解）
（1）System.out，System.err对象，都是PrintStream类型
A：void print(Xxx b)  ：支持各种数据类型的数据打印，打印完b不换行
B：void println(Xxx b)  ：支持各种数据类型的数据打印，打印完b换行
C：PrintStream printf(String format, Object... args)

（2）System.out,System.err对象可以重定向
System.setOut(PrintStream out)
System.setErr(PrintStream err)

System.out： public final static PrintStream out = null;
看起来System.out是常量对象，在Java层面一旦赋值是无法修改值的，其实本质上是通过C语言赋值为控制台及其实现重定向
 */
public class TestPrintStream {
    @Test
    public void test04() throws FileNotFoundException {
        System.setOut(new PrintStream("d:/1.txt"));
        System.out.println("hello");//
    }
    @Test
    public void test03(){
        System.out.println("hello");//默认在控制台
    }

    @Test
    public void test02(){
        int a = 1;
        double b = 4.567;
        //printf(String format, Object... args)
        System.out.printf("a=%d,b=%.2f",a,b);//a=1,b=4.57
    }

    @Test
    public void test01(){
        System.out.println("hello");
        //System.out先获取PrintStream的对象，然后调用它的println方法

//        System.out.print();//不写参数错误，因为PrintStream类中，没有定义print()空参的方法
        System.out.println();//对的，因为PrintStream类中，定义println()空参的方法

        int a = 1;
        int b = 2;
//        System.out.println(a,b);//错误，因为PrintStream类中，定义的所有的println方法，形参只有1个
        System.out.println(a+b);//println(int num)
        System.out.println("结果： " + (a+b));//println(String str)

        int[] arr = {1,2,3,4};
        System.out.println(arr);//[I@5d6f64b1  println(String str)，自动调用arr.toString()

        char[] letters = {'a','b','c'};
        System.out.println(letters);//abc  void println(char[] x)
    }
}
