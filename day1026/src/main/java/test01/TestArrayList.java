package test01;

import java.util.ArrayList;

/*
二、ArrayList的源码（部分）
变量和常量：
DEFAULTCAPACITY_EMPTY_ELEMENTDATA：长度为0的空数组
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
elementData：Object[]数组，即ArrayList中底层用来装元素的实际数组
size：记录实际存储元素的个数
DEFAULT_CAPACITY：默认容量值10



（1）new ArrayList<>()
    public ArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    为什么在new ArrayList<>()时，一开始不是创建长度为10的数组，而是初始化为一个长度为0的空数组呢？ ==》 再你装元素之前，先不创建数组，节省内存
        后期我们很多底层的代码（例如：DAO层，业务层）的方法的返回值类型是ArrayList或List的。
        DAO层：数据库访问层，该层的代码用来和数据库打交道，可能有一个方法，从数据库返回我们要查找的数据
                ArrayList<T> select (....)

                但是这个方法的实现时，存在两种情况：第一种，确实查到结果了，很多个对象存在ArrayList<T>集合中。
                                                 第二种，没有找到对应的结果，return 什么呢？
                                                            return null； ==》 调用者，收到了null值，
                                                            return new ArrayList(); ==> 调用者，收到了一个空集合对象。

                ArrayList<T> result = DAO层某个类的对象.select();
                    方式一：result可能为null，有的程序员就没有加if(result != null)判断，直接遍历，导致空指针异常。
                    方式二：result不会为空，但是没有元素。


（2）add(E e)
步骤：
第一步：检查elementData是否是DEFAULTCAPACITY_EMPTY_ELEMENTDATA，如果是，创建一个新数组，长度至少是10
第二步：如果还不够，再次扩容
        默认扩容为1.5倍
第三步：elementData[size++] = e;


 */
public class TestArrayList {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();

        list.add("hello");

    }
}
