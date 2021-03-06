package test01;

import java.util.Vector;

/*
三、modCount变量
（1）在集合的哪些方法中出现了
modCount：modify Count，修改次数 ==> 集合的元素的增、减等操作。替换元素和查询元素和他无关。
在add 和 remove方法中都看到了 modCount++;

（2）在集合的迭代器源码中出现了，
例如：Vector或ArrayList里面有一个内部类迭代器Itr
    int expectedModCount = modCount;

    每次new Itr对象时，expectedModCount初始化为当前的modCount值。
    即我们获取迭代器对象的瞬间，就记录了当前集合的modCount值。

    当我们用Itr对象去迭代集合时，调用next()时，会进行modCount的检查：
        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }

    这里考虑的是多线程，如果多个线程使用了同一个集合对象，一个线程正在遍历这个集合，另一个线程在对该集合进行
    添加元素或者删除元素，就会导致modCount变了。
    expectedModCount和modCount就不一样，就会报异常。

    为什么要报异常呢？
    因为当前Itr迭代器正遍历集合时，有一个cursor（游标），记录当前即将遍历的元素的下标。
    假设：我现在当前线程，正要遍历 cursor=3这个元素，另一个集合删除了元素，可能当前集合根本没有[3]这个元素。
                                                 还有一种情况[3]元素不是我刚刚要遍历，因为其他线程可能添加和删除时，移动了元素。
         为了避免这种情况，我们在访问[3]这个元素之前，就先检查expectedModCount和modCount的值，如果不一致，就说明集合被别人动过，
         应该快速失败，而不是冒着风险进行操作。


（3）如果调用的是迭代器自己的remove，删除了集合的元素怎么办？
    在里面会及时的同步expectedModCount和modCount的值。

（4）问题，如果我们在使用集合过程中，报了ConcurrentModificationException异常（并发修改异常）？
原因只有两个：
A：确实有多个线程同时操作同一个集合，导致并发问题
B：在使用foreach或Iterator迭代器的过程中，你擅自自己调用了  集合的add,remove等方法了。
这两个方式都会导致expectedModCount和modCount的值不一致。


 */
public class TestModCount {
    public static void main(String[] args) {
        Vector<String> vector = new Vector<>();
        vector.add("hello");
        vector.remove("hello");

        String s = vector.get(0);//里面没有modCount++;
        vector.set(0,"java");//里面没有modCount++;
    }
}
