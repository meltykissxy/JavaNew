package com.atguigu.test06;

/*
运行结果：
test结束
0
 */
public class TestExceptionExer3 {
    public static void main(String[] args) {
        int result = test("a");//得到返回值0
        System.out.println(result);//0
    }

    public static int test(String str){
        try{
            Integer.parseInt(str);//str中是非数字，是a，所以在转为int时报NumberFormatException异常
            return 1; //不会执行
        }catch(NumberFormatException e){//可以捕获异常
            return -1;//会执行   （1）先把-1返回给调用者，（2）结束当前方法，其中的（2）没有来得及执行，要先去执行finally
        }finally{
            System.out.println("test结束");//也会执行
            return 0;//也会执行   （1）把0返回给调用者，覆盖了之前的-1 （2）结束当前方法
        }
    }
}
