package test07;

import org.junit.Test;

import java.util.Iterator;

/*
演示：自己定义一个动态数组，即自己设计一个集合
 */
public class TestMyArrayList {
    @Test
    public void test05() {
        MyArrayList<String> list = new MyArrayList<>();

        list.add("hello");
        list.add("java");
        list.add("world");

        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
    @Test
    public void test04() {
        MyArrayList<String> list = new MyArrayList<>();

        list.add("hello");
        list.add("java");
        list.add("world");
        list.add("world");

        System.out.println(list.indexOf("haha"));
        System.out.println(list.indexOf("world"));
    }
    @Test
    public void test03() {
        MyArrayList<String> list = new MyArrayList<>();

        list.add("hello");
        list.add("java");
        list.add("world");

        list.remove("java");

        for (String s : list) {
            System.out.println(s);
        }
    }

    @Test
    public void test02() {
        MyArrayList<String> list = new MyArrayList<>();

        list.add("hello");
        list.add("java");
        list.add("world");

        list.remove(0);

        for (String s : list) {
            System.out.println(s);
        }
    }

    @Test
    public void test01(){
        MyArrayList<String> list = new MyArrayList<>();

        list.add("hello");
        list.add("java");
        list.add(0,"atguigu");

        System.out.println(list.size());

        for (String s : list) {
            System.out.println(s);
        }
    }
}
