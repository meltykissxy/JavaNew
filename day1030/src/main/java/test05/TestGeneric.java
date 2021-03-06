package test05;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

/*
具体通过Class对象能干什么？
（1）可以创建任意类型的对象，除了抽象类等不能创建对象的类型以外。
（2）可以操作任意对象的任意成员变量
（3）可以操作任意对象的任意方法，除了抽象方法不能直接运行，必须有它实现类才能创建对象
（4）就可以获取某个类的所有信息，只要他里面有的
（5）获取某个泛型父类的信息
步骤：
第一步：获取该类型的Class
第二步：获取该类型的父类类型

代表类型的类型更多了：
（1）Class
（2）GenericArrayType：它代表的是T[]类型
（3）ParameterizedType：它代表的是 ArrayList<String>
（4）TypeVariable：代表T，U，K，V
（5）WildcardType：代表ArrayList<?>  ArrayList<? super xx> ..

这些类型的公共接口是Type
 */
public class TestGeneric {
    public static void main(String[] args) {
        //获取Sub类在继承Father类是，指定泛型类型是什么
        //第一步：获取该类型的Class
        Class clazz = Sub.class;
        
        //第二步：获取它父类的类型
        //例如：获取Father<String, Integer>
        Class superclass = clazz.getSuperclass();//这个只能得到父类的类名，不能得到泛型的信息
        System.out.println("superclass = " + superclass);

        Type genericSuperclass = clazz.getGenericSuperclass();
        System.out.println("genericSuperclass = " + genericSuperclass);
        //Father<String, Integer>属于ParameterizedType
        ParameterizedType pt = (ParameterizedType) genericSuperclass;
        
        //如果是ParameterizedType，可以获取<>中的 实际的类型
        Type[] actualTypeArguments = pt.getActualTypeArguments();
        for (Type actualTypeArgument : actualTypeArguments) {
            System.out.println("actualTypeArgument = " + actualTypeArgument);
        }
    }
}

class Father<T, U>{
    private T[] arr;
}
class Sub extends Father<ArrayList<String>, Integer>{

}