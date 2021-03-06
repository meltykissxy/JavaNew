package test08;

/*
三、链式存储结构
1、经典的实现
（1）单链表
（2）双链表
（3）二叉树
....


2、无论是哪种链式存储结构，都依赖于“结点”的概念
（1）单链表
    class Node{
        Object data;
        Node next;
    }

（2）双链表
    class Node{
        Object data;
        Node prev;
        Node next;
    }
（3）二叉树
    class TreeNode{
        Object data;
        TreeNode parent;
        TreeNode left;
        TreeNode right;
    }

3、各种链式存储结构的简单设计
（1）单链表
class SingleLinked{
    private Node head;//记录第一个结点地址
    private int size;//记录结点的个数
}
（2）双链表
class DoubleLinked{
    private Node first;//记录第一个结点地址
    private Node last;//记录最后一个结点地址
    private int size;//记录结点的个数
}
（3）二叉树
class MyTree{
    private TreeNode root;//记录根结点地址
    private int size;//记录结点的个数
}

4、所有的二叉树的遍历有三种方式：
（1）前序遍历：中左右
（2）后序遍历：左右中
（3）中序遍历：左中右

 */
public class TestLinked {
}
