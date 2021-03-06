package test07;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/*
(三）和系统有关的类
1、java.lang.System类
常量对象：
    System.out
    System.in
    System.err

    为什么out,err,in不是大写的呢？按理说，常量对象习惯是大写？
    看源码：System.java
    public final static PrintStream out = null;
    public final static InputStream in = null;
    public final static PrintStream err = null;
    从Java角度来说，final声明就是常量，static表示静态的，public是公共的
    这里初始化为null，按照Java的规则，不是常量一旦赋值，就不能修改了。

    原因，在Java层面初始化为null，底层在给System类初始化时，调用了一个native修饰的registerNatives方法，
    通过C语言修改了out,in,err对象的null值，默认out代表的是控制台对象，in代表的键盘输入。
    既然能改，就不是常量对象，这里没有大写。

    这三个“常量”对象，还有set方法可以修改它们。
    public static void setIn(InputStream in)
    public static void setOut(PrintStream out)
    public static void setErr(PrintStream err)

其他方法：
public static long currentTimeMillis()：获取当前系统时间距离1970-1-1 0:0:0 0的毫秒差
public static void exit(int status)：退出JVM
public static void gc()：通知垃圾回收器赶紧回收内存
                注意：不一定一调用，立刻回收。
public static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length)  ：
            它本来不应该在这个类中的，但是JDK1.0的时候，因为还没有java.util.Arrays数组工具类，
            但是JDK1.0时就已经在JRE核心类库的某些方法中，涉及到数组的复制、元素移动等功能，这个方法就被放到了System类。

      实际开发中用于，在数组中插入元素，和从数组中删除元素。

2、java.lang.Runtime类
（1）它是单例类的经典实现，是饿汉式单例实现，它的唯一对象是代表当前程序的运行环境JVM
（2）如何获取它的单例对象
（3）方法
void gc()  ：调用GC
void exit(int status) ：退出JVM
long totalMemory() ：总内存
 long freeMemory() ：空闲内存
 */
public class TestSystem {
    @Test
    public void test08() {
        Runtime runtime = Runtime.getRuntime();
        System.out.println(runtime.totalMemory());//JVM的总内存，512753664字节
        System.out.println(runtime.freeMemory());//JVM的空闲内存，499331784字节
        System.out.println(runtime.totalMemory() - runtime.freeMemory());//JVM已用内存：13421880字节
    }

    @Test
    public void test07() {
        String[] str = new String[5];
        str[0] = "hello";
        str[1] = "world";
        str[2] = "java";

        //需求是在str中删除[0]位置的元素
        System.arraycopy(str, 1, str, 0, 2);
        str[2] = null;

        for (int i = 0; i < str.length; i++) {
            System.out.println(str[i]);
        }
        /*
        world
        java
        null
        null
        null
         */
    }


    @Test
    public void test06() {
        String[] str = new String[5];
        str[0] = "hello";
        str[1] = "world";
        str[2] = "java";

        //需求是在str[0]插入一个新元素
        System.arraycopy(str, 0, str, 1, 3);
        str[0] = "chailinyan";

        for (int i = 0; i < str.length; i++) {
            System.out.println(str[i]);
        }
        /*
        chailinyan
        hello
        world
        java
        null
         */
    }

    @Test
    public void test05() {
        String[] str = new String[5];
        str[0] = "hello";
        str[1] = "world";
        str[2] = "java";

        System.arraycopy(str, 1, str, 0, 2);
        /*
        从str[1]开始复制2个元素，放到str中，从str[0]开始放
        即str[0] = str[1]
          str[1] = str[2]
         */

        for (int i = 0; i < str.length; i++) {
            System.out.println(str[i]);
        }
        /*
        world
        java
        java
        null
        null
         */
    }

    @Test
    public void test04(){
        String[] str = new String[5];
        str[0] = "hello";
        str[1] = "world";
        str[2] = "java";

        System.arraycopy(str, 0, str, 1, 2);
        /*
        arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
        src：原数组
        srcPos：原数组的起始下标
        dest：目标数组
        destPos：目标数组的起始下标
        length：要复制的元素的个数

        System.arraycopy(str, 0, str, 1, 2);
        从str[0]开始复制元素，复制2个，放到str[1]开始的位置中
        即：str[1] = str[0]
           str[2] = str[1]
         如果发现是从前往后复制，它底层会先从后面开始
            str[2] = str[1]
            str[1] = str[0]
         */

        for (int i = 0; i < str.length; i++) {
            System.out.println(str[i]);
        }
        /*
        hello
        hello
        world
        null
        null
         */
    }

    @Test
    public void test03(){
        System.exit(0);//0表示正常退出JVM，1表示异常退出
        System.out.println("hello");//不执行
    }


    @Test
    public void test02() throws FileNotFoundException {
        System.setOut(new PrintStream("D:/1.txt"));
        System.out.println("atguigu");//现在不是往控制台打印，往d:/1.txt文件打印

    }

    @Test
    public void test01(){
        System.out.println("hello");//普通信息
        System.err.println("error");//错误信息
        Scanner input = new Scanner(System.in);//默认代表键盘输入
    }
}
