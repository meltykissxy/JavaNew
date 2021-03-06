package com.atguigu.test05;

import java.util.Stack;

/*
五、List接口的常用实现类们
1、List接口的常用实现类们
（1）ArrayList<E>：动态数组
（2）Vector<E>：动态数组
（3）LinkedList<E>：链表
（4）Stack<E>：栈
实现类们远不止它们几个，只是基础阶段，先学习它们。


2、ArrayList<E>与Vector<E>区别
它俩都是动态数组，那么有什么区别呢？
（1）出现的顺序不同
Vector<E>它最古老的动态数组，JDK1.0就有了，甚至比List接口还早。
ArrayList<E>它是后来增加的动态数组。
（2）Vector<E>线程安全的
    ArrayList<E>线程不安全的，当我们是单线程操作集合时，考虑用它，效率更高
（3）Vector<E>，底层是数组，数组的初始化容量是10。不够了再扩容，每次扩容为原来的2倍。
    ArrayList<E>，底层是数组，数组的初始化容量是10。不够了再扩容，每次扩容为原来的1.5倍。

    2倍的，浪费的可能性多了，扩容次数少；
    1.5，浪费的可能性少了，扩容的次数多了。

    如果我们对元素的个数有预估，直接使用Vector(int initialCapacity) 或ArrayList(int initialCapacity)
（4）Vector<E>除了现在的迭代和遍历方式支持以外，还支持古老的遍历方式：Enumeration<E> elements()
    ArrayList不支持Enumeration<E>迭代器

3、动态数组与    LinkedList<E>区别？
动态数组：底层是数组结构
LinkedList<E>：底层是链表结构，而且是双向链表

数组结构：要求在堆中开辟连续的一整块存储空间
        好处：元素因为是连续的，遍历和查找某个位置的元素效率非常高的（根据[index]操作效率很高）
        缺点：如果在中间插入或删除元素，需要移动元素
链表：不需要开辟连续的存储空间
        缺点：因为元素不连续，某个结点只能知道它前面和后面的元素的位置，
                如果要根据[index]操作的话，只能从头开始数，挨个计数，才能找到对应[index]位置。
        好处：如果在中间插入或删除元素，只需要修改前后元素的next和previous就可以，不需要大量移动元素。
             在我们创建链表时，不需要提前申请空间，只要在添加元素时，单独给这个元素创建结点即可，所以没有空间浪费问题。

    思考？ 判断某个元素是否在动态数组或链表中存在，它俩的效率是否有差异？没有太大差异，因为都要挨个查看。

4、Stack：栈
Stack是Vector的子类，它只是在Vector的基础上，增加了几个方法，来模拟栈的效果。
栈：先进后出（FILO：First In Last Out）或 后进先出（Last In First Out)
    方法栈就是一个栈结构。

    方法：
    A：E push(E item)  ：把元素压入栈，新增的元素是栈顶元素
    B：E pop() ：弹出栈顶元素
    C：E peek()  ：查看栈顶元素，但是不拿走
    D：int search(Object o),类似于indexOf(Object obj)，不同的是它从1基数开始，从栈顶开始数。

5、LinkedList：双向链表
    它除了实现List接口之外，还实现Deque<E>, Queue<E>
    Deque<E>：名称 deque 是“double ended queue（双端队列）”的缩写，通常读为“deck”。
    Queue<E>：队列通常（但并非一定）以 FIFO（先进先出）的方式排序各个元素。
        后面很多地方用到，例如：消息队列、线程队列
 */
public class TestListImpl {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);

//        System.out.println(stack.pop());
//        System.out.println(stack.pop());
//        System.out.println(stack.pop());
//        System.out.println(stack.pop());

//        System.out.println(stack.peek());
//        System.out.println(stack.peek());
//        System.out.println(stack.peek());
//        System.out.println(stack.peek());

        System.out.println(stack.search(2));
    }
}
