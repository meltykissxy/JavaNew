package com.atguigu.test03;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

/*
上午学习的Collection接口的API中：
（1）boolean addAll(Collection<? extends E> c)：
        假设E是Number
        形参可以接收ArrayList<Number>，ArrayList<Integer>，ArrayList<Double>
（2）boolean containsAll(Collection<?> c)
        <?>任意类型

（3）boolean removeAll(Collection<?> c)
        <?>任意类型
 */
public class TestGeneric4 {
    @Test
    public void test03() {
        Collection<Number> c1 = new ArrayList<>();
        c1.add(1);
        c1.add(2.0);

        Collection<String> c2 = new ArrayList<>() ;
        c2.add("hello");
        c2.add("java");

       c1.removeAll(c2);//c1集合中的元素只是和c2中的 元素做比较，如果类型不同，只是删除不成功，结果没删除掉
    }

    @Test
    public void test02() {
        Collection<Number> c1 = new ArrayList<>();
        c1.add(1);
        c1.add(2.0);

        Collection<String> c2 = new ArrayList<>() ;
        c2.add("hello");
        c2.add("java");

        System.out.println(c1.containsAll(c2));//c1集合中的元素只是和c2中的 元素做比较，如果类型不同，只是返回false
    }


    @Test
    public void test01(){
        Collection<Number> c1 = new ArrayList<>() ;
        c1.add(1);
        c1.add(2.0);

        Collection<Integer> c2 = new ArrayList<>() ;
        c2.add(1);
        c2.add(2);

        c1.addAll(c2);//c2的元素放到c1中安全，所以此处可以接收c2集合

    }
}
