package test07;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
七、java.util.Scanner
1、之前用它，键盘输入

System.in：
在System类中：
public final static InputStream in = null;

2、现在
public Scanner(InputStream source)构造器指定别的输入流

3、方法
（1）hasNextXxx()
（2）nextXxx()

 */
public class TestScanner {
    @Test
    public void test2() throws FileNotFoundException {
        //使用public Scanner(InputStream source)构造器
        Scanner input = new Scanner(new FileInputStream("d:/1.txt"));
        while(input.hasNextLine()){
            System.out.println(input.nextLine());
        }
        input.close();//关闭
    }

    @Test
    public void test(){
        //使用public Scanner(InputStream source)构造器
        Scanner input = new Scanner(System.in);
    }
}
