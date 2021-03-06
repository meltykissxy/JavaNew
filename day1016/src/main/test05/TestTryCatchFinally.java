package com.atguigu.test05;

/*
6、异常处理的方式二：try....catch....finally
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
finally{
    xxx代码
}

（2）执行的特点
其中try...catch和之前一样。
但是这里的finally中的代码是一定会执行的，
A:无论try中是否发生了异常
B:也不管catch中是否捕获异常
C:哪怕try...catch中有return语句
finally中的代码都一定会执行。

只有一种情况，finally不会执行，在try或catch中，执行了System.exit(0);

（3）finally中一般写资源释放代码
例如：IO流的关闭，数据库连接的断开，网络连接的断开等等。
不要在finally中编写其他的业务逻辑代码。


在面试题中还会见到这种形式：
try{
    可能发生异常的逻辑/业务代码
}finally{
    xxx代码
}


总结：只要finally中有return，try,catch中的return都失效了。
     如果finally中没有return，那么方法的返回值还是以try或catch中为准，finally对变量的修改不会影响返回值。
     实际开发中，不会在finally里面写return语句，除了面试题。
 */
public class TestTryCatchFinally {
    public static void main(String[] args) {
        try {
            //从命令行接收两个整数，求它们的商
            int a = Integer.parseInt(args[0]);
            int b = Integer.parseInt(args[1]);
            int shang = a/b;
            System.out.println("shang = " + shang);
            System.exit(0);//退出JVM
        } catch (NumberFormatException e) {//数字格式化异常，某个字符串无法转为数字
//            e.printStackTrace();
//            System.out.println(args[0] + "或" + args[1] +"可能不是数字");
            System.err.println(args[0] + "或" + args[1] +"可能不是数字");
        } catch (ArrayIndexOutOfBoundsException e){//数字下标越界异常
            e.printStackTrace();
        }catch(ArithmeticException e){//算术异常，例如：/0等
            e.printStackTrace();
        } finally {
            System.out.println("finally");
        }
    }
}
