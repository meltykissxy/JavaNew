package com.atguigu.test03;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

/*
补充：第11章  泛型
四、泛型的通配符
1、什么情况下需要用到泛型的通配符？
当我们在“使用”一个泛型类/泛型接口时，还是不能明确的指定泛型的具体类型，又不想见到的擦除处理，
这个时候可以上课用泛型通配符。

2、举例

3、形式
（1）类名<?>/接口名<?>：?代表任意类型
    Collection<?>，表示，可以是ArrayList<String>，ArrayList<Integer>,ArrayList<Double>,ArrayList<Object>

（2）类名<? extends 上限>：?代表的是上限或上限的子类可以
    接口名<? extends 上限>：?代表的是上限或上限的子类可以

    说明：<?>后面的上限只能写一个，无论它是类还是接口

（3）类名<? super 下限>：?代表是下限或下限的父类
     接口名<? super 下限>：?代表是下限或下限的父类

     说明：<?>后面的下限只能写一个，无论它是类还是接口


 */
public class TestGeneric {
    //声明一个方法，这个方法需要声明一个形参，该形参作用是接收一个Collection系列的集合
    //但是此时我们并不能确定该集合元素类型
/*    public void method(Collection<元素类型> coll){

    }*/

    //方案一：泛型的擦除，可以
    public void method1(Collection coll){//泛型的擦除
        //...
        //在这里元素类型是不确定的，元素类型只能按照Object处理
        coll.add("hello");
        coll.add(1);
    }

    //方案二：使用泛型的通配符，？
    public void method2(Collection<?> coll){//泛型的擦除
        //...
//        coll这个集合可以查看，遍历，可以删除,但是coll集合不能添加元素
//        coll.add("hello");//因为<?>代表可能是任意类型，目前添加什么都是编译不通过
//        coll.add(1);

    }

    //方案三：使用Object
    public void method3(Collection<Object> coll){//泛型的擦除
        //...
        //元素类型按照Object处理
        coll.add("hello");
        coll.add(1);
    }

    @Test
    public void test01(){
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        ArrayList<Double> list3 = new ArrayList<>();

        //method1方法的形参是(Collection coll)，实参传入ArrayList<String> list1
        //method1方法的形参是(Collection coll)，实参传入ArrayList<Inetger> list2
        //method1方法的形参是(Collection coll)，实参传入ArrayList<Double> list3
        //都可以
        method1(list1);
        method1(list2);
        method1(list3);
    }

    @Test
    public void test02(){
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        ArrayList<Double> list3 = new ArrayList<>();

        //method2方法的形参是(Collection<?> coll)，实参传入ArrayList<String> list1
        //method2方法的形参是(Collection<?> coll)，实参传入ArrayList<Integer> list2
        //method2方法的形参是(Collection<?> coll)，实参传入ArrayList<Double> list3
        //都可以
        method2(list1);
        method2(list2);
        method2(list3);
    }

    @Test
    public void test03(){
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        ArrayList<Double> list3 = new ArrayList<>();
        ArrayList<Object> list4 = new ArrayList<>();

        //method3方法的形参是(Collection<Object> coll)，实参传入ArrayList<String> list1
        //method3方法的形参是(Collection<Object> coll)，实参传入ArrayList<Integer> list2
        //method3方法的形参是(Collection<Object> coll)，实参传入ArrayList<Double> list3
        //都不可以
//        method3(list1);
//        method3(list2);
//        method3(list3);

        //method3方法的形参是(Collection<Object> coll)，实参传入ArrayList<Object> list4
        method3(list4);
    }
}
