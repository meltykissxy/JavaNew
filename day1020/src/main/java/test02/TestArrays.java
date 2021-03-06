package com.atguigu.test02;

import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import java.text.Collator;
import java.util.Arrays;
import java.util.Comparator;

/*
涉及的包：
1、java.lang：Java最最核心的语言包
  Object,Math，Thread，Runnable，Comparable，String,System，Runtime
  异常类型等，@Override，@SuppressWarnings，  @Deprecated，包装类，枚举的父类Enum，Cloneable

2、java.math：
BigDecimal
BigInteger

3、java.util：公共工具类
Comparator，Scanner，Date，Calendar,Random

4、java.text：文本处理相关
SimpleDateFormat

5、java.time及其子包：Java8新增的第三代日期时间
...




7、数组的工具类：java.util.Arrays
此类包含用来操作数组（比如排序和搜索）的各种方法。
除非特别注明，否则如果指定数组引用为 null，则此类中的方法都会抛出 NullPointerException。
（1）public static int binarySearch(int[] a, int key)：对a数组进行二分查找，查找key是否存在
    如果存在返回它的下标，如果不存在返回负数（负数是-插入点-1，插入点是指如果它存在，在当前数组中它的下标是几）
    特别说明：使用二分查找的数组必须是有序的
   ...支持各种类型的数组

public static int binarySearch(Object[] a, Object key)
public static int binarySearch(Object[] a,Object key, Comparator c)
    如果是对象数组，要么对象的类型支持Comparable接口，或者指定Comparator接口的实现类对象

（2）public static int[] copyOf(int[] original, int newLength)：复制一个新数组，新数组的长度为newLength
 ...支持各种类型的数组
（3）public static int[] copyOfRange(int[] original,int from,int to)：复制一个新数组，新数组是从旧数组[from, to)
 ...支持各种类型的数组
（4）public static boolean equals(int[] a,  int[] a2)：比较两个数组，他们的长度和元素是否相同
 ...支持各种类型的数组
（5）public static void fill(int[] a, int val)：用val填充整个a数组
 ...支持各种类型的数组
（6）public static void sort(int[] a)：排序
 ...支持各种类型的数组
 public static void sort(Object[] a)：对对象数组的元素有要求，必须实现Comparable接口
 public static void sort(Object[] a,Comparator c)：或者指定定制比较器
 （7）public static String toString(int[] a)：返回指定数组内容的字符串表示形式。字符串表示形式由数组的元素列表组成，括在方括号（"[]"）中。相邻元素用字符 ", "（逗号加空格）分隔。
 ...支持各种类型的数组

 8、java.text包
 Collator 类执行区分语言环境的 String 比较。
 Collator是抽象的。要么用它的子类创建对象，要么调用static的getInstance方法获取

 */
public class TestArrays {
    @Test
    public void test09(){
        int[] arr = {4,16,19,110,56};
        System.out.println(Arrays.toString(arr));//[4, 16, 19, 110, 56]
    }

    @Test
    public void test08() {
        Student[] arr = new Student[4];
        arr[0] = new Student("张三", 23);
        arr[1] = new Student("王五", 25);
        arr[2] = new Student("李四", 24);
        arr[3] = new Student("柴林燕", 18);

        Arrays.sort(arr, new Comparator() {

            @Override
            public int compare(Object o1, Object o2) {
                Student s1 = (Student) o1;
                Student s2 = (Student) o2;
                return Collator.getInstance().compare(s1.getName(),s2.getName());
            }
        });

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    @Test
    public void test07() {
        Student[] arr = new Student[3];
        arr[0] = new Student("张三", 23);
        arr[1] = new Student("王五", 25);
        arr[2] = new Student("李四", 24);

//        Arrays.sort(arr);//ClassCastException: com.atguigu.test02.Student cannot be cast to java.lang.Comparable
        Arrays.sort(arr, new Comparator(){

            @Override
            public int compare(Object o1, Object o2) {
                Student s1 = (Student) o1;
                Student s2 = (Student) o2;
                return s1.getAge() - s2.getAge();
            }
        });

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    @Test
    public void test06(){
        int[] arr = {4,16,19,110,56};
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    @Test
    public void test05() {
        int[] arr1 = {1, 2, 3, 4, 5, 6};
        int[] arr2 = {1, 2, 3, 4, 5};
        System.out.println(Arrays.equals(arr1, arr2));
    }

    @Test
    public void test04() {
        int[] arr = {1, 2, 3, 4, 5, 6};
        int[] ints = Arrays.copyOfRange(arr, 2, 5);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }

    @Test
    public void test03(){
        int[] arr = {1,2,3,4,5,6};

        int[] newArr1 = Arrays.copyOf(arr, 10);
        for (int i = 0; i < newArr1.length; i++) {
            System.out.println(newArr1[i]);
        }
    }

    @Test
    public void test02(){
        Student[] arr = new Student[3];
        arr[0] = new Student("张三",23);
        arr[1] = new Student("李四",24);
        arr[2] = new Student("王五",25);

        Student student = new Student("王五",25);
//        System.out.println(Arrays.binarySearch(arr, student));//ClassCastException:Student cannot be cast to Comparable
        /*
        要求：对象数组的元素必须支持比较大小
        因为在二分查找过程中，涉及到比较大小，arr[mid] 与value 比较，如果arr[mid]比value大/小，往左/右查找
         */

        System.out.println(Arrays.binarySearch(arr, student, new Comparator(){

            @Override
            public int compare(Object o1, Object o2) {
                Student s1 = (Student) o1;
                Student s2 = (Student) o2;
                return s1.getAge() - s2.getAge();
            }
        }));
    }

    @Test
    public void test(){
        int[] arr = {4,6,9,11,56};
        int i = Arrays.binarySearch(arr, 5);
        System.out.println(i);//-2     5如果存在，在当前数组中它的下标应该是1，-1-1=-2

        System.out.println(Arrays.binarySearch(arr, 11));//3
    }
}
class Student{
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}