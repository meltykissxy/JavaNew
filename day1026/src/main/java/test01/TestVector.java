package test01;

import java.util.Vector;

/*
一、JDK中的Vector的底层源码（部分）
变量：
elementData：Object数组，存储元素用的
capacityIncrement：容量的增量
elementCount：实际数组中存储的元素的个数

（1）new Vector<>();
elementData数组给创建出来了，长度为10，编译期间是Object类型；
capacityIncrement初始化为0

（2）add(E e)
步骤：
先判断当前数组的容量是否足够，不够就扩容，
        扩容：默认扩容为原来的2倍，如果你手动指定了capacityIncrement的值，可以按照   原来容量 + capacityIncrement的扩容方式。
            如果扩容后还不够，就按照你实际需要的尽量满足你。
把新元素放到  elementData[ elementCount++] = e;

（3）add(int index, E e)
步骤：
第一步：检查index
第二步：判断是否要扩容，如果需要就扩容
第三步：把[index]及其后面的元素往右移动，腾出[index]位置
第四步：elementData[index] = obj;
第五步：elementCount++;

（4）remove(int index)
步骤：
第一步：检查index
第二步：先用oldValue存储[index]原来的元素，即即将要被删除的元素
第三步：计算，我删除[index]位置，需要把[index]后面几个元素往前移动
第四步：如果要移动的元素个数>0，就调用System.arraycopy方法移动元素
第五步：elementData[--elementCount] = null;
第六步：返回刚刚被删除的元素的对象

（5）remove(Object obj)
步骤：
第一步：查找obj在当前集合中的下标
第二步：如果index>=0，即存在，删除[index]位置的元素
    ①检查index
    ②计算，我删除[index]位置，需要把[index]后面几个元素往前移动
    ③如果要移动的元素个数>0，就调用System.arraycopy方法移动元素
    ④--elementCount
    ⑤elementData[elementCount]null
    删除成功返回true
第三步：如果刚才index<0，返回false，即删除失败
 */
public class TestVector {
    public static void main(String[] args) {
        //跟踪源码的快捷键：按住ctrl键，鼠标单击对应的类型或方法
        Vector<String> vector = new Vector<>();

        vector.add("hello");

        vector.add(1, "java");

        vector.remove(0);

        vector.remove("java");
    }
}
