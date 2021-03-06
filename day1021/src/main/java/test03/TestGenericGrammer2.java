package test03;

/*
问题：如果某个类中有两个数据的类型不确定怎么办？
   解决：用多个泛型字母表示

提示：我们代表未知的类型一般使用单个大写字母表现，不要用单词。
        因为单词容易和已知的某种类型冲突。

 */
public class TestGenericGrammer2 {
}
class MyClass<T1,T2>{
    private T1 a;
    private T2 b;
}
class Other<Type>{
    private Type a;//Type这个符号与已知的某种类型重名了，接下来使用会有很多误会和麻烦
}