package test05;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

/*
5、Set与Map的关系
回忆：Set的实现类
    (1)HashSet：底层HashMap
    (2)LinkedHashSet：底层是LinkedHashMap
    (3)TreeSet：底层是TreeMap
今天：Map的实现类
    (1)HashMap
    (2)LinkedHashMap
    (3)TreeMap
    .....

问？HashSet和HashMap有关系吗？有， HashSet的底层就是HashMap

跟踪源码发现：
    public HashSet() {
        map = new HashMap<>(); ==> HashSet的底层就是HashMap
    }

问？但是我们存储到HashSet中的是一个一个的对象，而HashMap要求的是一对一对的键值对？
   那么，怎么处理从一个对象到一对键值对的呢？

   跟踪HashSet的add方法的源码：
     public boolean add(E e) {
        return map.put(e, PRESENT)==null;
    }

    ==>添加到set中的元素，是作为底层map的key
       value是一个PRESENT对象。

   再跟踪源码，HashSet中有一个：
    private static final Object PRESENT = new Object();
   ==>所有HashSet中的(key,value)的value是共享同一个对象，Object类型的PRESENT


 */
public class TestSetMap {
    @Test
    public void test3(){
        TreeSet<String> set = new TreeSet<>();
        set.add("张三");
        set.add("李四");
    }

    @Test
    public void test2(){
        LinkedHashSet<String> set = new LinkedHashSet<>();
        set.add("张三");
        set.add("李四");
    }

    @Test
    public void test1(){
        HashSet<String> set = new HashSet<>();
        set.add("张三");
        set.add("李四");
    }
}
