package test03;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/*
2、如何使用它们
要求：
（1）凡是要序列化的对象，它的类型必须实现一个接口：java.io.Serializable接口
否则在序列化过程中，就会报NotSerializableException。

（2）在实现java.io.Serializable接口，还得给这个类，固定一个“序列化版本ID”serialVersionUID
private static final long serialVersionUID = 3016115315876162045L; //具体的值，可以自己随便写

需求：
第一步：在对象已经序列化到文件之后，修改对象的类型。
  例如：student已经序列化（保存）到d:/stu.dat文件中了，现在要修改Student类
第二步：直接用原来的反序列的代码，读取d:/stu.dat文件
    发现报错：InvalidClassException（无效的类异常）

问题原因：如果我们在声明类并实现Serializable接口时，没有“固定”serialVersionUID的话，
        那么，每次修改类，重新编译后，都会自动生成一个新的serialVersionUID。

    哪怕对类的修改，并不影响反序列化，它也会报错，因为它先检查serialVersionUID是否一致，如果不一致，直接报错。

如果我们在声明类并实现Serializable接口时，“固定”serialVersionUID的话，哪怕对类做了修改，
  它也会“尽量尝试”去反序列化，如果是增加了某些新的成员变量，原来没有该成员变量，该成员变量的值先按默认值处理。
                    如果是减少了某些成员变量，本次读取时，就放弃该成员变量。
  如果尝试失败，再报异常。


 */
public class TestSerialVersionUID {

    @Test
    public void test2() throws IOException, ClassNotFoundException {
        //需求：读取d:/stu.dat文件，把里面的数据重构成一个Java对象
        //(1)选择IO流：FileInputStream   排除FileReader因为是整个对象处理，不是纯文本）
        FileInputStream fis = new FileInputStream("d:/stu.dat");
        //光有FileInputStream不够，需要对象IO流，
        ObjectInputStream ois = new ObjectInputStream(fis);

        //(2)调用read
        Object obj = ois.readObject();
        /*
        java.io.InvalidClassException（无效的类异常）: com.atguigu.test02.Student;
        local（本地） class（字节码） incompatible（不相容的；矛盾的）:
        stream（流） classdesc（类描述description） serialVersionUID（序列化版本ID） = 3016115315876162045,
        local class serialVersionUID = -3875554927712659365
         */
        System.out.println(obj);

        //（3）关闭
        ois.close();
        fis.close();
    }
}
