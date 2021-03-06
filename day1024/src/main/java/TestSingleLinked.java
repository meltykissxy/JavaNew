package com.atguigu;

import com.atguigu.test08.SingleLinked;

public class TestSingleLinked {
    public static void main(String[] args) {
        SingleLinked<String> list = new SingleLinked<>();
        list.add("hello");
        list.add("world");
        list.add("java");

        list.remove("java");

        for (String s : list) {
            System.out.println(s);
        }
        System.out.println("---------");
        list.remove("world");
        for (String s : list) {
            System.out.println(s);
        }
        System.out.println("---------");
        list.remove("hello");
        for (String s : list) {
            System.out.println(s);
        }

    }
}
