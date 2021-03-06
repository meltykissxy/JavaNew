import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class Playground {
    @Test
    public void play03() {
        List<Integer> integers = Arrays.asList(1, 3, 5, 7);
        System.out.println(integers);
    }
    @Test
    public void play02() {
        Class<?>[] interfaces = Integer.class.getInterfaces();
        for (Class<?> anInterface : interfaces) {
            System.out.println(anInterface);
        }
    }
    @Test
    public void play01() throws ClassNotFoundException {
        System.out.println(int.class);
        System.out.println(Integer.class);
        System.out.println(Beauty.class);
        Beauty beauty = new Beauty();
        System.out.println(beauty.getClass());
        System.out.println(Class.forName("Beauty"));
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        System.out.println(classLoader.loadClass("Beauty"));
        System.out.println("".getClass());
        System.out.println(Integer.class.getPackage().getName());
        Class<?>[] interfaces = Integer.class.getInterfaces();

        System.out.println(Integer.class.getSuperclass().getName());
    }
}

class Beauty {
    public String name;
    private int age;
}