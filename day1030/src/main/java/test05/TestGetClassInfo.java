package test05;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/*
具体通过Class对象能干什么？
（1）可以创建任意类型的对象，除了抽象类等不能创建对象的类型以外。
（2）可以操作任意对象的任意成员变量
（3）可以操作任意对象的任意方法，除了抽象方法不能直接运行，必须有它实现类才能创建对象
（4）就可以获取某个类的所有信息，只要他里面有的
步骤：
第一步：先获取该类的Class对象
第二步：获取它的信息
 */
public class TestGetClassInfo {
    public static void main(String[] args) {
        //获取String类的所有信息
        //第一步：先获取该类的Class对象
        Class clazz = String.class;
        
        //第二步：获取它的信息
        Package aPackage = clazz.getPackage();
        System.out.println("aPackage = " + aPackage);

        String clazzName = clazz.getName();
        System.out.println("clazzName = " + clazzName);

        Class superclass = clazz.getSuperclass();
        System.out.println("superclass = " + superclass);

        Class[] interfaces = clazz.getInterfaces();
        for (Class anInterface : interfaces) {
            System.out.println("anInterface = " + anInterface);
        }

        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println("declaredField = " + declaredField);
        }

        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
        for (Constructor declaredConstructor : declaredConstructors) {
            System.out.println("declaredConstructor = " + declaredConstructor);
        }

        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println("declaredMethod = " + declaredMethod);
        }
    }
}

