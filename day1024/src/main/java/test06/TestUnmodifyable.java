package test06;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
Collections工具类中有方法：把某个集合变为不可变集合
Arrays工具类中有一个方法：public static <T> List<T> asList(T... a)
 */
public class TestUnmodifyable {
    @Test
    public void test2(){
        List<String> strings = Arrays.asList("hello", "java", "world");
        strings.add("atguigu");//UnsupportedOperationException
        for (String string : strings) {
            System.out.println(string);
        }

        //getClass()是Object声明的，表示返回对象的运行时类型
        System.out.println(strings.getClass());//class java.util.Arrays$ArrayList
    }

    @Test
    public void test1(){
        ArrayList<String> list = new ArrayList<>();
        list.add("hello");
        list.add("world");

        List<String> strings = Collections.unmodifiableList(list);
//        strings.add("java");//UnsupportedOperationException

        for (String string : strings) {
            System.out.println(string);
        }
    }
}
