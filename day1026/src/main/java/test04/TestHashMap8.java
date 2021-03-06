package test04;

import java.util.HashMap;
import java.util.Objects;

/*
六、JDK1.8HashMap的源码分析
（一）看一下常量和变量
（1）DEFAULT_INITIAL_CAPACITY：默认的初始容量 16
（2）MAXIMUM_CAPACITY：最大容量  1 << 30
（3）DEFAULT_LOAD_FACTOR：默认加载因子 0.75
（4）TREEIFY_THRESHOLD：默认树化阈值8，当链表的长度达到这个值后，要考虑树化
（5）UNTREEIFY_THRESHOLD：默认反树化阈值6，当树中的结点的个数达到这个阈值后，要考虑变为链表
（6）MIN_TREEIFY_CAPACITY：最小树化容量64
		当单个的链表的结点个数达到8，并且table的长度达到64，才会树化。
		当单个的链表的结点个数达到8，但是table的长度未达到64，会先扩容
（7）Node<K,V>[] table：数组
（8）size：记录有效映射关系的对数，也是Entry对象的个数
（9）int threshold：阈值，当size达到阈值时，考虑扩容
（10）double loadFactor：加载因子，影响扩容的频率

（二）问题扫盲
1、为什么JDK1.8会出现红黑树和链表共存呢？
  如果table[index]下面的链表过长，会导致操作效率降低，使用红黑树。
  红黑树，是一个平衡二叉树，左右的层次的绝对值不大于1，从根结点到达叶子结点经历过的黑结点的数量一样，所以查询效率比较高。

2、什么时候树化？什么时候反树化？
  树化两个条件：（1）链表的结点个数达到8个，（2）table.length达到64
  反树化：在删除之后，再次添加的过程中，会数树的结点个数，还要判断其他的条件，考虑反树化。
        临界值，结点个数<=6，开始考虑反树化。


（三）源码
1、new HashMap<>()
    public HashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
    }

2、put(K key, V value)
    public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
    }

    hash(key)方法：对key的原始的hashCode值做一个干扰运算。

    第一次调用putVal方法，进去之后满足if ((tab = table) == null || (n = tab.length) == 0)，
            if条件满足之后，会创建一个长度为16的Node类型的数组，threshold = 16*0.75=12


    final V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict) {
        Node<K,V>[] tab;
        Node<K,V> p;
        int n, i;

        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;

        if ((p = tab[i = (n - 1) & hash]) == null)  //i = (n - 1) & hash] = (数组.length-1)& hash   p记录是table[i]的头结点/树的根结点
                                                    //if(p==null)那么说明table[i]下面是空的
            tab[i] = newNode(hash, key, value, null); //直接创建一个Node结点放到table[i]下面即可
        else {
            Node<K,V> e; K k;
            if (p.hash == hash && ((k = p.key) == key || (key != null && key.equals(k)))) //新的(key,value)与table[i]的下根结点p的key重复了
                e = p; //用e记录了p
            else if (p instanceof TreeNode) //说明table[i]下面是红黑树，p是树结点，并且是树的根结点
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value); //尝试看是否可以把新的(key,value)添加到这个红黑树中
                                                                            //如果在尝试添加过程中，发现红黑树中某个结点的key与新的key重复，用e记录
                                                                            //如果没找到重复的key,新的(key,value)就会添加到红黑树中，并且e是null

            else {  //如果到达这里，说明table[i]的下面是链表，并且头结点不重复
                for (int binCount = 0; ; ++binCount) { //binCount是记录当前table[i]下面的链表的结点的个数
                    if ((e = p.next) == null) { //p代表的是table[i]某个结点，e是p.next，它为null，说明是table[i]的最后一个结点
                                                //满足这个条件，说明table[i]没有key重复的
                        p.next = newNode(hash, key, value, null); //把新结点链接到当前链表的最后
                                                                    //记忆：JDK1.7是添加到链表的头，JDK1.8是添加到链表的尾，七上八下
                        if (binCount >= TREEIFY_THRESHOLD - 1) //  因为本次循环结点还未统计到binCount中，所以这里-1
                            treeifyBin(tab, hash);//考虑树化的方法
                        break;
                    }
                    if (e.hash == hash &&((k = e.key) == key || (key != null && key.equals(k)))) //在链表中找到了key重复的结点
                        break;
                    p = e;
                }
            }
            if (e != null) { //e不为空，说明找到了重复的key，用新的 value替换旧的value
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        if (++size > threshold)  //（key,value)键值对的总个数size+1后，如果大于阈值
            resize(); //扩容，数组长度扩容为原来的2倍，并且会对所有的(key,value)重新计算位置
        afterNodeInsertion(evict);
        return null;
    }

   final void treeifyBin(Node<K,V>[] tab, int hash) {
        int n, index; Node<K,V> e;
        if (tab == null || (n = tab.length) < MIN_TREEIFY_CAPACITY) //如果table是空的，或者table的长度未到达64，先扩容
            resize();
        else if ((e = tab[index = (n - 1) & hash]) != null) {//把链表转为红黑树
            TreeNode<K,V> hd = null, tl = null;
            do {
                TreeNode<K,V> p = replacementTreeNode(e, null);
                if (tl == null)
                    hd = p;
                else {
                    p.prev = tl;
                    tl.next = p;
                }
                tl = p;
            } while ((e = e.next) != null);
            if ((tab[index] = hd) != null)
                hd.treeify(tab);
        }
    }
 */
public class TestHashMap8 {
    public static void main(String[] args) {
        HashMap<MyKey, String> map = new HashMap<>();

//        map.put("哈哈","xx");

        for (int i=1; i<=40; i++){
            map.put(new MyKey(i), "xxx"+i);
        }
        /*
        [1]下面存储了：1,2,3,5,7,9,11,13,15,17
        13时，table的长度从16->32
        15时，table的长度从32->64
        17时，table[1]下面链表转为红黑树
         */

        for(int i=1; i<=40; i++){
            map.remove(new MyKey((i)));
        }
    }
}

//特殊类型作为key
class MyKey{
    private int i;

    public MyKey(int i) {
        this.i = i;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyKey myKey = (MyKey) o;
        return i == myKey.i;
    }

    @Override
    public int hashCode() {
//        return Objects.hash(i);
        //这里有玄机
        if(i%2 != 0){
            return 1; //制造hashCode冲突，希望他们存储到同一个table[i]下面
        }
        return Objects.hash(i);
    }
}