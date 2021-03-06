package com.atguigu.test07;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
7、异常处理方式之三： throws
（1）使用位置：在方法签名中，在方法的()后面，{}的前面

【修饰符】 返回值类型  方法名(【形参列表】) 【throws 异常列表】{
    //....
}

（2）为什么要使用它？ 什么情况下使用它？
在当前方法中，可能会发生xx异常，但是该异常在当前方法中不方便/不能处理，我们要把该异常抛给调用者处理，
写上throws，那么调用者就可以更加明确被调用的这个方法可能会抛出什么异常，我需要try...catch处理哪些异常。

举例：
    完成一个文件的复制功能
    public static void copy(String srcFile, String destFile){
        //检查了源srcFile不存在，会抛出一个FileNotFoundException
        //这个异常在当前方法中不方便，告诉调用者，源文件不存在。
    }

（3）throws使用过程中，在重写时的要求

重写方法的要求：
A：方法名：
    必须相同
B：形参列表：
    必须相同
C：返回值类型
    基本数据类型和void：必须相同
    引用数据类型：<=
D：修饰符
    权限修饰符：>=
    不能有：static，final，private
E：throws异常
    如果被重写的方法没有throws编译时异常，那么重写时，也不能throws编译时异常。
    如果被重写的方法throws编译时异常，那么重写时，throws的编译时异常必须 <= 之前的异常。

    为什么重写时方法抛出的异常，可以<=被重写方法抛出的异常呢？
    因为多态调用时，编译时看父类，运行时看子类。
    编译时看父类的话，按照被重写方法进行的try...catch，运行时抛出的异常<它的话，也是安全的。

    为什么重写时方法的返回值类型是引用数据类型的话，可以<=呢？
    因为多态调用时，编译时看父类，运行时看子类。
    编译时看父类的话，按照被重写方法的返回值类型接收返回值，运行时返回的对象是子类对象，是安全的。

8、关键字：throw
(1)作用：主动抛出异常


9、说明
无论是JVM帮我们new的异常对象，还是我们自己new的异常对象，都是需要处理的。
（1）try...catch处理
（2）throws


顺便复习一下：
重载：当某个类或对象，拥有多个方法名相同，形参列表不同的方法时，称为重载，和返回值类型无关。
 */
public class TestThrows {

    public static void main(String[] args) {
/*        try {
            copy("d:/1.txt","d:/2.txt");
            System.out.println("复制成功");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/

        System.out.println("------------------------");
        try {
            MyClass my = new MyClass();
            Object clone = my.clone();
            System.out.println(clone);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        System.out.println("------------------------");
        Father f = new Sub();
        try {
            f.fun();
        } catch (IOException e) {//编译看父类，父类fun方法抛出IOException，
                            // 运行时看子类，子类重写抛出的是FileNotFoundException
                            //catch也可以捕获FileNotFoundException
            e.printStackTrace();
        }

        System.out.println("------------------------");
        Object fang = f.fang();
        //编译时按照父类处理，返回值类型是Object，实际运行的是子类重写的方法，返回的是String对象，安全的
    }

    public static void copy(String srcFile, String destFile)throws FileNotFoundException{
        //File表示文件，在java.io包
        File file = new File(srcFile);
        //判断文件不存在
        if(!file.exists()){
            throw new FileNotFoundException(srcFile+"不存在");
        }
        //....
    }
}

class MyClass implements Cloneable{
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class Father{
    public void method() {
        //....
    }

    public void test()throws FileNotFoundException{

    }

    public void fun()throws IOException{

    }

    public Object fang(){
        return new Object();
    }
}
class Sub extends Father{
/*    @Override
    public void method() throws Exception{//因为被重写的方法没有throws编译时异常，重写时不能抛出编译时异常
        //....
    }*/

    @Override
    public void method() throws RuntimeException{//虽然被重写的方法没有throws运行时异常，重写时可以抛出运行时异常
                                            //因为运行时异常，编译器根本不检查
        //....
    }

/*    @Override
    public void test()throws IOException {//IOException > FileNotFoundException  错误

    }*/

    @Override
    public void fun()throws FileNotFoundException{//被重写的方法抛出IOException，重写时抛出的异常可以<它

    }

    @Override
    public String fang(){
        return new String();
    }

}