package test07;

import java.util.Arrays;
import java.util.Iterator;

/*
MyArrayList类型对象，就是一个容器，它的底层是数组实现的，对外表现为顺序结构。

 */
public class MyArrayList<T> implements Iterable<T>{
//    private T[] arr = new T[5];//错误，编译不通过，T类型不确定
    private Object[] arr = new Object[5];//数组的初始长度为5
    private int size;//记录arr数组中实际存储了几个元素

    //添加到数组的已有元素的后面
    public void add(T element){
        /*if(size >= arr.length){//判断arr数组是否已满
            //扩容
            arr = Arrays.copyOf(arr, arr.length+(arr.length>>1));//扩容为1.5倍
        }*/
        grow();

//        arr[下标] = element;
        arr[size++] = element;//这里是size++, ++放后面，++后的值是下次添加时用的下标
    }

    public void add(int index, T element){
        //(1)检查index合法性
        //合法的index范围：[0, size]
        if(index<0 || index>size){
            throw new IndexOutOfBoundsException(index+"越界");
        }

        //(2)判断arr是否已满
        /*if(size >= arr.length){//判断arr数组是否已满
            //扩容
            arr = Arrays.copyOf(arr, arr.length+(arr.length>>1));//扩容为1.5倍
        }*/
        grow();

        //(3)把[index]及其后面的元素右移
        //计算一共要移动几个元素
        /*
        假设：size = 4,  index = 0， 移动[0][1][2][3]四个
        count = size-index=4;

        假设：size = 4,  index = 4， 移动0个
        count = size-index=0;
         */
        int count = size-index;
        if(count > 0) {
            System.arraycopy(arr, index, arr, index + 1, count);
        }

        //(4)把新元素放到[index]
        arr[index] = element;

        //(5)元素个数增加
        size++;
    }

    private void grow(){
        if(size >= arr.length){//判断arr数组是否已满
            //扩容
            arr = Arrays.copyOf(arr, arr.length+(arr.length>>1));//扩容为1.5倍
        }
    }

    //删除[index]位置的元素
    public void remove(int index){
        //(1)检查index合法性:[0,size-1]
 /*       if(index<0 || index>=size){
            throw new IndexOutOfBoundsException(index+"越界");
        }*/
        checkExistIndex(index);

        //(2)把[index]后面的元素往左移动
        //计算要移动的元素的个数
        /*
        假设：size = 4, index = 0，移动[1][2][3]三个
        count = size - index - 1;

        size = 4, index = 3，移动0个
        count = size - index - 1;
         */
        int count = size - index - 1;
        if(count > 0){
            System.arraycopy(arr, index+1, arr, index, count);
        }

        //(3)把最后一个位置还原为null
        //最后一个元素的位置是[size-1]
//        arr[size-1] = null;//让GC回收垃圾对象
//
//        //(4)元素个数减少
//        size--;

        arr[--size] = null;
    }

    //在当前集合中删除obj对象，假设只删除找到第一个
    public void remove(Object obj){
        int index = indexOf(obj);
        if(index == -1){
            return;//结束删除
        }
        //如果index不是-1，正常删除
        remove(index);
    }

    //在当前集合中查找obj对象，假设只查找第一次出现
    public int indexOf(Object obj){
        //遍历什么范围：[0,size-1]
        if(obj == null){
            for (int i=0; i<size; i++){
                if(arr[i] == obj){//null == null满足条件
                    return i;
                }
            }
        }else{
            for (int i=0; i<size; i++){
                if(obj.equals(arr[i])){//执行的是元素对象的运行时类型的equals方法
                    return i;
                }
            }
        }
        //如果没找到的话，返回-1
        return -1;
    }

    public int lastIndexOf(Object obj){
        //遍历什么范围：[0,size-1]
        if(obj == null){
            for (int i=size-1; i>=0; i--){
                if(arr[i] == obj){//null == null满足条件
                    return i;
                }
            }
        }else{
            for (int i=size-1; i>=0; i--){
                if(obj.equals(arr[i])){//执行的是元素对象的运行时类型的equals方法
                    return i;
                }
            }
        }
        //如果没找到的话，返回-1
        return -1;
    }

    public int size(){
        return size;
    }

    public boolean contains(Object obj){
        return indexOf(obj) != -1;
    }

    //返回[index]位置的元素
    public T get(int index){
        //检查[index]合法性
        checkExistIndex(index);

        return (T) arr[index];//这里强制安全吗？
                            //因为add方法，添加的一定是T类型的对象
    }

    public void set(int index, Object newElement){
        //检查index的合法性
        //范围：[0,size-1]
 /*       if(index<0 || index>=size){
            throw new IndexOutOfBoundsException(index+"越界");
        }*/
        checkExistIndex(index);

        //替换[index]位置的元素为newElement
        arr[index] = newElement;
    }

    private void checkExistIndex(int index){
        if(index<0 || index>=size){
            throw new IndexOutOfBoundsException(index+"越界");
        }
    }

    //遍历
    public Object[] toArray(){
        //复制了arr数组中size个元素，构成了一个新数组返回
        return Arrays.copyOf(arr, size);
    }


    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    //声明一个内部类，实现Iterator<T>
    private class Itr implements Iterator<T>{
        private int cursor;//游标，默认值是0

        @Override
        public boolean hasNext() {
            //arr数组中有效元素的范围[0,size-1]
            return cursor < size;
        }

        @Override
        public T next() {
            return (T) arr[cursor++];
        }
    }
}
