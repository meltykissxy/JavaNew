package test03;

import org.junit.jupiter.api.Test;

public class TestClassLoader {
    @Test
    public void play01() {
        //查看一下：TestClassLoader这个类是谁加载
        //第一步：先得到这个类的Class对象
        Class<TestClassLoader> c = TestClassLoader.class;
        //第二步：通过Class对象.getClassLoader()就可以获取加载这个类的类加载器对象
        ClassLoader loader = c.getClassLoader();
        System.out.println(loader);
        System.out.println("父加载器：" + loader.getParent());
    }
    @Test
    public void play02() {
        Class<String> c2 = String.class;
        ClassLoader loader2 = c2.getClassLoader();
        System.out.println(loader2);
    }
    @Test
    public void play03() throws ClassNotFoundException {
        Class<?> c3 = Class.forName("com.atguigu.other.Demo");
        ClassLoader loader3 = c3.getClassLoader();
        System.out.println(loader3);
        System.out.println("父加载器：" + loader3.getParent());
    }
    @Test
    public void play0() throws ClassNotFoundException {
        //(1)得到AppClassLoader的对象
        ClassLoader appClassLoader = TestClassLoader.class.getClassLoader();
        //(2)让AppClassLoader的对象去加载"java.lang.String类"
        Class<?> aClass = appClassLoader.loadClass("java.lang.String");
        System.out.println(aClass);
        System.out.println(aClass.getClassLoader());//null
    }
}
