package test02;

import org.junit.Test;

import java.io.*;

/*
八、对象IO流：用来读/写对象
昨天学习的DataInputStream和DataOutputStream的IO流只支持：基本数据类型和String，
但是我们Java中数据基本上是存储在一个一个的“对象”中。

从JDK1.1之后，就引入了对象IO流，直接操作对象。

1、对象IO流的类型：
ObjectInputStream：用来读取对象和基本数据类型等
        反序列化：把字节序列重新构建为Java对象
ObjectOutputStream：用来输出对象和基本数据类型等，序列化
        序列化(Serialize)：把对象转为字节序列然后输出

2、如何使用它们
要求：
（1）凡是要序列化的对象，它的类型必须实现一个接口：java.io.Serializable接口
否则在序列化过程中，就会报NotSerializableException。

Serializable接口也是一个标识型接口，如何序列化它，不用我们程序员自己重写方法来实现，
而是由JVM底层帮我们处理好了，虽然不用重写抽象方法，但是我们必须实现这个接口。
 */
public class TestObjectIO {
    @Test
    public void test3() throws IOException {
        //需求：我有一个Student对象，需要持久化（永久保存），即把对象输出到文件中
        Student student = new Student(1,"张三",89,new Teacher());

        //(1)选择IO流：FileOutputStream     排除FileWriter（因为是整个对象处理，不是纯文本）
        FileOutputStream fos = new FileOutputStream("d:/stu.dat");
        //光有FileOutputStream不够的，需要对象IO流，把对象转为字节
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        //（2）调用写的方法
        oos.writeObject(student);
        /*
        java.io.NotSerializableException: com.atguigu.test02.Student ==> Student不支持序列化
        java.io.NotSerializableException: com.atguigu.test02.Teacher ==> Teacher不支持序列化
         */

        //(3)关闭IO流
        oos.close();
        fos.close();
    }

    @Test
    public void test2() throws IOException, ClassNotFoundException {
        //需求：读取d:/stu.dat文件，把里面的数据重构成一个Java对象
        //(1)选择IO流：FileInputStream   排除FileReader因为是整个对象处理，不是纯文本）
        FileInputStream fis = new FileInputStream("d:/stu.dat");
        //光有FileInputStream不够，需要对象IO流，
        ObjectInputStream ois = new ObjectInputStream(fis);

        //(2)调用read
        Object obj = ois.readObject();
        System.out.println(obj);

        //（3）关闭
        ois.close();
        fis.close();
    }

    @Test
    public void test() throws IOException {
        //需求：我有一个Student对象，需要持久化（永久保存），即把对象输出到文件中
        Student student = new Student(1,"张三",89);

        //(1)选择IO流：FileOutputStream     排除FileWriter（因为是整个对象处理，不是纯文本）
        FileOutputStream fos = new FileOutputStream("d:/stu.dat");
        //光有FileOutputStream不够的，需要对象IO流，把对象转为字节
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        //（2）调用写的方法
        oos.writeObject(student);
        /*
        java.io.NotSerializableException: com.atguigu.test02.Student ==> Student不支持序列化
         */

        //(3)关闭IO流
        oos.close();
        fos.close();
    }
}
