package com.atguigu.test02;

import org.junit.Test;

import java.util.*;

/*
Java核心类库中常用的集合分为两大类：
（1）Collection：存储一组对象
（2）Map：存储一对一对的键值对，(key,value)

六、Map
1、Map系列的集合是用来存储一对一对的键值对，(key,value)
2、java.util.Map<K,V>
其中的<K>代表的是key的类型，键的类型
其中的<V>代表的是value的类型，值的类型

说明：key是map中非常重要的键，同一个map中是不允许出现“重复”的key
     value是可以重复的。

3、Map接口的API：
（1）添加
V put(K key, V value)  ：添加一对键值对
void putAll(Map<? extends K,? extends V> m) ：添加多对键值对
（2）删除
void clear()
 V remove(Object key)  ：根据key删除一对键值对

（3）修改
再次对同一个key，put另一个value，可以覆盖原来的value

但是：我们不能修改key。
    如果要修改key，只能先根据原来的key删除键值对，然后添加新的key的键值对。千万不要直接修改key。

（4）查询
int size()
 V get(Object key) ：根据key，找value
 boolean containsKey(Object key)
 boolean containsValue(Object value)
 boolean isEmpty()

（5）遍历
Collection<V> values()：返回所有的value值
            为什么所有的value值，构成的是一个Collection。而不是Set啥的呢？
                如果Set的话，就不能重复了，而value是可以重复的。
            Collection是接口，那么它用什么实现类的对象来装所有value的呢？
                在Map接口的实现类中，例如：HashMap中有一个内部类（例如：Values)实现Collection接口。

Set<K> keySet()：返回所有的key值
            为什么所有的 key值，构成的是一个Set？
                因为所有的key不可重复的
            Set是接口，那么它用什么实现类的对象来装所有key的呢？
                在Map接口的实现类中，例如：HashMap中有一个内部类（例如：KeySet）实现Set接口

Set<Map.Entry<K,V>> entrySet()：返回所有的(key,vaule)键值对
        （1）为什么所有的(key,value)键值对，构成了一个Set？
                因为key不重复，(key,value)也不能重复。
        （2）(key,value)的类型是什么？
                Set接口的类型：Set<E>，E代表是元素类型
                              Set<Map.Entry<K,V>>，那么Map.Entry<K,V>是代表元素类型，这里的元素就是(key,value)键值对
                Map.Entry<K,V>：它是Map接口的静态内部接口类型。
        （3）Map.Entry<K,V>是接口类型，那么它的实现类在哪里？是谁？
                在Map接口的实现类中，例如：HashMap中有一个内部类（例如：Node，TreeNode等）实现Map.Entry接口
         （4）Set是接口，那么它用什么实现类的对象来装所有(key,value)的呢？
                在Map接口的实现类中，例如：HashMap中有一个内部类（例如：EntrySet）实现Set接口

 */
public class TestMap {
    @Test
    public void test11() {
        Map<String, String> map = new HashMap<>();

        map.put("王阳", "翠花");
        map.put("秦杰", "如花");
        
        //直接获取所有的(key,value)
        //左边的泛型比较麻烦，建议大家，先写=右边，然后用快捷键 ctrl +alt +v
        Set<Map.Entry<String, String>> entries = map.entrySet();
        //Map.Entry<String, String>是entries集合的元素类型
        for (Map.Entry<String, String> entry : entries) {
            System.out.println(entry);
        }
    }

    @Test
    public void test10() {
        Map<String, String> map = new HashMap<>();

        map.put("王阳", "翠花");
        map.put("秦杰", "如花");

        //演示，先得到key，然后再根据key获取value，效率低点
        for (String key : map.keySet()) {
            System.out.println(key + "-> " + map.get(key));//map.get(key)根据key再从map中找value
        }
    }

    @Test
    public void test09() {
        Map<String, String> map = new HashMap<>();

        map.put("王阳", "翠花");
        map.put("秦杰", "如花");

        //演示遍历所有的key
        //这里不是Map直接支持foreach循环，而是先得到Set对象，然后Set对象支持foreach循环
        for (String key : map.keySet()) {//map.keySet()返回了一个Set的对象，是一个集合对象
            System.out.println(key);
        }
    }

    @Test
    public void test08() {
        Map<String, String> map = new HashMap<>();

        map.put("王阳", "翠花");
        map.put("秦杰", "如花");
        
        //演示遍历所有的key
        Set<String> keys = map.keySet();//ctrl +alt +在keySet()方法名单击左键
        for (String key : keys) {
            System.out.println(key);
        }
    }

    @Test
    public void test07() {
        Map<String, String> map = new HashMap<>();

        map.put("王阳", "翠花");
        map.put("秦杰", "如花");

        //遍历所有的value
        Collection<String> values = map.values();//这里values()方法看源码应该看HashMap中values，
                                                //编译时map看Map接口类型，运行时执行的是HashMap实现类
        for (String value : values) {
            System.out.println(value);
        }
    }

    @Test
    public void test06() {
        Map<String, String> map = new HashMap<>();

        map.put("王阳", "翠花");
        map.put("秦杰", "如花");

        //演示map是否支持foreach循环
 /*       for (String s : map) {//不直接支持foreach，因为Map接口没有继承java.lang.Iterable<T>接口

        }*/

        //map有iterator()方法
//        map.iterator();//没有

    }


    @Test
    public void test05() {
        Map<String, String> map = new HashMap<>();//左边是Map接口，右边是Map接口的实现类

        //演示删除问题
        map.put("王阳", "翠花");
        map.put("秦杰", "如花");

        System.out.println("删除之前：" + map);//删除之前：{秦杰=如花, 王阳=翠花}
        map.remove("王阳");
        System.out.println("删除之后：" + map);//删除之后：{秦杰=如花}
    }


    @Test
    public void test04(){
        //演示key修改的问题
        //这里把key定为Student类型，是为了演示修改key的问题，因为String是不可变
        Map<Student,String> map = new HashMap<>();
        Student key = new Student("张三");
        map.put(key, "xxx");
        System.out.println(map);

        key.setName("张三丰");//修改了key
        System.out.println(map);

        String value = map.get(key);//如果重写了hashCode和equals方法，key修改了，就get不到value了
        System.out.println(value);

    }

    @Test
    public void test03(){
        Map<String,String> map = new HashMap<>();//左边是Map接口，右边是Map接口的实现类

        //演示key重复问题
        map.put("王阳","翠花");
        map.put("秦杰","如花");
        map.put("王阳","凤姐");

        System.out.println(map);//{秦杰=如花, 王阳=凤姐}
    }

    @Test
    public void test02(){
        Map<String,String> map = new HashMap<>();//左边是Map接口，右边是Map接口的实现类

        map.put("王阳","翠花");
        map.put("秦杰","如花");

        Map<String,String> map2 = new HashMap<>();
        map2.put("含笑","翠花");
        map2.put("王克","凤姐");

        map.putAll(map2);

        System.out.println(map.size());
        System.out.println(map);//{秦杰=如花, 王阳=翠花, 含笑=翠花, 王克=凤姐}
    }

    @Test
    public void test01(){
        //存储咱们班同学的姓名和他（她）对象的姓名
        //key：咱们班同学的姓名，String
        //value：他（她）对象的姓名，String  或  ArrayList<String>  或 String[]
        //目前我们选择一对一
        Map<String,String> map = new HashMap<>();//左边是Map接口，右边是Map接口的实现类

        map.put("王阳","翠花");
        map.put("秦杰","如花");

        System.out.println(map.size());
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}