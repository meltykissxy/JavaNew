package com.atguigu.test03;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

public class TestGeneric3 {
    //声明一个method方法，它的形参是Collection的集合，
    // 虽然我们无法确定该集合元素的具体类型，但是我们知道该元素的类型必须是Number或Number父类
    public void method(Collection<? super Number> coll){
        //?代表Number或Number的父类，但是具体是什么不清楚
        coll.add(1); //传入的实参ArrayList<Object>或ArrayList<Number>
                    //元素是Integer都是安全的
        coll.add(1.0);//传入的实参ArrayList<Object>或ArrayList<Number>
                    //元素是Double都是安全的
    }

    @Test
    public void test(){
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        ArrayList<Double> list3 = new ArrayList<>();
        ArrayList<Object> list4 = new ArrayList<>();
        ArrayList<Number> list5 = new ArrayList<>();

//        method(list1);
//        method(list2);
//        method(list3);
        method(list4);
        method(list5);
    }
}
