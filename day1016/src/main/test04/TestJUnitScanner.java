package com.atguigu.test04;

import org.junit.Test;

import java.util.Scanner;

public class TestJUnitScanner {
    @Test
    public void test(){
        Scanner input = new Scanner(System.in);
        System.out.print("请输入姓名：");
        String name = input.next();
        System.out.println("name = " + name);
    }
}
