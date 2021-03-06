package com.atguigu.test05;

/*
5、异常处理的方式之一：try...catch
（1）语法格式
try{
    可能发生异常的逻辑/业务代码
}catch(异常类型1  异常名){ //异常名习惯写e
    处理或记录或显示异常的代码
}catch(异常类型2  异常名){ //异常名习惯写e
    处理或记录或显示异常的代码
}catch(异常类型3  异常名){ //异常名习惯写e
    处理或记录或显示异常的代码
}
。。。。

（2）如何执行
A：try中没有异常发生，任意一个catch都不会执行
B：try中发生了异常，但是catch捕获不到，当前方法就结束了，异常抛给调用者
C：try中发生了异常，其中一个catch可以捕获到，这个捕获的匹配顺序是从上到下，只要有一个匹配了，
下面的就不看了。
代码不会挂，从try...catch整个结构的下面继续执行。

注意：如果try中发生了异常，try中剩下的代码都不能运行。

（3）演示

（4）在catch中经常用到
A： e.printStackTrace()： e是异常对象，printStackTrace()方法，该方法是打印栈的跟踪痕迹，即这个异常经过哪些方法栈
B：System.out.println(xxx）
C：System.err.println(xxx）
D：其他的处理代码也可以
 */
public class TestTryCatch {

    public static void main(String[] args) {
        //选中要try的代码，快捷键ctrl + Alt + T，选中try...catch
        try {
            //从命令行接收两个整数，求它们的商
            int a = Integer.parseInt(args[0]);
            int b = Integer.parseInt(args[1]);
            int shang = a/b;
            System.out.println("shang = " + shang);
        } catch (NumberFormatException e) {//数字格式化异常，某个字符串无法转为数字
//            e.printStackTrace();
//            System.out.println(args[0] + "或" + args[1] +"可能不是数字");
            System.err.println(args[0] + "或" + args[1] +"可能不是数字");
        } catch (ArrayIndexOutOfBoundsException e){//数字下标越界异常
            e.printStackTrace();
        } catch(ArithmeticException e){//算术异常，例如：/0等
            e.printStackTrace();
        }

        //如果上面try中发生的异常，被catch捕获了，那么下面的代码就可以正常运行
        //如果try中的异常没有被捕获，那么下面的代码是不能运行的。
        System.out.println("其他代码.....");

    }
}
