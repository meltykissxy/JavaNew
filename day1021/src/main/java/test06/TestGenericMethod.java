package test06;

import org.junit.Test;

import java.util.Arrays;

/*
四、泛型方法
1、什么是泛型方法呢？
在声明方法的时候，在返回值类型前面声明了一个泛型
【修饰符】 <泛型/类型变量> 返回值类型  方法名(【形参列表】)【throws 异常列表】{}

2、为什么需要泛型方法？或者说，什么情况下需要泛型方法？
（1）如果某个方法是静态方法，并且它的形参等类型不确定，此时也需要使用泛型，只能在该方法前面声明泛型
（2）如果某个非静态方法，它的形参等类型不确定，此时该方法所在的类不是泛型类，或者说该方法的形参与类上面声明的泛型<T>无关，
此时也需要在该方法前面声明泛型

3、在方法上面声明的泛型，是否可以指定上限呢？
可以

【修饰符】 <泛型/类型变量 extends 上限> 返回值类型  方法名(【形参列表】)【throws 异常列表】{}
【修饰符】 <泛型/类型变量 extends 上限1 & 上限2 ...> 返回值类型  方法名(【形参列表】)【throws 异常列表】{}
 */
public class TestGenericMethod {
    @Test
    public void test01(){
        String[] strings = {"hello","haha","hi","heihei"};

        MyArrays.sort(strings);

        System.out.println(Arrays.toString(strings));
    }

    @Test
    public void test02(){
        String[] strings = {"hello","haha","hi","heihei"};
        MyArrays.sort2(strings);//String类型实现了Comparable接口
        System.out.println(Arrays.toString(strings));
    }

    @Test
    public void test03(){
        Number[] numbers = {1,5.8,5};
//        MyArrays.sort2(numbers);//Number类型没有实现Comparable接口，编译期间就避免错误
        System.out.println(Arrays.toString(numbers));
    }
}

class MyArrays{
    //方法，该方法表示，可以为任意对象数组进行排序
    public static <T> void sort(T[] arr){
        //直接选择排序
        for (int i=0; i<arr.length-1; i++){
            //找本轮的最小值
            int minIndex = i;
            T min = arr[i];
            for(int j=i+1; j<arr.length; j++){
                //该句代码运行要通过，arr[j]的元素实际类型（运行时类型）实现Comparable
                Comparable element = (Comparable) arr[j];
                if(element.compareTo(min)<0){
                    min = arr[j];
                    minIndex = j;
                }
            }

            //判断minIndex是否还在i的位置
            if(minIndex != i){
                T temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }

    //给T指定上限
    public static <T extends Comparable> void sort2(T[] arr){
        //直接选择排序
        for (int i=0; i<arr.length-1; i++){
            //找本轮的最小值
            int minIndex = i;
            T min = arr[i];
            for(int j=i+1; j<arr.length; j++){
                if(arr[j].compareTo(min)<0){//T类型一定是实现了Comparable接口
                    min = arr[j];
                    minIndex = j;
                }
            }

            //判断minIndex是否还在i的位置
            if(minIndex != i){
                T temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }
}

class OtherArrays<T>{
    private T info;

    public <T> void sort(T[] arr){//此时该方法的<T>和类上面声明的T无关。
        //直接选择排序
        for (int i=0; i<arr.length-1; i++){
            //找本轮的最小值
            int minIndex = i;
            T min = arr[i];
            for(int j=i+1; j<arr.length; j++){
                //该句代码运行要通过，arr[j]的元素实际类型（运行时类型）实现Comparable
                Comparable element = (Comparable) arr[j];
                if(element.compareTo(min)<0){
                    min = arr[j];
                    minIndex = j;
                }
            }

            //判断minIndex是否还在i的位置
            if(minIndex != i){
                T temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }
}