package com.atguigu.test01;

/*
6、数组的直接选择排序
之前讲过：冒泡排序
今天新增一种排序：直接选择排序
    思路一：
        每一次确定一个位置的元素
        第一轮：确定[0]位置的元素，用剩下的元素[0]元素比较，如果有比它小的就交换
        第二轮：确定[1]位置的元素，用剩下的元素[1]元素比较，如果有比它小的就交换
        ...
    思路二：
        每一次确定一个位置的元素
        每一轮：先找出本轮中未排序元素中最小值及其位置，
                如果它不在正确的位置，就用它与它本来应该在的位置元素交换
 */
public class TestArray6 {
    public static void main(String[] args) {
        int[] arr = {3,6,1,8,2};

        //思路二
        for (int i=0; i<arr.length-1; i++){//和冒泡排序一样，轮数 = 长度-1
            //(1)找出本轮最小值及其下标
            int min = arr[i];//假设本轮最小值是arr[i]位置
            int index = i;
            //用min与[i+1, arr.length-1]范围查找最小值
            for(int j=i+1; j<arr.length; j++){
                if(min > arr[j]){
                    min = arr[j];
                    index = j;
                }
            }

            //（2)如果min不在[i]位置就交换
            if(index != i){
                //交换arr[i] 与 arr[index]
                int temp = arr[i];
                arr[i] = arr[index];
                arr[index] = temp;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
