package test05;

import bean.Employee;
import bean.Student;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

public class TestField {
    @Test
    public void test02() throws Exception {
        //通过反射操作Employee类的静态的company成员变量
        //(1)第一步：获取该类型的Class对象
        Class clazz = Employee.class;

        //（2）第二步：获取company成员变量对应的Field对象
        Field companyField = clazz.getDeclaredField("company");

        //（3）第三步：可以设置company的值
        companyField.setAccessible(true);
        companyField.set(null, "尚硅谷");//null，代表不需要Employee的对象

        System.out.println(Employee.getCompany());
    }

    @Test
    public void test01() throws Exception {
        Student stu = new Student("张三");
//        stu.name = "李四";//不可以，因为name是私有的

        //(1)第一步：获取该类型的Class对象
        Class<?> aClass = stu.getClass();//得到Student类的Class对象

        //（2）第二步：获取你要操作的成员变量的Field对象
        //例如：这里要操作name成员变量，得到name的Field对象
/*        aClass.getFields();     获取所有的公共的Field
        aClass.getDeclaredFields();  获取所有的已声明的Field
        aClass.getDeclaredField(String fieldName)  获取某个的声明的Field
        aClass.getField(String fieldName)  获取某个的公共的Field

        在一个类中，靠什么区别成员变量？  成员变量的名字

        */
        Field nameField = aClass.getDeclaredField("name");
        //因为name属性私有化，那么我要使得它课访问
        nameField.setAccessible(true);

        //获取name成员变量的值
        Object value = nameField.get(stu);//获取stu对象的name属性值
        System.out.println(value);

        //修改name成员变量的值
        nameField.set(stu, "李四");//修改stu学生对象的name属性为"李四"
        System.out.println(stu);
    }
}
