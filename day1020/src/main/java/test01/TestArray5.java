package com.atguigu.test01;

import org.junit.Test;

import javax.swing.*;

/*
5、数组的二分查找
之前学过顺序查找，思路：从头到尾挨个的判断是否是符号查找目标
        最好：第一个就命中
        最坏：最后一个都没有找到        查找了arr.length次
        对数组没有要求

今天学习二分查找：思路：每一次判断，都可以排除剩下的一半
        最好： 第一个就命中
        最坏： 最后一个都没有找到       查找了 logn
        对数组有要求，数组必须有序的


 */
public class TestArray5 {
    public static void main(String[] args) {
        int[] arr = {3, 8, 20, 25, 35, 40, 52, 60, 74, 82};

//        int value = 5;
        int value = 82;
        int left = 0;
        int right = arr.length - 1;
        int mid = (left + right) / 2;
        int index = -1;

        while (left <= right) {//left>right结束
            if (arr[mid] == value) {
                index = mid;
                break;
            } else if (arr[mid] > value) {
                //往左边
                right = mid - 1;
            } else {
                //往右边
                left = mid + 1;
            }
            mid = (left + right) / 2;
        }

        if (index == -1) {
            System.out.println("不存在");
        } else {
            System.out.println(value + "在[" + index + "]位置");
        }
    }

    @Test
    public  void other() {
        int[] arr = {3, 8, 20, 25, 35, 40, 52, 60, 74, 82};
        int index = -1;
        int value = 52;
        for (int left = 0, right = arr.length - 1; left <= right; ) {
            int mid = (left + right) / 2;
            if (arr[mid] == value) {
                index = mid;
                break;
            } else if (arr[mid] > value) {
                //往左边
                right = mid - 1;
            } else {
                //往右边
                left = mid + 1;
            }
        }
        if (index == -1) {
            System.out.println("不存在");
        } else {
            System.out.println(value + "在[" + index + "]位置");
        }
    }
}