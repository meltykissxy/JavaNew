package test03;

import org.junit.Test;

import java.io.*;

/*
2、如何使用它们
要求：
（1）凡是要序列化的对象，它的类型必须实现一个接口：java.io.Serializable接口
否则在序列化过程中，就会报NotSerializableException。

（2）在实现java.io.Serializable接口，还得给这个类，固定一个“序列化版本ID”serialVersionUID
private static final long serialVersionUID = 3016115315876162045L; //具体的值，可以自己随便写

（3）某些成员变量是不序列化的
A：static修饰的静态变量是不序列化的。
    因为我们序列化是针对“对象”的，而静态变量它不是属于对象的，是属于类的，是所有对象共享的。
B：transient修饰的成员变量是不参与序列化
  transient：瞬时的，临时的，不需要永久保存
 */
public class TestNotSerialize {
    @Test
    public void test03() throws IOException {
        Account account = new Account("1111",10000, 50);
        Account.setRate(0.035);

        //要序列化account对象
        //相对路径，相当于当前模块，因为我是JUnit测试方法，相当于当前模块，如果是main，相对于当前project
        FileOutputStream fos = new FileOutputStream("bank.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(account);

        oos.close();
        fos.close();
    }

    @Test
    public void test02() throws IOException, ClassNotFoundException {
        //对bank.dat文件进行反序列化
        FileInputStream fis = new FileInputStream("bank.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);

        Account object = (Account) ois.readObject();
        System.out.println(object);
        System.out.println(Account.getRate());


        ois.close();
        fis.close();
    }

    @Test
    public void test01() throws IOException {
        Account account = new Account("1111",10000);
        Account.setRate(0.035);

        //要序列化account对象
        //相对路径，相当于当前模块，因为我是JUnit测试方法，相当于当前模块，如果是main，相对于当前project
        FileOutputStream fos = new FileOutputStream("bank.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(account);

        oos.close();
        fos.close();
    }
}
