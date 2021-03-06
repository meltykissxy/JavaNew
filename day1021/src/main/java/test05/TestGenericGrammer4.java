package test05;

/*
6、在使用泛型类或泛型接口时，可以对泛型进行擦除
问题：泛型擦除后，T按照什么类型处理？
   默认按照第一个上限处理的。
   如果T没有指定上限，它的默认上限就是Object。
 */
public class TestGenericGrammer4 {
    public static void main(String[] args) {
        MyClass<String> my = new MyClass<>();
        my.method("hello");

        System.out.println("------------------");
        MyClass my2 = new MyClass();//泛型擦除
        my2.method(1);
        my2.method("hello");

        System.out.println("------------------");
        OtherDemo t = new OtherDemo();//泛型擦除
        t.method(1);
//        t.method("hello");//错误
    }
}

class MyClass<T>{
    public void method(T t){
        System.out.println(t);
    }
}
class OtherDemo<T extends Number>{
    public void method(T t){
        System.out.println(t);
    }
}