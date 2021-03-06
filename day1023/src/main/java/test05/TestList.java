package com.atguigu.test05;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/*
四、List集合（java.util.List，不是java.awt.List）
上午：Collection 层次结构 中的根接口。
Collection 表示一组对象，这些对象也称为 collection 的元素。
一些 collection 允许有重复的元素，而另一些则不允许。
一些 collection 是有序的，而另一些则是无序的。
JDK 不提供此接口的任何直接 实现：它提供更具体的子接口（如 Set 和 List）实现。

1、List接口是Collection的子接口。
==> Collection接口中的所有方法，List都有。

2、List子接口增加了很多Collection没有的方法，即扩展了Collection接口。

3、List接口的介绍
（1）有序的 collection（也称为序列）。
（2）此接口的用户可以对列表中每个元素的插入位置进行精确地控制。用户可以根据元素的整数索引（在列表中的位置）访问元素，并搜索列表中的元素。
（3）与 set 不同，列表通常允许重复的元素。即可能存在两个相同的元素（equals方法返回true）

总结：有序、可重复的、可以通过下标进行操作。

4、方法们（注意扩展的方法们）
（1）添加
 boolean add(E e) ：默认在列表的最后添加
 boolean addAll(Collection<? extends E> c) ：默认在列表的最后添加一组元素
 新增的方法：
 void add(int index, E element) ：在[index]位置添加，即插入
 boolean addAll(int index, Collection<? extends E> c)  ：在[index]位置插入一组对象

（2）删除
void clear()
boolean remove(Object o)
boolean removeAll(Collection<?> c)
boolean retainAll(Collection<?> c)
 新增的方法：
 E remove(int index) ：删除[index]位置的元素

 （3）修改
 增了修改方法：
 E set(int index, E element)

 （4）查询或获取
 int size()
 boolean isEmpty()
 boolean contains(Object o)
boolean containsAll(Collection<?> c)

增加了方法：
E get(int index)  ：获取[index]位置的元素
int indexOf(Object o) ：查找某个对象在当前集合中出现的下标，第一个
int lastIndexOf(Object o)  ：查找某个对象在当前集合中出现的下标，最后一个
List<E> subList(int fromIndex, int toIndex)：截取[fromIndex, toIndex)范围的子集

（5）迭代
Object[] toArray()
Iterator<E> iterator()
foreach，本质还是Iterator<E> iterator()

新增一种遍历：
ListIterator<E> listIterator()
ListIterator<E> listIterator(int index)

ListIterator<E>接口，它是Iterator<E>的子接口。
Iterator<E>接口是从前往后遍历。
ListIterator<E>接口可以从任意位置开始，从前往后，或从后往前。

A：boolean hasNext()
B：E next()
C：boolean hasPrevious()
D：E previous()
E：int nextIndex() ：获取下一个元素的下标，但是不去走下一个元素，游标也不往后走
F：int previousIndex()

G：void remove() ：在遍历过程中进行删除
H：void add(E e) ：在遍历过程中可以添加
I：void set(E e)  ：在遍历过程中可以修改

 */
public class TestList {
    @Test
    public void test08() {
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("java");
        list.add("haha");
        list.add("heihei");

        ListIterator<String> listIterator = list.listIterator();
        while(listIterator.hasNext()){
            System.out.println(listIterator.nextIndex());
            System.out.println(listIterator.next());
        }
    }

    @Test
    public void test07() {
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("java");
        list.add("haha");
        list.add("heihei");

        //从前往后遍历
        ListIterator<String> listIterator = list.listIterator(2);
        while(listIterator.hasNext()){//是否有下一个元素
            System.out.println(listIterator.next());//取出下一个元素
        }
        System.out.println("-------------------");
        //经过上一个循环，游标已经到达集合的最后
        while(listIterator.hasPrevious()){//是否有上一个元素
            System.out.println(listIterator.previous());//取出前一个元素
        }
    }

    @Test
    public void test06() {
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("java");
        list.add("haha");
        list.add("heihei");

        //从前往后遍历
        ListIterator<String> listIterator = list.listIterator();
        while(listIterator.hasNext()){//是否有下一个元素
            System.out.println(listIterator.next());//取出下一个元素
        }
        System.out.println("-------------------");
        //经过上一个循环，游标已经到达集合的最后
        while(listIterator.hasPrevious()){//是否有上一个元素
            System.out.println(listIterator.previous());//取出前一个元素
        }
    }


    @Test
    public void test05() {
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("java");

        list.set(0,"hahha");
        System.out.println(list);//[hahha, java]
    }
    @Test
    public void test04() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(5);
        list.add(7);

        //删除元素对象1的话
//        list.remove(Integer.valueOf(1));//可以
//        list.remove(new Integer(1));//可以
        list.remove((Integer)1);//可以
        System.out.println(list);//[2, 5, 7]

    }

    @Test
    public void test03() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(5);
        list.add(7);

        list.remove(1);//1是int类型
        /*
        boolean remove(Object o)
         E remove(int index)
         它俩是重载，先找最匹配的， E remove(int index)
         */
        System.out.println(list);//[1, 5, 7]

    }

    @Test
    public void test02() {
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("java");

        list.remove(0);
        System.out.println(list);//[java]
    }

    @Test
    public void test01(){
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("java");
        list.add(0,"haha");

        System.out.println(list);//[haha, hello, java]
    }
}
