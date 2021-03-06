package test04;

/*
1、声明一个坐标类Coordinate<T>，它有两个属性：x,y，都为T类型
2、在测试类中，创建两个不同的坐标类对象，
分别指定T类型为String和Double，并为x,y赋值，打印对象
 */
public class TestExer1 {
    public static void main(String[] args) {
        Coordinate<String> c1 = new Coordinate<>("东经45","北纬56");
        Coordinate<Double> c2 = new Coordinate<>(85.5,45.3);

        System.out.println(c1);
        System.out.println(c2);
    }
}

class Coordinate<T>{
    private T x;
    private T y;

    public Coordinate() {
    }

    public Coordinate(T x, T y) {
        this.x = x;
        this.y = y;
    }

    public T getX() {
        return x;
    }

    public void setX(T x) {
        this.x = x;
    }

    public T getY() {
        return y;
    }

    public void setY(T y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}