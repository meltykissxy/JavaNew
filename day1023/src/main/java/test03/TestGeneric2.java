package com.atguigu.test03;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

public class TestGeneric2 {
    //声明一个method方法，它的形参是Collection的集合，
    // 虽然我们无法确定该集合元素的具体类型，但是我们知道该元素的类型必须是Number或Number子类
    public void method1(Collection<? extends Number> coll){
        //因为无法确定?的具体类型
//        coll.add(1);//添加Integer对象，可能实参是ArrayList<Double>
//        coll.add(1.0);//添加Double对象，可能实参是ArrayList<Integer>
    }

    public void method2(Collection<Number> coll){

    }

    @Test
    public void test01(){
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        ArrayList<Double> list3 = new ArrayList<>();
        ArrayList<Object> list4 = new ArrayList<>();

//        method1(list1);//String不是Number及其子类
        method1(list2);
        method1(list3);
//        method1(list4);//Object错误，因为Object是Number的父类
    }

    @Test
    public void test02(){
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        ArrayList<Double> list3 = new ArrayList<>();
        ArrayList<Object> list4 = new ArrayList<>();
        ArrayList<Number> list5 = new ArrayList<>();

//        method2(list1);
//        method2(list2);
//        method2(list3);
//        method2(list4);
        method2(list5);//Number
    }
}
