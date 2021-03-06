package com.atguigu.test05;

/*
4、异常的抛出机制
过程：
（1）当某句代码发生异常之后，JVM暂停下来，会检查是什么问题导致的异常，
这个时候，会找到的异常的类型，并创建一个对象。
例如：空指针异常，下标越界异常等

（2）抛出这个对象
（3）JVM会在这句代码的外围，搜索是否有try...catch结构，
A：如果没有，说明在该方法中，压根没有考虑处理异常，那么当前方法就会结束，并且把这个异常抛给调用者。
如果调用者也没有处理，一路上往上抛，直到main方法，如果main也没有处理，程序就挂了。

B：如果在某句发生异常的代码周围有try...catch结构，并且可以捕获这个异常，
  那么程序就从try...catch下面继续运行。

 public static void main(String[] args){
        xx.a();
}

public void a(){
    b();
}
public void b(){
    代码1；
    c(); 发生异常，如果这里也没有处理，下面的代码2不会执行，
    代码2；
}

public void c(){
    代码1
    代码2  发生异常  在这里停下来，如果代码2位置没有处理异常，那么代码3是没有机会执行
    代码3
}
 */
public class TestExceptionThrow {
}
