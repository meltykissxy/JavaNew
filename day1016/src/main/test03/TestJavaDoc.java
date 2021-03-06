package com.atguigu.test03;

/*
二、文档注释
1、Java特有的
2、长什么样？
/**开头，* /结尾（这里*和/之间本来没有空格，但是因为它不能出现在多行注释中进行嵌套，所以为了避免错误，加了空格）

3、它的作用是什么？
给我们自己编写的类、方法等，加上合理的注释，并且这个注释不仅仅在打开.java文件能看到，
我们还希望能够把这个注释生成API文档，供其他人查看。

4、我们自己也可以编写文档注释

5、如何生成API文档
使用的工具：javadoc.exe工具

注意：javadoc.exe默认选择的编码是和当前操作系统默认编码一致，比如：中文操作系统，默认是GBK

6、几个文档注释的解释
@author：作者
@see：另请参考
@since：从xx版本开始
@param：只能写在方法上面，表示方法的形参，有几个形参，就写几个@param
    格式：@param 形参名  形参的数据类型  形参的解释
@return：只能写在方法上面，表示方法的返回值类型，如果方法的返回值类型，就不用写。
    格式：@return 返回值类型  返回值的描述
@throws：只能写在方法上面，表示方法可能抛出的异常类型，有几种可能，就写几个
    格式：@throws 异常类型   说明xx情况下会抛出这个异常
 */

/**
 * 文档注释演示
 *
 * @author Irene
 * @since 1.8
 * @see java.lang.Object
 */
public class TestJavaDoc {

    /**
     * main方法又被称为主方法，它是Java程序的入口。
     * @param args String[] 它是main的形参，我们称为命令行参数，给他传参数可以在java命令之后传入参数，java 主类名  参数1  参数2 ...
     */
    public static void main(String[] args) {
        System.out.println("Java程序入口");
    }

    /**
     * sum方法的作用是求两个整数的和
     * @param a int 其中一个整数
     * @param b int 另一个整数
     * @return int 返回a+b的结果
     */
    public int sum(int a, int b){
        return a+b;
    }

    /**
     * divide方法的作用是求两个整数的商
     * @param a int 被除数
     * @param b int 除数
     * @return int 返回a/b，结果是int类型，如果无法除尽，舍去小数部分。
     * @throws ArithmeticException 当b=0时，会抛出该异常
     */
    public int divide(int a, int b) throws ArithmeticException{
        return a/b;
    }
}
