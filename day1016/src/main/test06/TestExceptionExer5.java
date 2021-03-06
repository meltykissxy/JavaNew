package com.atguigu.test06;

public class TestExceptionExer5 {
    static int i = 0;
    public static void main(String[] args) {
        System.out.println(test());//2
    }

    public static int test(){
        try{
            return ++i; //执行  （1）i先自增 （2）把i的值返回给调用者  1 （3） 准备结束当前方法，但是结束之前要去执行finally
        }finally{
            return ++i;//执行  (1)i再自增 （2）把i的值返回给调用者  2 覆盖原来的1，（3）结束当前方法
        }
    }
}
