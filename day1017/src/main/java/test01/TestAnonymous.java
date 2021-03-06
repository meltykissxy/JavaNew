package com.atguigu.test01;

/*
5、使用匿名内部类来继承Thread类 或  实现Runnable接口
示例：
（1）通过匿名内部类继承Thread类，打印：1-100的偶数
（2）通过匿名内部类实现Runnable接口，打印：1-100的奇数
 */
public class TestAnonymous {
    public static void main(String[] args) {
/*        new Thread(){
            public void run(){
                for(int i=2; i<=100; i+=2){
                    System.out.println("thread:" + i);
                }
            }
        }.start();*/

        Thread thread = new Thread() {
            public void run() {
                for (int i = 2; i <= 100; i += 2) {
                    System.out.println("thread:" + i);
                }
            }
        };
        thread.start();

        System.out.println("--------------------------------------");
/*        Runnable runnable = new Runnable() {
            public void run() {
                for (int i = 1; i <= 100; i += 2) {
                    System.out.println("runnable:" + i);
                }
            }
        };
        Thread t = new Thread(runnable);
        t.start();*/

        new Thread(new Runnable() {
            public void run() {
                for (int i = 1; i <= 100; i += 2) {
                    System.out.println("runnable:" + i);
                }
            }
        }).start();
    }
}
