package com.atguigu.test01;

/*
JavaSE阶段的课程，分为三大块内容：
一、面向对象之前：程序设计的基础
基本数据类型、运算符、流程控制语句结构、数组

二、面向对象的语法和特点和新特性
1、类和对象
如何声明类？类的5大成员：成员变量、成员方法、构造器、代码块、内部类 ，前三个最基础最重要
创建对象？new对象

2、基本特征：封装、继承、多态

3、修饰符或关键字
this,super,static, final,abstract,native,public,protected,private,缺省,
interface,enum

静态、抽象、接口、枚举

4、泛型

三、各种API
1、包装类，String等，数学，系统，日期时间相关。。。
2、集合
3、。。。。

第十二章  集合
一、如何学集合这章
1、重要性：无论在写代码层面还是面试中，都很重要。
2、集合要学习哪些内容
（1）基本API：Collection，List，Set，Map接口及其他们的实现类们    （必须）
（2）掌握常见的几种集合的底层实现（重点方法的实现要关注）           （尽量）
    动态数组：Vector，ArrayList
    链表：LinkedList
    哈希表：Hashtable，HashMap
（3）自定义自己的数据结构，了解其他更多的数据结构                   （长期规划）

二、集合的概述
1、集合是干什么用的？
集合对于使用者来说，就是一个容器。容器是用来装东西，在Java中用来装对象。
集合对于设计者来说，它是一种数据结构。数据结构：数据的存储和管理的结构。

2、常见的分为两大类
（1）Collection系列：存储一组对象，每一个对象之间是独立的
        比喻：单身party
        Collection系列的集合中存储：王阳、含笑、秦杰、佟威、董浩
（2）Map系列：存储一个一个的键值对（映射关系），每一对键值对是有映射关系的
        比喻：情侣party，家庭party
        Map系列的集合中存储：
                王阳->翠花
                秦杰->如花
                含笑->杨幂
                佟威->翠花、如花、春花
                董浩->null

三、Collection系列集合
1、Collection是存储一组对象的，
   我们之前学习的对象数组，也可以存储一组对象，他们有什么区别呢？

第一点：
数组的长度一旦确定，就不能修改，对于同一个数组对象来说长度是固定的。
        对于数组来说，如果需要扩容等操作，需要程序员做很多事情。
集合的长度不是固定的，它可以根据需求自动的扩容等，对于外部的集合对象来说，是同一个集合对象。
        对于集合来说，使用者就不用操这个心了。

第二点：
数组不仅仅可以存储对象，也可以存储基本数据类型的值。
集合只能存储对象，不能存储基本数据类型的值，如果把基本数据类型的值放到集合中，会自动装箱为包装类对象。

2、Collection系列的集合有很多：
（1）有连续存储、非连续存储的
（2）有允许重复的、不允许重复
（3）有有序的，也可以是无序的
...
虽然有这么多种的要求，但是他们有共同的特点，都是存储一组对象的容器。

容器：
存：添加
删：去掉
查：看是否包含等，个数
看全部：遍历或迭代
...

==>公共的父接口：Collection接口

3、java.util.Collection<E>接口
  这里<E>是泛型，它代表的是集合中元素的类型，元素的单词是element
  Collection 层次结构 中的根接口。
  Collection 表示一组对象，这些对象也称为 collection 的元素。
  一些 collection 允许有重复的元素，而另一些则不允许。
  一些 collection 是有序的，而另一些则是无序的。

  JDK 不提供此接口的任何直接 实现：它提供更具体的子接口（如 Set 和 List等）实现。
  方法们：
（1）添加
boolean add(E e)  ：添加一个元素
boolean addAll(Collection<? extends E> c) ：添加一组对象

（2）查询
int size()：查看集合中元素的个数
boolean contains(Object o) ：判断当前集合中是否包含o对象
boolean containsAll(Collection<?> c)  ：判断当前集合中是否包含c集合的所有元素
        相当于判断c是否是当前集合的“子集”
boolean isEmpty()  ：判断当前集合是否为空集合

（3）删除
 void clear() ：清空当前集合
 boolean remove(Object o) ：从当前集合中删除一个元素
boolean removeAll(Collection<?> c) ：从当前集合中删除  当前集合与c集合的交集
        this = this - this ∩  c；
boolean retainAll(Collection<?> c) ：在当前集合中保留 和c集合的交集，非交集部分删除
        this = this ∩  c；

（4）修改
Collection接口中，没有提供直接修改某个元素的方法。

（5）迭代/遍历：挨个查看集合的元素
Object[] toArray()
Iterator<E> iterator()

java.util.Iterator<E>迭代器接口：对 collection 进行迭代的迭代器。
    A：boolean hasNext()：判断是否还有元素可迭代
    B：E next()：取出下一个要迭代的元素
    C： void remove()：删除刚刚迭代的元素


 Iterator<E>迭代器接口的实现类在哪里？实现类的对象又是在哪里创建对象？
 Iterator<E>迭代器接口的实现类在Collection系列的集合的具体集合的实现类中，用内部类的形式实现的。
 例如：ArrayList这个集合，内部就有一个Iterator<E>迭代器接口的实现类,
                    private class Itr implements Iterator<E> {...}//ArrayList的内部类Itr
       在ArrayList的iterator方法中，public Iterator<E> iterator() {
                                        return new Itr();
                                    }
  如果我们使用的是其他Collection的实现类，例如：Vector,LinkedList等，那么他们内部也会有对应的内部类实现 Iterator<E>迭代器接口。

  为什么要这么干？
  因为每一种集合的内部结构是不同，有的里面是数组，有的里面是链表，有的里面是...，那么它们具体的遍历方式是不同。
  因此Iterator接口的实现类也会有很多种，每一种集合（ArrayList,Vector,LinkedList等)都有自己独特的遍历方式。
  所以只能为它们单独实现具体的迭代器。

  作为内部类的好处，还有两点，（1）内部类可以访问外部类的私有的成员，即作为内部类的迭代器可以直接访问集合的私有的成员。
                            （2）这个内部类只为当前集合服务，所以定义成私有的内部类更合理一点。

  这种设计是一个迭代器设计模式。


    Collection的remove方法与Iterator的remove方法的区别？
    Collection的remove方法只能用于删除具体的目标对象。必须知道要被删除的元素是谁。
    Iterator的remove方法可以遍历过程中，判断元素是否满足xx条件，再删除。

    特别提醒：
        不要一边用Iterator迭代，在迭代过程中，使用Collection的remove方法。
        可能会报ConcurrentModificationException，或者是不报异常，结果不对。

        因为：Iterator迭代器，如果没有一个机制去时刻关注集合的元素个数等情况的话，可能会导致遍历的结果不一致。
            Iterator迭代器就必须在每次遍历的过程中，要校验我们记录的元素个数，位置，是否还是正确的。


        比喻：集合好比是火车的一个卧铺车厢，迭代器好比是乘务员。
              乘客上车要把票换成卡，下车把卡换成票，这么做的其中一个好处，乘务员时刻知道哪些乘客在车上，哪些铺位是空的。

              如果没有换卡的过程，乘客下下车，有可能乘务员不知道。



 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

public class TestCollection {
    @Test
    public void test16() {
        //删除：  单词中包含o字符的元素
        Collection<String> c1 = new ArrayList<>();
        c1.add("hello");
        c1.add("world");
        c1.add("java");
        c1.add("atguigu");

        //使用迭代器遍历，判断元素是否满足xx条件，然后调用集合的remove方法
        Iterator<String> iterator = c1.iterator();
        while(iterator.hasNext()){
            String element = iterator.next();
            if(element != null && element.contains("o")){
               c1.remove(element);//ConcurrentModificationException  不要这么做
            }
        }
        System.out.println(c1);
    }

    @Test
    public void test15() {
        //删除：  单词中包含o字符的元素
        Collection<String> c1 = new ArrayList<>();
        c1.add("hello");
        c1.add("world");
        c1.add("java");
        c1.add("atguigu");

        //如果使用Collection的remove方法，需要人工筛选出目标对象
//        c1.remove("hello");
//        c1.remove("world");

        //使用迭代器的remove方法
        Iterator<String> iterator = c1.iterator();
        while(iterator.hasNext()){
            String element = iterator.next();
            if(element != null && element.contains("o")){
                iterator.remove();
            }
        }
        System.out.println(c1);
    }


    @Test
    public void test14() {
        //删除"java"，两种都可以
        Collection<String> c1 = new ArrayList<>();
        c1.add("hello");
        c1.add("world");
        c1.add("java");
        c1.add("atguigu");

        /*Iterator<String> iterator = c1.iterator();
        while(iterator.hasNext()){
            String element = iterator.next();
            if("java".equals(element)){// if(element.equals("java"))可能有空指针异常
                iterator.remove();
            }
        }
*/
        c1.remove("java");
        System.out.println(c1);
    }

    @Test
    public void test13() {
        Collection<String> c1 = new ArrayList<>();
        c1.add("hello");
        c1.add("world");
        c1.add("java");
        c1.add("atguigu");

        /*
        c1的编译时类型是Collection，运行时类型是ArrayList
        c1.iterator()执行的是ArrayList的iterator()，它内部给我new了一个Itr的对象
        Itr是在ArrayList中声明的一个内部类，这个内部类实现了Iterator接口。
         */
        Iterator<String> iterator = c1.iterator();
        /*
        iterator.hasNext()和iterator.next()，编译时看到的是Iterator接口的方法，
                                            实际运行的是Itr实现类的hasNext()和next()
         */
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Test
    public void test12() {
        Collection<String> c1 = new ArrayList<>();
        c1.add("hello");
        c1.add("world");
        c1.add("java");
        c1.add("atguigu");

        Iterator<String> iterator = c1.iterator();
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());//NoSuchElementException
    }

    @Test
    public void test11() {
        Collection<String> c1 = new ArrayList<>();
        c1.add("hello");
        c1.add("world");
        c1.add("java");
        c1.add("atguigu");

        //转为数组，然后再遍历
        Object[] objects = c1.toArray();
        for (int i = 0; i < objects.length; i++) {
            System.out.println(objects[i]);
        }
    }

    @Test
    public void test10() {
        //(1)先创建容器对象，即创建集合对象，并且指定这个集合中要装什么类型的对象
        Collection<String> c1 = new ArrayList<>();
        c1.add("hello");
        c1.add("world");
        c1.add("java");
        c1.add("atguigu");

        Collection<String> c2 = new ArrayList();
        c2.add("hello");
        c2.add("world");
        c2.add("java");
        c2.add("haha");

        c1.retainAll(c2);
        System.out.println(c1);//[hello, world, java]
    }

    @Test
    public void test09() {
        //(1)先创建容器对象，即创建集合对象，并且指定这个集合中要装什么类型的对象
        Collection<String> c1 = new ArrayList<>();
        c1.add("hello");
        c1.add("world");
        c1.add("java");
        c1.add("atguigu");

        Collection<String> c2 = new ArrayList();
        c2.add("hello");
        c2.add("world");
        c2.add("java");
        c2.add("haha");

        c1.removeAll(c2);
        System.out.println(c1);//atguigu
    }

    @Test
    public void test08() {
        //(1)先创建容器对象，即创建集合对象，并且指定这个集合中要装什么类型的对象
        Collection<String> c1 = new ArrayList<>();
        c1.add("hello");
        c1.add("world");
        c1.add("java");
        c1.add("atguigu");

        c1.remove("world");
        System.out.println(c1);
    }

    @Test
    public void test07() {
        //(1)先创建容器对象，即创建集合对象，并且指定这个集合中要装什么类型的对象
        Collection<String> c1 = new ArrayList<>();
        c1.add("hello");
        c1.add("world");
        c1.add("java");
        c1.add("atguigu");

        Collection<String> c2 = new ArrayList();
        c2.add("hello");
        c2.add("world");
        c2.add("java");
//        c2.add("haha");

        System.out.println(c1.containsAll(c2));//true  false
        System.out.println(c1.contains(c2));//false
    }

    @Test
    public void test06() {
        //(1)先创建容器对象，即创建集合对象，并且指定这个集合中要装什么类型的对象
        Collection<Student> c1 = new ArrayList<>();
        c1.add(new Student("张三"));
        c1.add(new Student("李四"));

        System.out.println(c1.contains(new Student("张三")));//false
        //说明contains方法内部使用 元素的equals方法，只不过Student没有重写equals，等价于==
    }

    @Test
    public void test05() {
        //(1)先创建容器对象，即创建集合对象，并且指定这个集合中要装什么类型的对象
        Collection c1 = new ArrayList();
        c1.add("hello");
        c1.add("world");
        c1.add(new String("atguigu"));

        System.out.println(c1.contains("hello"));//true
        System.out.println(c1.contains("java"));//false
        System.out.println(c1.contains(new String("atguigu")));//true
    }

    @Test
    public void test04(){
        //(1)先创建容器对象，即创建集合对象，并且指定这个集合中要装什么类型的对象
        Collection c1 = new ArrayList();
        c1.add("hello");
        c1.add("world");

        Collection c2 = new ArrayList();
        c2.add("hello");
        c2.add("world");
        c2.add("java");

        c1.add(c2);//因为c1没有指定泛型，表示它的元素可以是任意类型，这里把c2整个集合当中一个对象
        System.out.println(c1.size());//3
        System.out.println(c1);//[hello, world, [hello, world, java]]
    }

    @Test
    public void test03(){
        //(1)先创建容器对象，即创建集合对象，并且指定这个集合中要装什么类型的对象
        Collection<String> c1 = new ArrayList<>();
        c1.add("hello");
        c1.add("world");

        Collection<String> c2 = new ArrayList<>();
        c2.add("hello");
        c2.add("world");
        c2.add("java");

//        c1.add(c2);//错误，因为如果把c2当成一个对象，它的类型是Collection<String>类型，不是String类型
        c1.addAll(c2);
        System.out.println(c1.size());//5  ArrayList是允许重复的，所以c1+c2的个数
        System.out.println(c1);//[hello, world, hello, world, java]
    }


    @Test
    public void test02(){
        //(1)先创建容器对象，即创建集合对象，并且指定这个集合中要装什么类型的对象
        Collection<String> c = new ArrayList<>();
//        c.add(1);//错误，因为1不是字符串对象
        c.add("hello");
        c.add("world");

        System.out.println(c.size());
    }

    @Test
    public void test(){
        //（1）先创建容器对象，即创建集合对象
//        Collection c = new Collection();//错误，接口是不能直接new对象
        Collection c = new ArrayList();//ArrayList是Collection子接口List的实现类
                                        //为什么不写ArrayList c = new ArrayList()？因为我们使用多态引用的话，
                                    // 目前编译阶段按Collection处理，我们只能调用Collection中有的方法，关注点在Collection。
        //（2）添加元素
        c.add(1);
        c.add("hello");
        c.add(new Date());
    }
}
class Student{
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}