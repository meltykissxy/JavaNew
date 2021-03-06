package test01;

public class TestRuntime {
    public static void main(String[] args) {
        //java.lang.Runtime类
        //它的对象代表当前Java程序的JVM运行环境
        //一个Java程序只有一个JVM运行环境，所以Runtime是单例的
        Runtime runtime = Runtime.getRuntime();
    }
}
