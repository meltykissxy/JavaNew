package test03;

import java.util.Scanner;

/*
主线程：打印[1,10]，每隔10毫秒打印一个数字，
自定义线程类：不停的问是否结束，输入Y或N，
现在当主线程打印完5之后，就让自定义线程类加塞，直到自定义线程类结束，主线程再继续。
 */
public class TestExer2 {
    public static void main(String[] args) {
        AskThread a = new AskThread();
        a.start();

        for (int i=1; i<=10; i++){
            System.out.println(Thread.currentThread().getName() +":" + i);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                if(i==5){
                    a.join();//这句代码在main线程体中，a线程阻塞了main线程，main要继续必须等a线程结束
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class AskThread extends Thread{
    public void run(){
        Scanner input = new Scanner(System.in);
        while(true) {
            System.out.print("是否继续：(Y,N)：");
            String str = input.next();

            if("N".equals(str)){
                break;
            }
        }

        input.close();//关闭System.in的IO流
    }
}