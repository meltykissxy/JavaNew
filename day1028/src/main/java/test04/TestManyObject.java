package test04;

import org.junit.jupiter.api.Test;
import test02.Student;

import java.io.*;
import java.util.ArrayList;

/*
学生的问题：
    如果有多个对象，需要序列化，怎么办呢？
    一般是把他们放到一个数组或集合中，然后再序列化
 */
public class TestManyObject {
    @Test
    public void test02()throws IOException ,ClassNotFoundException{
        //反序列化
        FileInputStream fis = new FileInputStream("manyStu.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);

        Object o = ois.readObject();
        ArrayList<Student> list = (ArrayList<Student>) o;
        System.out.println(list);

        ois.close();
        fis.close();
    }

    @Test
    public void test01()throws IOException {
        Student s1 = new Student(1,"张三",89);
        Student s2 = new Student(2,"李四",55);
        Student s3 = new Student(3,"王五",78);

        ArrayList<Student> list = new ArrayList<>();
        list.add(s1);
        list.add(s2);
        list.add(s3);

        //序列化list
        FileOutputStream fos = new FileOutputStream("manyStu.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(list);

        oos.close();
        fos.close();
    }
}
