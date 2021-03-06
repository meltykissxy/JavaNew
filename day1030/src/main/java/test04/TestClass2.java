package test04;

import org.junit.jupiter.api.Test;

public class TestClass2 {
    @Test
    public void play01() {
        //得到类加载器对象
        //ClassLoader classLoader = TestClass2.class.getClassLoader();
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        try {
            //用类加载器对象，去加载我们要加载的其他类
            Class<?> aClass = classLoader.loadClass("test04.BestKiss");
            System.out.println(aClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
