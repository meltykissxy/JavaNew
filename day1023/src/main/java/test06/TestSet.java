package com.atguigu.test06;

import org.junit.Test;

import java.text.Collator;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

/*
六、Set集合
1、Set也是Collection子接口
==>Collection有的方法，它都有

2、Set接口没有扩展Collection以外的方法。

3、Set接口的介绍：
一个不包含重复元素的 collection。接口模仿了数学上的 set 抽象。

4、它常用的实现类们
（1）HashSet：元素是不可重复，元素是完全无序的，
        存储和遍历的顺序与添加的顺序无关。
        它的存储和对象的hashCode值有关系。
（2）LinkedHashSet：元素是不可重复，存储也是和hashCode值有关系，和添加顺序无关，即不是完全按照添加的顺序依次存储。
        但是它每个元素会记录添加的顺序用(prev,next等）。
（3）TreeSet：元素是不可重复，存储元素和元素的大小有关。
        要求，元素要么实现Comparable接口，还要在创建TreeSet时，传入Comparator接口的实现类对象

    思考：如何选择HashSet还是LinkedHashSet？
           如果你对元素只有不可重复的要求，选择HashSet。
           如果你对元素既要不可重复，还要保留添加顺序的信息，那么选择LinkedHashSet。
           因为LinkedHashSet每次添加元素和删除元素时，都要去记录和保留添加顺序的信息，做的事情多，效率低。

 */
public class TestSet {
    @Test
    public void test05() {
        TreeSet<Teacher> set = new TreeSet<>(new Comparator<Teacher>() {
            @Override
            public int compare(Teacher o1, Teacher o2) {
                return Collator.getInstance().compare(o1.getName(),o2.getName());
            }
        });//可以用定制比较器对象

        set.add(new Teacher("宋宋"));
        set.add(new Teacher("柴老师"));
        set.add(new Teacher("孙艺萌"));

        for (Teacher teacher : set) {
            System.out.println(teacher);
        }
    }


    @Test
    public void test04() {
        TreeSet<Teacher> set = new TreeSet<>();

        //ClassCastException: com.atguigu.test06.Teacher cannot be cast to java.lang.Comparable
        //因为Teacher不支持比较大小
        set.add(new Teacher("宋宋"));
        set.add(new Teacher("柴老师"));
        set.add(new Teacher("孙艺萌"));

        for (Teacher teacher : set) {
            System.out.println(teacher);
        }
    }
    @Test
    public void test03() {
        TreeSet<String> set = new TreeSet<>();
        set.add("hello");
        set.add("java");
        set.add("chai");
        set.add("world");

        for (String s : set) {
            System.out.println(s);
        }
        /*
        chai
        hello
        java
        world
         */
    }

    @Test
    public void test02(){
        LinkedHashSet<String> set = new LinkedHashSet<>();
        set.add("张三");
        set.add("李四");
        set.add("王五");
        set.add("赵六");

        for (String s : set) {
            System.out.println(s);
        }
        /*
        张三
        李四
        王五
        赵六
         */
    }


    @Test
    public void test01(){
        HashSet<String> set = new HashSet<>();
        set.add("张三");
        set.add("李四");
        set.add("王五");
        set.add("赵六");

        for (String s : set) {
            System.out.println(s);
        }
        /*
        李四
        张三
        王五
        赵六
         */
    }
}

/*
class Teacher implements Comparable<Teacher>{
    private String name;

    public Teacher(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Teacher o) {
        return Collator.getInstance().compare(this.name,o.name);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                '}';
    }
}*/
class Teacher {
    private String name;

    public Teacher(String name) {
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
        return "Teacher{" +
                "name='" + name + '\'' +
                '}';
    }
}