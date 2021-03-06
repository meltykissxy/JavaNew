package test05;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Random;

/*
第十章  常用类及其API
一、和数学计算有关的
1、java.lang.Math类
Math 类包含用于执行基本数学运算的方法，如初等指数、对数、平方根和三角函数。

用过：public static double random()：返回[0,1)范围的double小数
     public static double sqrt(double a)：返回a的平方根
     public static final double PI：常量

今天：
（1）public static final double E：自然对数的底数
（2）public static double abs(double a)：返回a的绝对值
    ...有几个重载的方法，支持不同的数据类型
（3）和三角函数有关的方法
（4）public static double floor(double a)：向下取整
    public static double ceil(double a)：向上取整   floor地板，ceil天花板
    public static long round(double a)：四舍五入
（5）public static double max(double a, double b)：返回a,b中较大者
    public static double min(double a,double b)：返回a,b中的较小者
    ...有几个重载的方法，支持不同的数据类型
（6）对数系列
    public static double log(double a)
    public static double log10(double a)
    ...
（7）求a的b次方
  public static double pow(double a, double b)

2、java.util.Random类
此类的实例用于生成伪随机数流。
（1）public int nextInt()：随机产生一个int类
（2）public double nextDouble()：同Math.random()，产生[0,1)范围的随机小数
（3）...支持nextBoolean(),nextLong()等
（4）public int nextInt(int n)：带参数的nextInt方法，产生[0, n)范围的整数

3、java.math包
java.math.BigDecimal：表示不可变的、任意精度的有符号十进制数。
java.math.BigInteger：不可变的任意精度的整数。

之前说float和double是浮点数，不精确。当我们如果程序对数字的精确度比较高，那么就用BigDecimal。
我们之前一般的整数用int，大一点用long，如果再大的话就用BigInteger。

特殊：BigDecimal和BigInteger是引用数据类型，必须new对象才能使用。

BigInteger的方法同BigDecimal

 */
public class TestMath {
    @Test
    public void test07(){
        BigDecimal b1 = new BigDecimal("1.897894421174148774444555555788888411");
        BigDecimal b2 = new BigDecimal("855.89789458855421174148774444555555788888411");
//        BigDecimal b2 = new BigDecimal("2.0");

        //计算
//        System.out.println(b1 + b2);//错误，因为引用数据类型不能直接  加减乘除等，
                                    //又不是包装类，不会自动拆箱
        System.out.println(b1.add(b2));
        System.out.println(b1.subtract(b2));
        System.out.println(b1.multiply(b2));

//        System.out.println(b1.divide(b2));
        //ArithmeticException: Non-terminating decimal expansion; no exact representable decimal result.
        //算术异常：无法终止的小数表达，没有精确的表示方式，即除不尽

        System.out.println(b1.divide(b2,50, BigDecimal.ROUND_UP));//如果结果可能是除不尽的，一定要指定一下，最后你要保留到小数点后几位，以及舍去的部分如何处理
                                        //如何舍去剩下的部分。可以直接截掉，还是四舍五入
        System.out.println(b1.divide(b2,50, BigDecimal.ROUND_DOWN));
    }

    @Test
    public void test06(){
        Random rand = new Random();
        System.out.println(rand.nextInt());
        System.out.println(rand.nextInt(10));
    }

    @Test
    public void test05(){
        //求3的8次方
        System.out.println(Math.pow(3,8));
        //求4的2.5次
        System.out.println(Math.pow(4,2.5));
    }
    @Test
    public void test04(){
        double x = 1.2;
        double y = 2.2;
        double z = 0.2;

        System.out.println(Math.max(Math.max(x,y),z));
    }

    @Test
    public void test03(){
        System.out.println(Math.round(2.5));//3  入3
        System.out.println(Math.round(-2.5));//-2  入2
    }
    @Test
    public void test02(){
        System.out.println(Math.floor(-2.6));//-3.0
        System.out.println(Math.ceil(-2.6));//-2.0
        System.out.println(Math.round(-2.6));//-3
        System.out.println("------------------------");
        System.out.println(Math.floor(-2.2));//-3.0
        System.out.println(Math.ceil(-2.2));//-2.0
        System.out.println(Math.round(-2.2));//-2
    }

    @Test
    public void test01(){
        System.out.println(Math.floor(2.6));//2.0
        System.out.println(Math.ceil(2.6));//3.0
        System.out.println(Math.round(2.6));//3
        System.out.println("------------------------");
        System.out.println(Math.floor(2.2));//2.0
        System.out.println(Math.ceil(2.2));//3.0
        System.out.println(Math.round(2.2));//2
    }
}
