package test05;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

/*
具体通过Class对象能干什么？
（1）可以创建任意类型的对象，除了抽象类等不能创建对象的类型以外。
（2）可以操作任意对象的任意成员变量
（3）可以操作任意对象的任意方法，除了抽象方法不能直接运行，必须有它实现类才能创建对象
步骤：
第一步：获取某个类的Class对象
第二步：要获取你要调用的方法的Method对象
第三步：通过调用Method对象.invoke()方法

注意：如果方法的权限修饰符有限制，调用Method对象.setAccessible(true);
 */
public class TestMethod {
    @Test
    public void test3()throws Exception{
        //尝试调用Graphic类public abstract double area();
        //第一步：获取某个类的Class对象
        Class<?> aClass = Class.forName("com.atguigu.bean.Graphic");

        //第二步：要获取你要调用的方法的Method对象
        Method areaMethod = aClass.getDeclaredMethod("area");

        //第三步：调用area方法
        //因为area方法是非静态的，先创建Graphic对象
        Object obj = aClass.newInstance();//InstantiationException，因为抽象类无法实例化
        Object result = areaMethod.invoke(obj);
        System.out.println("result = " + result);
    }

    @Test
    public void test2()throws Exception{
        //通过反射调用Student类public int test(int a, int b)

        //第一步：获取某个类的Class对象
        Class<?> aClass = Class.forName("com.atguigu.bean.Student");

        //第二步：要获取你要调用的方法的Method对象
        Method testMethod = aClass.getDeclaredMethod("test", int.class, int.class);
        //如果test的访问权限有问题
        testMethod.setAccessible(true);

        //第三步：调用test方法
        //因为test方法是非静态的，先创建Student对象
        Object stu = aClass.newInstance();
        Object returnValue = testMethod.invoke(stu, 1, 2);
        System.out.println(returnValue);

    }

    @Test
    public void test()throws Exception{
        //第一步：获取某个类的Class对象
        Class<?> aClass = Class.forName("com.atguigu.bean.Student");

        //第二步：要获取你要调用的方法的Method对象
        //例如：我想要调用Student类fun法
/*        aClass.getDeclaredMethods(); ：获取所有声明的方法，包括私有的等
        aClass.getMethods(); //获取所有公共的
        aClass.getDeclaredMethod(String name, Class... parameterType) //获取某个声明的
        aClass.getMethod(String name, Class... parameterType)// 获取某个公共的

        方法可以重载，如何在一个 类中确定你要找哪个方法？
        （1）方法名
        （2）形参列表
        */
        Method fun = aClass.getDeclaredMethod("fun");
        
        //第三步：让fun方法执行
        //因为fun方法是非静态的，所以需要Student类对象
        Object stu = aClass.newInstance();//要求Student有公共的构造器，否则就的先得到构造器，然后创建对象
        fun.invoke(stu);


    }

}
