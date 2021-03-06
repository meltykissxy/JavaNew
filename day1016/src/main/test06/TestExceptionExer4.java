package com.atguigu.test06;

public class TestExceptionExer4 {
    public static void main(String[] args) {
        int test = test(3,5);
        System.out.println(test);//8
    }

    public static int test(int x, int y){
        int result = x;//result = 3
        try{
            if(x<0 || y<0){ //条件不成立
                return 0; //不执行
            }
            result = x + y; //执行  result = 3+5 = 8
            return result; //执行  （1）先把result的值返回给调用者  （2）结束当前方法，但是在执行（2）之前，要先执行finally
        }finally{
            result = x - y;//执行  result = 3-5=-2，但是因为返回值已经给调用者了，新的result值不会覆盖原来的8
        }
    }
}
