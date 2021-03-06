package test02;

import java.util.LinkedList;

/*
看一个类型的成员的快捷键：Ctrl + F12


四、LinkedList的源码分析（部分）
1、LinkedList双向链表的结点类型
    private static class Node<E> {
        E item; //存储数据
        Node<E> next;//存储下一个结点的地址
        Node<E> prev;//存储上一个结点的地址

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

2、LinkedList双向链表需要记录链表的头、尾结点，以及结点的个数
int size = 0; //记录结点的个数
Node<E> first; //记录头结点，即第一个结点
Node<E> last;  //记录尾结点，即最后一个结点

3、new LinkedList<>()
    public LinkedList() {
    }
暂时还没有创建任何结点。

4、add(E e)
这里要特别注意：e是元素的值，不是结点本身。
    void linkLast(E e) {
        final Node<E> l = last;//用l记录尾结点
        final Node<E> newNode = new Node<>(l, e, null); //创建新结点
                //调用了Node的构造器：Node(Node<E> prev, E element, Node<E> next)
                //新结点的prev = l,  新结点的item = e, 新结点的next = null
                //让新结点的prev指向  原来链表的尾结点


                原来的链表的尾结点  <---  新结点

        last = newNode; //链表的尾结点现在是刚刚创建的新结点
        if (l == null)   //l == null ，l是原来的尾结点，如果是null，说明原来的链表是空的
            first = newNode;  //链表的第一个结点，现在是刚刚创建的新结点
        else            //l != null，l是原来的尾结点，如果不为null，说明原来的链表是非空的
            l.next = newNode; //原来的尾结点的next指向新结点     原来的链表的尾结点  --->  新结点
                             //到了这一步，如果原来链表是非空的     原来的链表的尾结点  <--->  新结点

        size++; //结点个数增加
        modCount++; //修改次数增加
    }

5、E get(int index)
注意：get的不是结点本身，而是结点中存储的数据item

    public E get(int index) {
        checkElementIndex(index);//检查index的合法性 [0,size)
        return node(index).item; //node(index)得到的是[index]位置的Node结点对象， node(index).item取出结点的item数据
    }

    Node<E> node(int index) {
        if (index < (size >> 1)) { // size>>1，等价于size/2, index< size/2，说明要返回的元素在链表的前半截
            Node<E> x = first;      //从first头结点开始找，比较快
            for (int i = 0; i < index; i++)  //这个for循环的目的就是把x从first结点移动 index的结点
                x = x.next;
            return x;
        } else {
            Node<E> x = last;       //从last尾结点开始找，
            for (int i = size - 1; i > index; i--) //这个for循环的目的就是把x从last结点移动 index的结点
                x = x.prev;
            return x;
        }
                                //返回的x就是[index]对应的Node结点
    }

6、remove(Object obj)

    public boolean remove(Object o) {
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) { //从first结点开始查找o对象对应的Node
                if (x.item == null) {
                    unlink(x);  //如果满足if条件，说明x是要被删除数据对应的Node结点
                    return true;
                }
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) { //从first结点开始查找o对象对应的Node
                if (o.equals(x.item)) {
                    unlink(x);  //如果满足if条件，说明x是要被删除数据对应的Node结点
                    return true;
                }
            }
        }
        return false;
    }

    //unlink方法的作用就是从当前链表中删除x结点
    E unlink(Node<E> x) {
        final E element = x.item; //被删除结点的数据
        final Node<E> next = x.next; //被删除结点的next
        final Node<E> prev = x.prev;  //被删除结点的prev

        if (prev == null) { //被删除结点的prev是null，说明被删除结点是当前链表的 第一个结点
            first = next;   //first现在指向被删除结点的next，即原来的第二个结点变成了链表的新的第一个结点
        } else {            //prev != null，说明被删除结点不是第一个结点
            prev.next = next;   //  prev结点<-->  x结点<--> next结点 三个结点，现在prev结点.next指向被删除结点的下个结点，即next
                                //   prev结点-->  next结点
            x.prev = null;      //把x结点（被删除结点）与它前一个结点  断开了
        }

        if (next == null) { //被删除结点的next为null，说明被删除结点是当前链表的最后一个结点
            last = prev;    //last现在指向被删除结点的prev，即原来倒数第二个结点成了现在的尾结点
        } else {            //说明被删除结点不是原来的尾结点
            next.prev = prev; //被删除结点的下一个结点的prev 指向被删除结点的prev
                               //这一步执行之前    prev结点  ---> next结点
                                                  x结点  <--> next结点
                              //这一步执行之后：   prev结点  <--> next结点
                                                 x结点    --> next结点
            x.next = null;      //把x（被删除结点）与它的下一个结点断开
                              //这一步执行之后：   prev结点  <--> next结点
                                                 x结点    孤立了
        }

        x.item = null;   //把x中的数据也清空了，等着给GC彻底回收了
        size--; //结点个数减少
        modCount++;//修改次数增加
        return element; //返回了被删除结点的数据
    }

 7、remove(int index)
     public E remove(int index) {
        checkElementIndex(index);//检查index合法性
        return unlink(node(index));

        //node(index)：找到[index]的结点
        //unlink(x)：删除x结果
    }
 */
public class TestLinkedList {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();

        list.add("hello");//"hello"只是LinkedList中Node的item数据项

        String s = list.get(0);

        list.remove("hello");

        list.remove(0);
    }
}
