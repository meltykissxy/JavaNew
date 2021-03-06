package test05;

import bean.Student;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;

public class TestNewInstance {
    @Test
    public void test2()throws Exception {
        //(1）获取com.atguigu.bean.Student的Class对象
        Class<?> aClass = Class.forName("com.atguigu.bean.Student");

        //（2）得到Student类的构造器对象
        /*
        aClass.getDeclaredConstructors();  ==> 获取所有该类声明的构造器们
        aClass.getConstructors();  ==> 获取所有该类声明的公共的构造器们
        aClass.getDeclaredConstructor(Class... parameterType); ==> 获取该类某一个具体的声明的构造器
        aClass.getConstructor(Class... parameterType)==> 获取该类某一个具体的声明的公共构造器

        一个类中构造器可以重载，获取其中一个，如何区分？通过形参列表
         */

        //例如：得到Student类的private Student() 构造器
        Constructor<?> c1 = aClass.getDeclaredConstructor();
        System.out.println(c1);
        //因为无参构造是私有的，我们要绕过权限验证
        c1.setAccessible(true);//可访问的，无论你原来的权限修饰符是什么，我都让当前程序可以访问该构造器
        Object stu1 = c1.newInstance();
        System.out.println(stu1);

        //例如：得到Student类的public Student(String name)
        Constructor<?> c2 = aClass.getDeclaredConstructor(String.class);
        System.out.println(c2);
        Object stu2 = c2.newInstance("张三");
        System.out.println(stu2);
    }

    @Test
    public void test()throws Exception{
        //编译期间没有com.atguigu.bean.Student这个类，但是不妨碍我写接下来的代码

        //(1）获取com.atguigu.bean.Student的Class对象
        Class<?> aClass = Class.forName("com.atguigu.bean.Student");
        //（2）创建com.atguigu.bean.Student的学生对象
        Object stu = aClass.newInstance();//stu是Student的对象
        //方式一：有要求，Student类必须有公共的无参构造
        System.out.println(stu);
    }
}
