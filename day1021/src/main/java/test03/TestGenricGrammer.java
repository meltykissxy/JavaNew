package test03;

import org.junit.Test;

/*
二、泛型的语法
1、泛型长什么样？
在代码中出现了<类型>，就是泛型。

其实我们之前学过的类型也有和泛型有关系，只是之前忽略了。
例如：java.lang.Comparable<T>
     java.util.Comparator<T>
     java.lang.Enum<E extends Enum<E>>

2、泛型的形式有两种：
根据泛型声明的位置不同：
（1）泛型类/接口
（2）泛型方法

三、泛型类/泛型接口
1、如何声明？
【修饰符】 class  类名<泛型/类型变量>{
}
【修饰符】 interface  接口名<泛型/类型变量>{
}

看Comparable源码：
public interface Comparable<T> {
    public int compareTo(T o); //抽象方法
}

2、示例
我们某小学要制作一个学生成绩管理系统，里面需要声明一个学生类型，
这个学校的老师比较有个性，
语文老师说：我评定学生的成绩是用“优秀、良好、及格..”
数学老师说：我评定学生的成绩是用“85.5,52.0...”
英语老师说：我评定学生的成绩是用“A,B,C,D...”
我们如何设计这个学生类型。

3、如何确定泛型的具体类型呢？
（1）在创建类的对象时，可以确定泛型的类型
（2）在继承父类或实现接口时，可以确定泛型的类型

4、要求
（1）泛型的类型指定必须是引用数据类型，不能是基本数据类型，
如果是基本数据类型，选用包装类解决。
（2）泛型类或泛型接口上面的<T>泛型类型，不能用于静态成员
 */
public class TestGenricGrammer {
    @Test
    public void test04(){
        //语文老师使用定制版的学生类型
        ChineseStudent c = new ChineseStudent("张三","优秀");
    }

    @Test
    public void test03(){
        //英语老师使用Student类型
        Student<Character> stu = new Student<>("张三",'A');
    }

    @Test
    public void test02(){
        //数学老师使用Student类型
//        Student<double> stu1 = new Student<>("张三",85.5);//不能使用基本数据类型
//        Student<Double> stu1 = new Student<>("李四",58);//报错，包装类只能与对应的基本数据类型进行自动装箱 58是int，对应的是Integer
        Student<Double> stu1 = new Student<>("李四",58.5);

        String name = stu1.getName();
        Double score = stu1.getScore();
        System.out.println("姓名：" + name);
        System.out.println("成绩：" + score);
    }

    @Test
    public void test01(){
        //语文老师使用Student类型
        Student<String> stu1 = new Student<>("张三","优秀");//从JDK1.7之后，右边可以省略<>中的类型，但是不要省略<>
//        Student<String> stu2 = new Student<>("李四",58);//报错，因为成绩现在是String类型

        String name = stu1.getName();
        String score = stu1.getScore();
        System.out.println("姓名：" + name);
        System.out.println("成绩：" + score);
    }
}

class Student<T>{
//    private static T school;//不能用于静态成员
    private String name;
    private T score;//成绩的类型不确定，我们使用T表示，这个T是我们自己选择的一个字母而已
                    //所以在类上面要声明一下

    public Student() {
    }

    public Student(String name, T score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getScore() {
        return score;
    }

    public void setScore(T score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}

//语文老师专用学生类
class ChineseStudent extends Student<String>{
    public ChineseStudent() {
    }

    public ChineseStudent(String name, String score) {
        super(name, score);
    }
}

//Circle实现Comparable接口
/*
class Circle implements Comparable{
    private double radius;

    @Override
    public int compareTo(Object o) {
        return Double.compare(radius, ((Circle)o).radius);
    }
}*/

class Circle implements Comparable<Circle>{
    private double radius;


    @Override
    public int compareTo(Circle o) {
        return Double.compare(radius, o.radius);
    }
}