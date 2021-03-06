package com.atguigu.test01;

/*
2、数组的扩容
因为的长度一旦确定，是无法修改的。
但是实际开发中，一开始创建的数组不够存储新增的元素，这个时候就需要对数组进行扩容。
 */
public class TestArray2 {
    public static void main(String[] args) {
        //需求：新增1个元素6，存储在arr数组的最后
        int[] arr = {1,2,3,4,5};

        /*
        思路：
        （1）创建一个新数组，长度为6
        （2）把旧数组的元素复制到新数组中
        （3）把6存储到新数组的最后
        （4）让arr指向新数组
         */
        int[] newArr = new int[arr.length+1];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        newArr[newArr.length-1] = 6;

        arr = newArr;

        //遍历arr
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

        System.out.println("==========================");
        int[] old = {1,2,3,4,5};
        old = grow(old);
        System.out.println("长度" + old.length);
        for (int i = 0; i < old.length; i++) {
            System.out.println(old[i]);
        }

    }

    //设计一个方法，可以实现对数组的扩容，新数组的长度是旧数组的1.5倍
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
