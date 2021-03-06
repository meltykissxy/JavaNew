package com.atguigu.test01;

/*
复习：关于数组
1、声明数组
一维数组：元素的数据类型[] 数组名;
2、初始化
一维数组的静态初始化：  数组名 = new 元素的数据类型[]{元素列表};
                      元素的数据类型[] 数组名= {元素列表};
 一维数组的动态初始化：  数组名 = new 元素的数据类型[长度];
                       数组名[下标] = 值;
3、遍历
普通for循环： for(int i=0; i<数组名.length; i++){
                    数组名[i]表示元素
            }
4、算法
（1）统计数组元素的情况
找偶数的个数、素数的个数、总和、平均值
遍历数组，对元素继续判断和计算即可

（2）找数组中元素的最大/最小值
A：假设数组的第一个元素   最大/最小
B：遍历数组剩下的元素，和max/min进行比较，如果有比max大/比min小的，就修改max或min的值

（3） 找数组中元素的最大/最小值及其下标
A：假设数组的第一个元素   最大/最小
    用max/min表示最大/最小值， 用index表示最大/小值的下标
B：遍历数组剩下的元素，和max/min进行比较，如果有比max大/比min小的，就修改max或min的值
                                        同时修改index的值
（4）查找
顺序查找
A：先假设index=-1
B：遍历数组，查找某个值是否存在，存在修改index=它的下标;
C：在循环外面判断index的值是否是-1，如果是-1，说明没找到

（5）冒泡
for(int i=1; i<数组名.length; i++){
    for(int j=0; j<数组名.length-i; j++){
        if(arr[j] 与 arr[j+1] 比较){
            //交换arr[j] 与 arr[j+1]
            元素的类型  temp = arr[j];
            arr[j] = arr[j+1];
            arr[j+1] = temp;
        }
    }
}

四、数组的算法扩展
1、数组元素的反转
（1）整个数组的反转
（2）数组的部分元素反转

思路：
（1）首尾对应位置交换
（2）借助一个新数组
 */
public class TestArray {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
//        reverse(arr);
        arr = reverse2(arr);

        System.out.println("反转之后：");
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    //声明一个方法，可以反转整个数组中的元素
    public static void reverse(int[] arr){
        if(arr == null){
            return;
        }
        /*
        思路：首尾对应位置的元素交换
        （1）确定交换几次
           次数 = 数组.length / 2
        （2）谁和谁交换
        for(int i=0; i<次数; i++){
             int temp = arr[i];
             arr[i] = arr[arr.length-1-i];
             arr[arr.length-1-i] = temp;
        }
         */
        /*for(int i=0; i<arr.length/2; i++){
            int temp = arr[i];
            arr[i] = arr[arr.length-1-i];
            arr[arr.length-1-i] = temp;
        }*/
        for(int left = 0,right = arr.length-1; left<right; left++,right--){
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
        }

    }

    public static int[] reverse2(int[] arr){
        if(arr == null) return null;
        /*
        思路：新建一个数组
        （1）新建数组，长度和类型和原来数组一样
        （2）把旧数组的元素  逆序 赋值到新数组中
        （3）新数组就是我们最后要的结果
         */
        int[] newArr = new int[arr.length];
        for (int i = 0; i < newArr.length; i++) {
            newArr[i] = arr[arr.length-1-i];
        }
        return newArr;
    }

    //反转数组的一部分，反转arr数组[start, end) Java中习惯传下标和范围，左闭右开
    public static void reverse(int[] arr,int start, int end){

        /*
        思路：在[start, end-1]范围内首尾交换
        （1）确定次数
           次数 = (end-start)/2
        （2）谁和谁交换
           for(int i=0; i<次数; i++){
               int temp = arr[start + i];
              arr[start+i] = arr[end-1-i];
             arr[end-1-i] = temp;
        }

         */
        for(int i=0; i<(end-start)/2; i++){
            int temp = arr[start + i];
            arr[start+i] = arr[end-1-i];
            arr[end-1-i] = temp;
        }
    }
}
