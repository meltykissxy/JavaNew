package test05;

import org.junit.Test;

/*
5、在声明泛型类与泛型接口时，还可以指定上限

示例：
我们某小学要制作一个学生成绩管理系统，里面需要声明一个学生类型，
这个学校的老师比较有个性，
语文老师说：我评定学生的成绩是用“85、75、”
数学老师说：我评定学生的成绩是用“85.5,52.0...”
....
我们如何设计这个学生类型。

无论是哪个老师，虽然我们意见不完全一致，但是我们要求成绩的类型必须是数字。
可能是Integer，Double，Long等
它们有一个共同的特点，都是Number的子类。

java.lang.Number类型：

（1）声明泛型的上限语法
【修饰符】 class  类名<泛型/类型变量 extends 上限>{
}
【修饰符】 interface  接口名<泛型/类型变量 extends 上限>{
}

如果假设，这个成绩，不仅要求是Number或Number的子类，还要求，成绩必须支持比较大小。
即T有两个上限，Number 和 Comparable

如果，这个成绩，又加要求，上限有三个，Number 和 Comparable 和 Cloneable

【修饰符】 class  类名<泛型/类型变量 extends 上限1 & 上限2 & 上限3 ...>{
}
【修饰符】 interface  接口名<泛型/类型变量 extends 上限1 & 上限2 & 上限3 ...>{
}

要求：上限中，类最多只能有一个，接口可以是多个，而且如果有类，类必须在最左边
 */
public class TestGenericGrammer3 {
    @Test
    public void test01(){
//        Student<String> stu = new Student<>();//错误，因为T类型的上限是Number
                                            //<>中必须是Number或Number的子类
//        Student<Double> stu1 = new Student<>();//错误，Double没有实现Cloneable
//        Student<Integer> stu2 = new Student<>();//错误，Double没有实现Cloneable
//        Student<Long> stu3 = new Student<>();//错误，Double没有实现Cloneable
//        Student<Number> stu4 = new Student<>();//错误，Number没有实现Comparable

        Student<MyNumber> stu5 = new Student<>();
    }
}

//即现在的T有要求，有上限
/*
class Student<T extends Number>{
    private String name;
    private T score;
}*/
/*
class Student<T extends Number & Comparable>{
    private String name;
    private T score;
}*/
class Student<T extends  Number &  Comparable & Cloneable>{
    private String name;
    private T score;
}

class MyNumber extends Number implements Comparable, Cloneable{

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    @Override
    public int intValue() {
        return 0;
    }

    @Override
    public long longValue() {
        return 0;
    }

    @Override
    public float floatValue() {
        return 0;
    }

    @Override
    public double doubleValue() {
        return 0;
    }
}