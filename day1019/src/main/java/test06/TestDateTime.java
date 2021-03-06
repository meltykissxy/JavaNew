package test06;

import org.junit.jupiter.api.Test;

import java.util.Date;


/*
二、日期和时间相关的API
（一）第一代
java.util.Date：类 Date 表示特定的瞬间，精确到毫秒

注意：java.sql.Date类是java.util.Date的子类，在数据库的日期时间与Java的日期时间映射时可以使用。

（1）很多方法已过时
（2）它显示的日期时间的风格没有考虑本地化，即语言习惯
（3）它是可变对象
按理说，某一个日期时间对象，应该只对应某一个具体的日期时间值
（4）没有考虑闰秒

现在也仍然有人在用，它有三个方法还在用：
（1）new Date()：无参构造
（2）new Date(long 毫秒值)：有参构造
（3）long getTime()：获取某个日期时间距离1970-1-1 0点0分0秒0毫秒的毫秒差值
    其中第三个方法，如果Date对象是当前系统时间的话，也可以用System类public static long currentTimeMillis()方法代替


 */
public class TestDateTime {
    @Test
    public void test02() {
        Date d1 = new Date();//获取当前系统日期时间
        Date d2 = new Date(Long.MAX_VALUE);//Long.MAX_VALUE数字对应的毫秒值对应的 日期时间值是多少
        System.out.println(d1);
        System.out.println(d2);//Sun Aug 17 15:12:55 CST 292278994
        System.out.println(d1.getTime());//当前系统时间距离1970-1-1 0点0分0秒0毫秒的毫秒差值
    }

    @Test
    public void test01(){
        Date d1 = new Date();
        System.out.println(d1);//Mon Oct 19 15:18:10 CST 2020
                //默认的格式不符合我们当前语言环境的日期时间的显示风格

        //已过时
        System.out.println(d1.getYear());//120  年  距离1900年的间隔
        System.out.println(d1.getMonth());//9  月  从0开始 9表示10月
        System.out.println(d1.getDate());//19  日
        System.out.println(d1.getDay());//1  星期

        d1.setYear(121);
        System.out.println(d1);//Tue Oct 19 15:21:59 CST 2021
    }
}
