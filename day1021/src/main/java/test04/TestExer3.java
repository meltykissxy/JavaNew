package test04;

import java.text.Collator;
import java.util.Arrays;
import java.util.Comparator;

/*
1、声明员工类型Employee，包含姓名（String），薪资（double），年龄（int）
2、员工类Employee实现java.lang.Comparable<T>接口，
指定T为Employee类型，重写抽象方法，按照薪资比较大小，薪资相同的按照姓名的自然顺序比较大小。
3、在测试类中创建Employee数组，然后调用Arrays.sort(Object[] arr)方法进行排序，遍历显示员工信息
4、再次调用Arrays.sort(Object[] arr,Comparator<T> c)方法进行按照年龄排序，
年龄相同的按照姓名自然顺序比较大小，遍历显示员工信息
 */
public class TestExer3 {
    public static void main(String[] args) {
        Employee[] arr = new Employee[4];
        arr[0] = new Employee("王阳", 20000, 28);
        arr[1] = new Employee("秦杰", 2500, 22);
        arr[2] = new Employee("樊彦辰", 25000, 22);
        arr[3] = new Employee("董浩", 25000, 24);

        Arrays.sort(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

        System.out.println("-------------------");
        Arrays.sort(arr, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                int result = Integer.compare(o1.getAge(),o2.getAge());
                if(result != 0){
                    return result;
                }
//                return o1.getName().compareTo(o2.getName());//自然排序
                return Collator.getInstance().compare(o1.getName(),o2.getName());//定制比较
            }
        });

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
class Employee implements Comparable<Employee>{
    private String name;
    private double salary;
    private int age;

    public Employee(String name, double salary, int age) {
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    public Employee() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Employee o) {
        //重写抽象方法，按照薪资比较大小，薪资相同的按照姓名的自然顺序比较大小。
        int result = Double.compare(this.salary, o.salary);
        if(result != 0){
            return result;
        }
        return this.name.compareTo(o.name);
        //字符串的自然顺序，是按照字符的Unicode编码值比较
    }
}