package com.atguigu.test04;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/*
第十二章  集合
三、集合工具类：（部分方法），为了熟悉<?>等
java.util.Collections类
java.util.Collection接口

Collections这个类中定义了很多静态方法，来操作集合，包括，二分查找、排序，反转，找最大值.....
（1）public static <T> boolean addAll(Collection<? super T> c, T... elements)
       参数一：Collection<? super T> 需要一个集合，这个集合的元素类型必须指定为是T 或 T的父类
            假设：T的类型是String，请问Collection<? super T>可以是哪些类型？
                Collection<String>
                Collection<Object>
（2）public static <T> int binarySearch(List<? extends Comparable<? super T>> list, T key)：在list集合中查找key是否存在
    二分查找，是不是要求数组或集合是有大小序，要么从小到大，要么从大到小。
    参数一：List<? extends Comparable<? super T>> list：
            ? extends Comparable：要请List中的元素类型必须是实现了Comparable接口。因为我们查找过程中要按大小比较，去左边还是去右边。
            Comparable<? super T>：可以是T或T的父类实现了Comparable接口。

（3）public static <T> void copy(List<? super T> dest,  List<? extends T> src)
     参数一：List<? super T> dest：目标集合
     参数二：List<? extends T> src：原集合
     从src集合中复制元素到dest集合中， dest的元素类型 >= src的元素类型
     假设T是Number类型，  dest可以是Number，或Number的父类
                         src可以是Number，或Number的子类
    隐含要求：list1的长度>=list2，因为它是按照对应位置复制，不是在list1中增加。

（4）public static <T extends Object & Comparable<? super T>> T min(Collection<? extends T> coll)
    方法名：min，找最小值
    形参：Collection<? extends T> coll，传入的集合的元素类型是T 或 T的子类
    返回值类型：T
    泛型方法的泛型声明：<T extends Object & Comparable<? super T>>，指定了T的上限有两个，Object和Comparable
 */
public class TestCollections {
    @Test
    public void test06(){
        Collection coll = new ArrayList<>();
        coll.add(3);
        coll.add(1);
        coll.add(6);
        coll.add(2);

        //public static <T extends Object & Comparable<? super T>> T min(Collection<? extends T> coll)
        Comparable min = Collections.min(coll);
    }

    @Test
    public void test05(){
//        Collection<Number> coll = new ArrayList<>();
//        Number min = Collections.min(coll);//错误，Number没有实现Comparable
        
        Collection<Integer> coll = new ArrayList<>();
        coll.add(3);
        coll.add(1);
        coll.add(6);
        coll.add(2);
        Integer min = Collections.min(coll);
        System.out.println(min);
    }

    @Test
    public void test04(){
        List<Number> list1 = new ArrayList<>();
        list1.add(1.0);
        list1.add(2.0);
        list1.add(3.0);

        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        list2.add(4);

        Collections.copy(list1, list2);//把list2中的元素复制到list1中
        System.out.println(list1);
    }

    @Test
    public void test02(){
        Collection<Object> coll = new ArrayList<>();
        Collections.addAll(coll, "hello","world");
    }

    @Test
    public void test01(){
        Collection<String> coll = new ArrayList<>();
        Collections.addAll(coll, "hello","world");
    }

    @Test
    public void test03(){
        List<Circle> list = new ArrayList<>();
        list.add(new Circle(1.2));
        list.add(new Circle(1.3));
        list.add(new Circle(1.4));

        //public static <T> int binarySearch(List<? extends Comparable<? super T>> list, T key)
        int index = Collections.binarySearch(list, new Circle(1.4));
        System.out.println(index);
    }
}

abstract class Graphic implements Comparable<Graphic>{
    public abstract double area();

    public int compareTo(Graphic g){
        return Double.compare(this.area(), g.area());
    }
}

class Circle extends Graphic{
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                '}';
    }

    @Override
    public double area() {
        return Math.PI * radius*radius;
    }
}
