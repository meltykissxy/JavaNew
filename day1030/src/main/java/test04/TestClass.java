package test04;

import org.junit.jupiter.api.Test;

import java.time.Month;

public class TestClass {
    @Test
    public void test2() throws ClassNotFoundException {
        //得到String类对应的Class对象
        Class<?> stringClass1 = String.class;

//        "hello"是String类的对象
        Class<?> stringClass2 = "hello".getClass();

        System.out.println(stringClass1 == stringClass2);//true，同一个Class对象，String类型在方法区只有唯一的一个Class对象

        Class<?> stringClass3 = Class.forName("java.lang.String");
        System.out.println(stringClass1 == stringClass3);
    }

    @Test
    public void test(){
        //得到String类对应的Class对象
        Class<String> stringClass = String.class;
        //得到某个接口的Class对象
        Class<Comparable> comparableClass = Comparable.class;
        //得到某个枚举的Class对象
        Class<Month> monthClass = Month.class;
        //得到某个注解的Class对象
        Class<Override> overrideClass = Override.class;
        //得到某个数组的Class对象
        Class<int[]> aClass = int[].class;
        Class<String[]> aClass1 = String[].class;
        Class<int[][]> aClass2 = int[][].class;
        //得到基本数据类型的Class对象
        Class<?> integerClass = int.class;
        Class<?> voidClass = void.class;
    }
}
