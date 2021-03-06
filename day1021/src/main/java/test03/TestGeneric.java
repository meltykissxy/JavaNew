package test03;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;

/*
第十一章  泛型
一、为什么要使用泛型？
1、生活中：
    中药店，大药柜，有很多抽屉，每个抽屉上面都有标签。
    如果没有标签，抽屉里可能放各种药，拿的时候，药师反复确认。效率比较低。
                    甚至会出现拿错，放错。
     ==>不安全，麻烦

2、Java中：
    不仅限于容器，这里拿容器说明。
    自定义一个容器，不是数组，容器是用来装对象。
    如果容器没有限定“元素”的类型，那么说明，这个容器，可以装任意类型对象。
    但是拿的时候，就麻烦了，需要检查对象的类型。而且如果没有检查会出现类型转换异常等问题。

    ==>不安全，麻烦

3、如何解决呢？

   学习方法时：
       方法签名：【修饰符】 返回值类型   方法名(【形参列表】)【throws 异常列表】{}
       其中的【形参列表】什么情况下需要？
       在完成{}中的功能时，发现需要调用者给我传一些未知、不确定的数据，我们使用形参。

   上面的容器等问题，是因为我们在设计这个容器时，我们不确定将来这个容器用来装什么类型的对象。
   那么我们能不能和方法的形参一样，在设计时用一个形参名代替，调用时，实参给形参赋值。
   我们在设计容器时，对象的类型，用一个“泛型形参/类型变量，例如：T”代表，在使用这个容器时，给T确定具体的类型。

4、泛型的好处：
（1）安全：在编译期间就可以实现类型检查
（2）方便：使用时不需要强制类型转换

 */
public class TestGeneric {

    @Test
    public void test03(){
        RongQi<String> c = new RongQi<String>();
        c.add("hello");
        c.add("world");
//        c.add(1);//错误，在编译期间就可以类型检查，避免不符合要求对象加入

        System.out.println(c.get(0).length());
        System.out.println(c.get(1).length());

    }
    @Test
    public void test02(){
        //我希望我的c容器对象中只装字符串
        Container c = new Container();
        c.add("hello");
        c.add("world");
        c.add(1);//编译期间不会报错

        System.out.println(((String)c.get(0)).length());
        System.out.println(((String)c.get(1)).length());
        System.out.println(((String)c.get(2)).length());//运行报错
    }

    @Test
    public void test01(){
        Container c = new Container();
        c.add("hello");
        c.add(1);//int  还是  Integer
        c.add(new StringBuffer());
        c.add(LocalDate.now());

        //从上面来看，任意类型的对象都可以往里放

        //取一个对象
        Object o = c.get(0);
        //求字符串的长度
        if(o instanceof String) {
            String str = (String) o;//强制类型转换
            System.out.println(str.length());
        }

    }
}
//自定义容器
class Container{
    private Object[] arr = new Object[5];//暂时底层使用数组结构来存储
    private int total;//记录容器中实际存储的对象的个数

    //形参是Object，表示可以接收任意类型的对象
    public void add(Object obj){
        if(total >= arr.length){
            //要么报错
            //要么扩容
            arr = Arrays.copyOf(arr, arr.length*2);//这里简单处理，扩容为2倍
        }
        arr[total++] = obj;
    }

    public Object get(int index){
        if(index < 0 || index >= total){
            throw new IndexOutOfBoundsException(index +"越界");
        }
        return arr[index];
    }

    //...
}

class RongQi<T>{
    private Object[] arr = new Object[5];//暂时底层使用数组结构来存储
    private int total;//记录容器中实际存储的对象的个数

    //形参是Object，表示可以接收任意类型的对象
    public void add(T obj){
        if(total >= arr.length){
            //要么报错
            //要么扩容
            arr = Arrays.copyOf(arr, arr.length*2);//这里简单处理，扩容为2倍
        }
        arr[total++] = obj;
    }

    public T get(int index){
        if(index < 0 || index >= total){
            throw new IndexOutOfBoundsException(index +"越界");
        }
        return (T) arr[index];//编译时数组元素类型是Object类型，但是实际对象是T类型
                                //因为add方法，放进去一定是T类型的对象。
    }

    //...
}