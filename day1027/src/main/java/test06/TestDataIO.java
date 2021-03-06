package test06;

import org.junit.Test;

import java.io.*;

/*
六、数据IO流：读写各种基本数据类型和字符串
1、对于各种基本数据类型，FileInputStream，FileReader,FileWriter，FileOutputStream没有提供直接的支持，
需要借助数据IO流处理一下。

2、类型
DataInputStream：读各种基本数据类型和字符串
        字节==>各种基本数据类型和字符串
DataOutputStream：写各种基本数据类型和字符串
        各种基本数据类型和字符串==>字节
 */
public class TestDataIO {
    @Test
    public void test5()throws IOException {
        FileInputStream fis = new FileInputStream("d:/game.dat");
        DataInputStream dis = new DataInputStream(fis);

        //读的顺序与写的顺序必须是一致的
        String name = dis.readUTF();
        int age = dis.readInt();
        char gender = dis.readChar();
        int en = dis.readInt();
        double price = dis.readDouble();
        boolean relive = dis.readBoolean();

        System.out.println(name);
        System.out.println(age);
        System.out.println(gender);
        System.out.println(en);
        System.out.println(price);
        System.out.println(relive);

        dis.close();
        fis.close();
    }

    @Test
    public void test4()throws IOException {
        String name = "巫师";
        int age = 300;
        char gender = '男';
        int energy = 5000;
        double price = 75.5;
        boolean relive = true;

        //把上面的数据写到文件中，永久保存起来
        FileOutputStream fos = new FileOutputStream("d:/game.dat");
        DataOutputStream dos = new DataOutputStream(fos);

        dos.writeUTF(name);
        dos.writeInt(age);
        dos.writeChar(gender);
        dos.writeInt(energy);
        dos.writeDouble(price);
        dos.writeBoolean(relive);

        dos.close();
        fos.close();
    }

    @Test
    public void test3()throws IOException {
        FileReader fr = new FileReader("d:/game.dat");
        char[] data  = new char[1024];
        fr.read(data);

        //取几个字符构成name，几个字符构成age....
    }
    @Test
    public void test2()throws IOException {
        String name = "巫师";
        int age = 300;
        char gender = '男';
        int energy = 5000;
        double price = 75.5;
        boolean relive = true;

        //把上面的数据写到文件中，永久保存起来
        FileWriter fw = new FileWriter("d:/game.dat");
        //使用字符串的形式把各种基本数据类型的数据写出去
        fw.write(name);
        fw.write(age+"");
        fw.write(gender+"");
        fw.write(energy+"");
        fw.write(price+"");
        fw.write(relive+"");

        fw.close();
    }

    @Test
    public void test()throws IOException {
        String name = "巫师";
        int age = 300;
        char gender = '男';
        int energy = 5000;
        double price = 75.5;
        boolean relive = true;

        //把上面的数据写到文件中，永久保存起来
        FileOutputStream fos = new FileOutputStream("d:/game.dat");
//        fos.write(xx);//不行，不能支持各种基本数据类型
    }
}
