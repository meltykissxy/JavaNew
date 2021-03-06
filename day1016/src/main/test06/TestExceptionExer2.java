package com.atguigu.test06;

/*
运行结果：
test结束
-1
 */
public class TestExceptionExer2 {
    public static void main(String[] args) {
        int result = test("a");//得到返回值-1
        System.out.println(result);//-1
    }

    public static int test(String str){
        try{
            Integer.parseInt(str);//str中是非数字，是a，所以在转为int时报NumberFormatException异常
            return 1;//没有执行
        }catch(NumberFormatException e){//可以捕获异常
            return -1;//执行了
        }finally{
            System.out.println("test结束");//finally也会执行
        }
    }
}
