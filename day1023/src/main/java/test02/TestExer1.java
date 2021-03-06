package com.atguigu.test02;

import sun.misc.ClassLoaderUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

/*
#### 1、练习1
* 添加100以内的质数到Collection的某个集合中
* 使用foreach遍历
* 使用Iterator遍历，并删除个位数是3个质数
* 删除11
* 查看最后剩下几个元素
* 添加10个100以内的随机整数到另一个Collection的集合中
* 求它们的交集
 */
public class TestExer1 {
    public static void main(String[] args) {
        Collection<Integer> coll = new ArrayList<>();

        //找出100以内的质数，添加到coll中
        for(int i=2; i<100; i++){
            //判断i是否是质数
            boolean flag = true;
            //在[2,i-1]范围内找是否有i的约数，如果有，i就不是质数
            for(int j=2; j<i; j++){
                if(i%j==0){
                    flag = false;
                    break;
                }
            }
            if(flag){
                coll.add(i);
            }
        }

//        使用foreach遍历
        System.out.println("100以内的质数有：");
        for (Integer integer : coll) {
            System.out.print(integer + " ");
        }
        System.out.println();

        System.out.println("---------------------");
        //使用Iterator遍历，并删除个位数是3个质数
        Iterator<Integer> iterator = coll.iterator();
        while(iterator.hasNext()){
            Integer element = iterator.next();
            if(element % 10 ==3){//个位数是3
                iterator.remove();//必须用迭代器的remove方法
            }
        }
        //查看删除后的结果
        System.out.println("删除个位数是3的质数后：");
        for (Integer integer : coll) {
            System.out.print(integer + " ");
        }
        System.out.println();

        System.out.println("---------------------");
        //* 删除11
        //因为11是明确目标，直接用集合的删除方法
        coll.remove(11);
        System.out.println("删除11后：" + coll.size());
        for (Integer integer : coll) {
            System.out.print(integer + " ");
        }
        System.out.println();

        System.out.println("-----------------------------");
        //添加10个100以内的随机整数到另一个Collection的集合中
        Collection<Integer> other = new ArrayList<>();
        Random random = new Random();
        for (int i=1; i<=10; i++){
            other.add(random.nextInt(100));
           // other.add((int)(Math.random() * 100));
        }

        System.out.println("另一个集合的元素有：");
        System.out.println(other);

        System.out.println("-----------------------------");
        System.out.println("它们的交集：");
        coll.retainAll(other);
        for (Integer integer : coll) {
            System.out.print(integer + " ");
        }
        System.out.println();
    }
}
