package bean;

public class Student {
    private String name;

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }

    public void fun(){
        System.out.println("fun");
    }

    private  int test(int a, int b){
        return a+b;
    }
}
