package map;

import java.util.HashMap;

/*
2、HashMap在JDK1.7的源码分析（部分）
int DEFAULT_INITIAL_CAPACITY = 1 << 4;默认的初始化容量16
float DEFAULT_LOAD_FACTOR = 0.75f; 默认的加载因子0.75
float loadFactor; 加载因子，如果没有手动指定，默认它的值就会初始化为0.75
int threshold; 阈值,xx的临界值
Entry<?,?>[] EMPTY_TABLE = {}; 长度为0的空数组
Entry<K,V>[] table = (Entry<K,V>[]) EMPTY_TABLE;; 这是一个数组，用来存储(key,value)，这个Entry是HashMap一个内部类，它实现了Map接口的内部接口Entry类型。
int size; 存储到HashMap中(key,value)键值对的数量


（1）HashMap底层是数组+链表
数组必须创建，并且指定长度，才能装对象。
一开始第一次new数组的长度，如果没有特别指定，长度就初始化为16。

（2）虽然我们指定了数组的长度，但是随着我们存储到HashMap中的(key,value)键值对越来越多，
那么就算我们用了链表来避免HashCode冲突的问题，但是因为元素太多了，还是无法避免链表变的很长。
这个时候，就需要对数组进行“扩容”，来降低hashCode冲突问题。
这就涉及到一个问题，什么时候扩容？

动态数组的元素是靠左对齐存储，一个arr[index]中只存储一个对象，数组满了自然扩容。
哈希表，它底层的数组元素并 不是靠左对齐，那么不能看数组是否已满来决定是否扩容了，
那么这个就需要用到loadFactor和threshold。
threshold = 数组的长度 * loadFactor；
当我们(key,value)的个数，size ，达到threshold ，就考虑扩容。

真正扩容的条件：①size 达到threshold ②我们新添加的(key,value)不是存在 数组[index]为空的下面，
                            而是存储在数组[index]非空的下面，既我们不扩容，链表会继续变长的情况下

              即如果size达到 threshold  ，但是新添加的(key,value)正好要存储在 数组[index]为空的元素中，暂时不扩容


（3）new HashMap<>()
loadFactor初始化为0.75
threshold初始化为16

（4）put(K key, V value)
第一步：判断是否是第一次添加，如果是，把table初始化一下，长度为16，阈值为12
第二步：判断key是否为null，如果为null，放到table[0]中，如果已经存在另一个key为null，用新的value替换原来的value
第三步：计算key的hash，用hashCode和相关的算法，算出了一个hash值
第四步：计算(key,value)要存储到数组中下标[i]   hash & (table.length-1)
第五步：在table[i]下面是否有重复的key，如果有就用新的value替换原来的value
第六步：把新的(key,value)存储到数组中下标[i]
      在添加到table[i]下面过程中，考虑可能扩容。

    public V put(K key, V value) {
        if (table == EMPTY_TABLE) { //第一次添加时，table是一个空数组
            inflateTable(threshold); //两件事情：（1）table数组重新建了，长度默认为16，如果不是2的n次方，会纠正为2的n次方
                                                 （2）threshold = 数组的长度（16） * loadFactor（0.75）= 12
        }
        if (key == null)  //当key为null，单独处理
            return putForNullKey(value);

        int hash = hash(key); //对key的hashCode做计算，目的是干扰我们的原始的hashCode值，尽量的使得hashCode概率降低
                              //  y = f(x)函数， x是原始的hashCode，y是经过某个算法计算出来的新的hash值。
        int i = indexFor(hash, table.length);  //这个[i]他的计算方式   [i] = hash & (table.length-1)
                            //table.length一定是2的n次方， table.length-1的二进制    左边是全是0，右边全是1
                             //例如：table.length是16， 16-1是15,15的二进制： 24个0 0000 1111
                             //例如：table.length是32， 32-1是31,31的二进制： 24个0 0001 1111
                             //例如：table.length是64， 64-1是63,63的二进制： 24个0 0011 1111
                             //      hash & (table.length-1) 结果的范围[0, table.length-1]范围内

        for (Entry<K,V> e = table[i]; e != null; e = e.next) { //Entry<K,V> e = table[i]取出table[i]下面链表的头结点
                                                                //e!=null，说明table[i]有东西的
            Object k;
            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                                                            // 新添加的（key,value）的key与table[i]下面链表的某个结点的key的hash相等或者是key的地址或equals相同
                                                            //意味着key重复了
                V oldValue = e.value;                       //如果key重复了，用新的value替换原来的value
                e.value = value;
                e.recordAccess(this);
                return oldValue;
            }
        }
                                                //如果上面的for没有满足过if，退出循环是当e == null时退出，
                                                //即把table[i]下面的结点全部都检查过了，key都不重复

        modCount++;
        addEntry(hash, key, value, i);    //要把新的(key,value)添加到table[i]下面
        return null;
    }


   private void inflateTable(int toSize) {
        // Find a power of 2 >= toSize
        int capacity = roundUpToPowerOf2(toSize); //对toSize的值进行判断，如果它不是2的n次方，纠正为 >= toSize的最近的一个2的n次方

        threshold = (int) Math.min(capacity * loadFactor, MAXIMUM_CAPACITY + 1); //阈值在此处更新为 threshold = capacity * loadFactor;
        table = new Entry[capacity];//创建了新数组，长度为capacity
        initHashSeedAsNeeded(capacity);
    }


   void addEntry(int hash, K key, V value, int bucketIndex) {
        if ((size >= threshold) && (null != table[bucketIndex])) { //看是否需要扩容（1）size >= threshold达到阈值
                                                                                    （2）table[bucketIndex]不为空，如果不扩容，链表会变长
            resize(2 * table.length); //把数组扩容为原来的2倍，并且把原来数组中所有键值对重新转移到新的数组中，
                                        //转移的过程不是原样复制，重新计算存储的位置，因为存储的位置 = hash & table.length-1。
            hash = (null != key) ? hash(key) : 0;  //重新计算新的(key,value)的hash值
            bucketIndex = indexFor(hash, table.length);//重新计算新的(key,value)要存储的位置
        }

        createEntry(hash, key, value, bucketIndex);
    }

    void createEntry(int hash, K key, V value, int bucketIndex) {
        Entry<K,V> e = table[bucketIndex];  //e是table[bucketIndex]下面的链表的头结点
        table[bucketIndex] = new Entry<>(hash, key, value, e); //e是原来链表的头结点，现在变为新结点的next
                                                                //说明新结点成了table[bucketIndex]的头结点
        size++;
    }


   private V putForNullKey(V value) {
        for (Entry<K,V> e = table[0]; e != null; e = e.next) { //e是table[0]的头结点，说明key为null时，我们固定把它存储在table[0]下
                                                               //table[0]下面的链表的长度是否可能超过1
                                                               // 或者问 table[0]的键值对是不是一定都是key为null的。
                                                               //不是，table[0]下面包含key为null的键值对，也可能包含 hash& table.length-1正好为0的(key,value)
            if (e.key == null) {                //如果也找到了，另一个键值对的key为null，替换value
                V oldValue = e.value;
                e.value = value;
                e.recordAccess(this);
                return oldValue;
            }
        }
                    //退出for循环，说明没找到另一个键值对， 它的key为null

        modCount++;
        addEntry(0, null, value, 0); //添加新的 键值对，位置在table[0]下面，key为null的hash用0表示
        return null;
    }

（5）get(K key)
    public V get(Object key) {
        if (key == null)            //看是否key为null
            return getForNullKey();
        Entry<K,V> entry = getEntry(key);

        return null == entry ? null : entry.getValue();
    }

   private V getForNullKey() {
        if (size == 0) {  //说明map是空的
            return null;
        }
        for (Entry<K,V> e = table[0]; e != null; e = e.next) { //进入到table[0]下面查找key为null的键值对
            if (e.key == null)
                return e.value;
        }
        return null; //说明没找到，返回null
    }

   final Entry<K,V> getEntry(Object key) {
        if (size == 0) { //说明map是空的
            return null;
        }

        int hash = (key == null) ? 0 : hash(key); //计算它的目的，是为了算[index]
        //这个for循环，在table[index]下面找，index= hash& (table.length-1)，找重复key
        for (Entry<K,V> e = table[indexFor(hash, table.length)];e != null;e = e.next) {
            Object k;
            if (e.hash == hash &&  ((k = e.key) == key || (key != null && key.equals(k))))
                return e; //返回结点Entry对象
        }
        return null;
    }


问题1：HashMap的内部类  Entry，为什么每一个(key,value)存到map中后，要把(key,value)单独用hash存起来？
    static class Entry<K,V> implements Map.Entry<K,V> {
        final K key;
        V value;
        Entry<K,V> next;
        int hash;
        .....
   }
   目的是为了效率。不用每次在比较时，重新计算hash值。

问题2：如果把key修改了，新的hash与已经存好hash会不同，是否会导致找不到原来的(key,value)?
   对，
   一旦把(key,value)存入map，就不要修改key了。


（6）remove(K key)
 */
public class TestHashMap7 {
    public static void main(String[] args) {
        HashMap<String,String> map = new HashMap<>();

//        for(int i=1; i<=20; i++) {
//            map.put("王阳"+i, "翠花");
//        }

        map.get("王阳");

        map.remove("王阳");
    }
}
