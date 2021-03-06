package com.atguigu.test01;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class TestIterator {
    @Test
    public void test03() {
        Collection<Integer> coll = new ArrayList<>();
        coll.add(1);
        coll.add(2);
        coll.add(3);

        Iterator<Integer> iterator = coll.iterator();
        while(iterator.hasNext()){//循环一次，拿两个元素
            Integer element = iterator.next();
            System.out.println(iterator.next());
        }
        //出现漏掉1和3，并且最后出现NoSuchElementException，因为3后面没有元素了
    }

    @Test
    public void test02(){
        Collection<Integer> coll = new ArrayList<>();
        coll.add(1);
        coll.add(2);
        coll.add(3);

        Iterator<Integer> iterator = coll.iterator();
        //得到一个迭代器对象，此时迭代器对象的游标，指向集合开头元素
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        System.out.println("------------------");
        //当经过上面的while循环，迭代器对象的游标已经到达集合的结尾
        //所以，需要再次调用iterator()方法，获取新的迭代器对象，即让游标回到开头
        iterator = coll.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Test
    public void test01(){
        Collection<Integer> coll = new ArrayList<>();
        coll.add(1);
        coll.add(2);
        coll.add(3);

        Iterator<Integer> iterator = coll.iterator();
        //得到一个迭代器对象，此时迭代器对象的游标，指向集合开头元素
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        //当经过上面的while循环，迭代器对象的游标已经到达集合的结尾
        System.out.println("------------------");
        //接下去循环，hasNext()返回false  ，所以下面的循环遍历，啥也不打印
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
