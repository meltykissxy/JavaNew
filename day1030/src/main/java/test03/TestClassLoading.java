package test03;

import java.io.FileInputStream;

public class TestClassLoading extends Father {
    static{
        System.out.println("main所在的类初始化");
    }

    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("hello");

        new DemoClass();

        System.out.println(MyClass.a);//访问MyClass类的静态变量

        //例如：以下代码就是通过反射的代码操作某个类
        Class.forName("com.atguigu.test03.Other");
    }
}
class Father{
    static{
        System.out.println("父类的初始化");
    }
}
class DemoClass{
    static{
        System.out.println("DemoClass类初始化");
    }
    public DemoClass(){
        System.out.println("DemoClass类无参构造");
    }
}

class MyClass{
    public static int a = 10;
}

class Other{
    static{
        System.out.println("other类的初始化");
    }
}