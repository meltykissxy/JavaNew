package com.atguigu.test01;

import java.util.ArrayList;
import java.util.Collection;

/*

 */
public class TestReview {
    public static void main(String[] args) {
        MyClass<?> my;//使用这个泛型类声明变量

        Collection<?> coll ;
//        coll.add("hello");//此时的形参类型是?，不知道什么类型，无法编译通过，编译器不知道该按照什么类型处理

        Collection<? extends Number> coll2 =new ArrayList<>();;
//        coll2.add(1);//此时的形参类型是? extends Number，不知道什么类型，无法编译通过，编译器不知道该按照什么类型处理

        Collection<? super Number> c4 =new ArrayList<>();
        c4.add(1);//此时形参的类型是? super Number，一定是Number或Number的父类，底线在Number
//        c4.add(new Object());//越过底线
    }
}

/*
class MyClass<?>{//错误，声明泛型类，不能用通配符

}*/
class MyClass<T>{

}