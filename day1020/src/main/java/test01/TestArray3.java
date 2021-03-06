package com.atguigu.test01;

/*
3、数组元素的插入
（1）如果原来的数组已满的话，先对原来的数组扩容，
（2）把插入位置index及其右边的元素右移
（3）把新元素插入到index位置
 */
public class TestArray3 {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        arr = insertElement(arr, 2, 10);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    //方法，可以在arr数组中插入一个新的元素，新元素的位置是index，新元素的值是value
    //arr已满的情况
    public static int[] insertElement(int[] arr, int index, int value){
        //(1)记录一下原来数组的长度
        int oldLength = arr.length;
        //(1)先扩容
        arr = grow(arr);//对arr进行了扩容，长度扩容为原来的1.5倍
        //(2)把[index]及其右边的元素往右移动
        System.arraycopy(arr, index, arr, index+1, oldLength-index);
        //(3)把value放到arr[index]
        arr[index] = value;
        //(4)返回arr
        return arr;
    }

    public static int[] grow(int[] arr){
        //(1)创建新数组，长度是原来的1.5倍
        //（2）把旧数组的元素复制到新数组中
        //(3)返回新数组
//        int[] newArr = new int[arr.length*1.5];//因为1.5是double，数组的长度是int
//        int[] newArr = new int[(int)(arr.length*1.5)];
        int[] newArr = new int[arr.length + (arr.length>>1)];//(arr.length>>1)必须加()因为有运算符优先级问题
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        return newArr;
    }
}
