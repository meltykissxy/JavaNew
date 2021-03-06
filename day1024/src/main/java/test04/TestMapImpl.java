package test04;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

/*
4、Map的实现类们
（1）HashMap：哈希表
（2）Hashtable：哈希表
（3）LinkedHashMap
（4）TreeMap
（5）Properties
（6）ConcurrentHashMap（在juc中讲解）
...

问题1：HashMap与Hashtable都是哈希表，有什么区别？
类似的区别：StringBuffer和StringBuilder，Vector和ArrayList

Hashtable：旧版的，JDK1.0就有的
            线程安全的
            不允许key,value为null值
HashMap：后增的，JDK1.2加入的
            线程不安全的
            允许key,value为null值，key为null会特殊处理，存储在特殊的位置

问题2：  HashMap和    LinkedHashMap的区别
类似的区别：HashSet和LinkedHashSet
 HashMap：无序的，存储和遍历的顺序和添加的顺序无关。
LinkedHashMap：会记录键值对的添加顺序，遍历时，会按照添加的顺序遍历
LinkedHashMap是HashMap的子类。

问题3：TreeMap有什么特点呢？
    TreeMap的键值对存储和key的大小有关，所以要求key必须实现java.lang.Comparable接口
                                            或者在创建TreeMap时传入java.util.Comparator接口的实现类对象

问题4：Properties有什么特点？
（1）Properties不需要指定泛型
（2）Properties继承Hashtable，不允许key,value为null
（3）Properties的key和value都是String类型
（4）它的作用是用来存储 系统属性
（5）建议添加(key,value)使用的是setProperty
（6）获取value的时候使用的是getProperty
 */
class MyKey{
    private int i;

    public MyKey(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    @Override
    public String toString() {
        return "MyKey{" +
                "i=" + i +
                '}';
    }
}

public class TestMapImpl {
    @Test
    public void test08() throws IOException {
        //演示后期使用Properties封装我们自己的属性配置文件的内容
        //在src下创建一个jdbc.properties文件，src下new，File
        Properties properties = new Properties();

        //加载文件
        //这个先知道是加载src下的配置文件，相关API在反射章节再讲
        properties.load(TestMapImpl.class.getClassLoader().getResourceAsStream("jdbc.properties"));

        //看一下用户名是什么
        String user = properties.getProperty("user");
        System.out.println(user);
    }

    @Test
    public void test07() {
        //System代表系统
        Properties properties = System.getProperties();//获取所有的系统属性
        //properties是一个map，按照map遍历
        Set<Map.Entry<Object, Object>> entries = properties.entrySet();
        for (Map.Entry<Object, Object> entry : entries) {
            System.out.println(entry);
        }
    }

    @Test
    public void test06() {
        Properties properties = new Properties();
        properties.setProperty("user","chai");
        properties.setProperty("password","123");

        String value = properties.getProperty("user");
        System.out.println(value);
    }

    @Test
    public void test05() {
        TreeMap<MyKey, String> map = new TreeMap<>(new Comparator<MyKey>() {
            @Override
            public int compare(MyKey o1, MyKey o2) {
                return Integer.compare(o1.getI(),o2.getI());
            }
        });
        map.put(new MyKey(3), "xxx");
        map.put(new MyKey(1), "xxx");
        map.put(new MyKey(1), "yyy");
        map.put(new MyKey(2), "xxx");
        map.put(new MyKey(4), "xxx");

        System.out.println(map);

    }

    @Test
    public void test04() {
        TreeMap<MyKey, String> map = new TreeMap<>();
        map.put(new MyKey(1), "xxx");//ClassCastException: com.atguigu.test04.MyKey cannot be cast to java.lang.Comparable
        map.put(new MyKey(2), "xxx");
        map.put(new MyKey(3), "xxx");
        map.put(new MyKey(4), "xxx");

        System.out.println(map);

    }

    @Test
    public void test03(){
        //String实现了java.lang.Comparable
        TreeMap<String,String> map = new TreeMap<>();
        map.put("zhangsan","xxx");
        map.put("lisi","xxx");
        map.put("wangwu","xxx");
        map.put("zhaoliu","xxx");

        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println(entry);
        }
        /*
        lisi=xxx
        wangwu=xxx
        zhangsan=xxx
        zhaoliu=xxx
         */

    }

    @Test
    public void test02(){
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("张三","xxx");
        map.put("李四","xxx");
        map.put("王五","xxx");
        map.put("赵六","xxx");

        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println(entry);
        }
        /*
        张三=xxx
        李四=xxx
        王五=xxx
        赵六=xxx
         */
    }

    @Test
    public void test01(){
        HashMap<String,String> map = new HashMap<>();
        map.put("张三","xxx");
        map.put("李四","xxx");
        map.put("王五","xxx");
        map.put("赵六","xxx");

        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println(entry);
        }
        /*
        李四=xxx
        张三=xxx
        王五=xxx
        赵六=xxx
         */
    }
}
