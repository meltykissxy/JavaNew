package test02;

/*
二、注解
1、什么是注解？
注解：annotation
注解又被称为注释，但是它和我们之前讲的注释有点不同，
之前我们用过：单行注释//
            多行注释
这两种注释完全是给“人/程序员”看的。

今天学习的注解，它也有注释/解释的作用，但是它不仅仅是给人看，它更重要的是给“另一段代码”看的。
因此注解又称为“代码级别的注释”。

2、长什么样？
@注解名
@注解名(...)

例如：@Override

3、一个完整的注解，它应该包含三个部分：
（1）注解的声明
和之前学过的类、变量、方法等一样，都是要先声明后使用

（2）注解的使用

（3）注解的读取
必须由另一段代码来读取它，才有它实际的意义，否则就没有意义

4、学习几个常用的注解
（1）@Override
A：声明：在java.lang包下

package java.lang;
import java.lang.annotation.*;
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Override {
}

B：使用
在子类或实现类重写父类或父接口的方法时，在方法的上面可以加@Override

C：编译器在编译时，读取到某个方法上面标记了@Override 注解的话，会对该方法进行严格的格式检查，
按照重写的要求进行格式检查。
a：方法名是否一致
b：形参列表是否一致
c：返回值类型
    基本数据类型和void：必须一致
    引用数据类型：<=
d：修饰符
    权限修饰符：>=
    其他修饰符：不能static，final，private等
e：异常列表：后面讲

（2）@Deprecated
A：声明

package java.lang;
import java.lang.annotation.*;
import static java.lang.annotation.ElementType.*;
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value={CONSTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, PACKAGE, PARAMETER, TYPE})
public @interface Deprecated {
}

B：使用
在类、方法、包等上面出现@Deprecated

C：读取
编译器在编译时，如果读取到某个类、方法、包等上面出现了@Deprecated，就会警告使用者，
该类、方法、包等已经过时了，不建议使用了。
如果继续使用它，可能比较麻烦，或者是有风险。

为什么存在@Deprecated注解？
开发原则：面向修改关闭，对扩展开发。
    因为之前的某个类、方法等设计的有问题，但是它已经存在一段时间了，被大量的使用了，
    我们如果轻易去修改它，会导致更多的问题。
    那么我们只能标记为@Deprecated，让更新的（后来的）开发人员，不要用了，改为别的方案。

 （3）@SuppressWarnings
A：声明

package java.lang;
import java.lang.annotation.*;
import static java.lang.annotation.ElementType.*;
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})
@Retention(RetentionPolicy.SOURCE)
public @interface SuppressWarnings {
    String[] value();
}

B：使用
在我们弹警告的类、方法等上面加@SuppressWarnings

C：读取
编译器读取到某个类、方法等上面加了@SuppressWarnings，就不弹对应的警告

总结：
从JDK1.5之后引入的：
（1）可变参数
（2）枚举
（3）注解
（4）foreach
....

 */

import java.util.ArrayList;
import java.util.Date;

//@SuppressWarnings(value={"uncheck","unused","deprecated"})
@SuppressWarnings("all")
public class TestAnnotation {
    public static void main(String[] args) {
        Date d = new Date(2020,10,16);//year参数应该是表示距离1900年过了几年
                                                        //month参数表示月份值，但是1月从0开始。
        System.out.println(d);//Tue Nov 16 00:00:00 CST 3920


        ArrayList list = new ArrayList();
        list.add(1);
    }
}
@SuppressWarnings(value="all")
class Father{
    public void method(){
        System.out.println("父类的方法");
    }

    public void print1n(){
        System.out.println("父类的print1n方法");
    }

    public double fun(){
        return 0.0;
    }
}
@SuppressWarnings(value="all")
class Sub extends Father{
    @Override //使用@Override注解
    public void method() {
        System.out.println("子类重写的方法");
    }
/*    @Override
    public void println(){
        System.out.println("子类重写的println方法");
    }

    @Override
    public void method(String info){
        System.out.println("子类重写方法");
    }

    @Override
    public int fun(){
        return 0;
    }*/
}