package com.atguigu.test01;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

/*
4、foreach遍历
foreach遍历方式是JDK1.5之后引入的。它被称为增强for循环。区别与普通的for循环。

普通for循环：for(初始化表达式; 循环条件; 迭代表达式){循环体语句;}
增强for循环：for(元素的类型  元素名 : 容器对象名){循环体语句;}  代码更简洁，作用是用于遍历集合、数组等容器。


本质：
    数组，或我们目前Collection系列的集合都支持增强for循环，因为他们都是实现了一个接口java.lang.Iterable<E>。

    java.lang.Iterable<T>：实现这个接口允许对象成为 "foreach" 语句的目标。
       这个接口的抽象方法：Iterator<T> iterator()

    集合中使用foreach遍历，本质上还是在用Iterator<T> iterator()遍历。

    特别注意：（1）在foreach遍历过程中，也不要调用Collection的remove方法。
             （2）不要在foreach遍历过程中，让元素名 = 新对象，这样操作是无法修改集合或数组的元素，
                        但是可以直接  元素名.setXxx，这样可以修改元素的成员变量。
 */
public class TestForeach {
    @Test
    public void test05(){
        Student[] arr = new Student[2];
        arr[0] = new Student("张三");
        arr[1] = new Student("李四");

        for (Student student : arr) {
            student.setName("xxx");
        }

        //等价于
        for (int i = 0; i < arr.length; i++) {
            Student student = arr[i];
            student.setName("xxx");//student和arr[i]现在是同一个对象，没有指向新对象，修改的就是元素的成员变量
        }

        System.out.println("--------------------");
        for (Student student : arr) {
            System.out.println(student);
        }
    }

    @Test
    public void test04(){
        String[] arr = {"hello","java","world"};

        /*
        以下代码，表面看，把数组中所有元素都修改为"atguigu"
         */
        for (String s : arr) {
           s = "atguigu";//因为这个s是局部变量
        }

/*        for (int i = 0; i < arr.length; i++) {
            String s = arr[i];
            s = "atguigu";
        }*/

        System.out.println("--------------------");
        for (String s : arr) {
            System.out.println(s);//没有修改成功
        }
    }

    @Test
    public void test03(){
        Collection<String> c1 = new ArrayList<>();
        c1.add("hello");
        c1.add("world");
        c1.add("java");
        c1.add("atguigu");

        //  //增强for循环，快捷模板 iter
        for (String s : c1) {
            if(s.contains("o")){
                c1.remove(s);//ConcurrentModificationException  错误
            }
        }
    }

    @Test
    public void test02(){
        Collection<String> c1 = new ArrayList<>();
        c1.add("hello");
        c1.add("world");
        c1.add("java");
        c1.add("atguigu");

        //  //增强for循环，快捷模板 iter
        for (String s : c1) {
            System.out.println(s);
        }
    }

    @Test
    public void test01(){
        String[] arr = {"hello","java","world"};

        //增强for循环，快捷模板 iter
        //这个s是一个局部变量，自己命名，代表元素
        for (String s : arr) {
            System.out.println(s);
        }
    }
}
