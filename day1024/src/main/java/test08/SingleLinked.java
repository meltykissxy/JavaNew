package test08;

import java.util.Iterator;

public class SingleLinked<T> implements Iterable<T>{
    //单链表需要记录第一个结点的地址
    private Node head;
    private int size;//结点的个数

    //这个结点只在这里有用，它服务于单链表SingleLinked，所以用内部类实现
    private class Node{
        //单链表的结点，包含：数据和下一个结点的地址
        Object data;
        Node next;

        Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public void add(T data){
        Node newNode = new Node(data, null);//新结点的next是null，新结点的数据就是我们传入的元素
        if(head==null){//说明单链表是空的
//            head = 新结点;
            head = newNode;
        }else{
            //找到现在链表的最后一个结点
            Node node = head;
            while(node.next != null){//node.next==null结束
                node = node.next;
            }
            //把新结点放到最后一个结点的next中
            node.next = newNode;
        }
        size++;
    }

    public void remove(Object obj){//obj不是结点，是结点的数据
        if(head==null){
            return;//结束删除
        }

        //单链表非空
        if(obj == null){
            //都是从第一个头结点开始找
            Node node = head;
            Node prevNode = null;//node现在是head，它没有前一个结点
            while(node!= null){
                if(node.data == obj){
                    if(prevNode == null){//说明删除的是头结点
                        head = head.next;
                    }else {
                        prevNode.next = node.next;
                    }
                    size--;
                    return;
                }
                prevNode = node;
                node = node.next;
            }

            //退出循环   代码到达这里  while(node!=null)不成立 node==null
        }else{
            //都是从第一个头结点开始找
            Node node = head;
            Node prevNode = null;//node现在是head，它没有前一个结点
            while(node!= null){
                if(obj.equals(node.data)){
                    if(prevNode == null){//说明删除的是头结点
                        head = head.next;
                    }else {
                        prevNode.next = node.next;
                    }
                    size--;
                    return;
                }
                prevNode = node;
                node = node.next;
            }
        }

    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator{
        private Node node = head;

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public Object next() {
            Object data = node.data;
            node = node.next;
            return data;
        }
    }
}
