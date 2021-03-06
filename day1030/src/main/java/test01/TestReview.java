package test01;

import org.junit.jupiter.api.Test;

import java.io.*;

/*
补充：java.io.Externalizable接口（了解）
作用，让程序员来确定哪些成员变量需要序列化，以及顺序等。不受static,transient等约束。

步骤：
（1）要序列化的对象的类型，实现java.io.Externalizable接口
（2）重写两个抽象方法
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        //这里面的代码，是用于把xxx成员变量的值，写到out流中
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            //这里面的代码，是用于从in流中读取数据，为成员变量赋值
    }
（3）使用ObjectOutputStream进行序列化和ObjectInputStream进行反序列化

Java的io包下的各种IO流类的设计，使用了一个“装饰者设计模式”。
装饰：在xx基础上增加新的xx功能。
     衣服穿完之后，我又在胸前别了别针。这个别针只是增加了“美化”功能，并没有改变衣服本身的功能。
     对象流、缓冲流、转换流等都是用来给其他IO流加装饰/辅助功能用的，并没有修改被装饰流的作用。
     例如：new ObjectOutputStream(fos); 只是在fos流基础上增加序列化功能，并没有修改fos用来输出xxx数据到xx文件的基本功能。

 注意：
 （1）仍然需要给实现了Externalizable接口的类加序列化版本ID
 （2）实现了Externalizable接口的类必须有一个无参构造
 */
public class TestReview {
    @Test
    public void test2() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("stu.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);

        Object object = ois.readObject();
        System.out.println(object);

        ois.close();
        fis.close();
    }

    @Test
    public void test() throws IOException {
        Student student = new Student(1,"张三",88);

        FileOutputStream fos = new FileOutputStream("stu.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);//oos包装了fos，在fos的基础上增加序列化功能

        oos.writeObject(student);

        oos.close();
        fos.close();
    }
}

class Student implements Externalizable {
    private int id;
    private String name;
    private int score;
    private static final long serialVersionUID = -4041202322135414406L;

    public Student() {
    }

    public Student(int id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(name);//把name成员变量写到out流中
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = in.readUTF();//从in的流中读取一个字符串为name成员变量赋值
    }
}