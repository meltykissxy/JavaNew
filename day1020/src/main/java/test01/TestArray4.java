package com.atguigu.test01;

/*
4、数组元素的删除

 */
public class TestArray4 {
    public static void main(String[] args) {
        String[] arr = {"hello","java","world","atguigu"};
        remove(arr, 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    //方法：实现在arr数组中删除[index]位置的元素
    public static void remove(Object[] arr, int index){
        if(arr == null) return;
        if(index <0 || index >= arr.length){
            throw new IndexOutOfBoundsException(index + "下标越界");
        }
        //(1)把[index+1]及其后面的元素往左移动
        System.arraycopy(arr, index+1, arr, index, arr.length-index-1);
        //(2)把最后一个元素还原为默认值
        arr[arr.length-1] = null;
    }

}
