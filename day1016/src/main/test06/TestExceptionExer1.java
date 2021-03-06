package com.atguigu.test06;

/*
运行结果：
test结束
1
 */
public class TestExceptionExer1 {
    public static void main(String[] args) {
        int result = test("12");//返回值是1
        System.out.println(result);
    }

    public static int test(String str) {
        try {
            Integer.parseInt(str);//虽然没有接收返回值，但是方法会调用
                                    //因为str中是12，所以可以正确转为int，方法不会报异常
                                    //try中不会发生异常
            return 1;           //执行了
        } catch (NumberFormatException e) {
            return -1;
        } finally {
            System.out.println("test结束");//finally也会执行
        }
    }

}
