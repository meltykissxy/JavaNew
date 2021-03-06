package test06;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.*;

/*
（二）第二代日期时间
1、java.util.Calendar：日历类型
Calendar 类是一个抽象类，不能直接new对象。

（1）如何获取Calendar 类的对象
A：创建它子类的对象，GregorianCalendar 是 Calendar 的一个具体子类，提供了世界上大多数国家/地区使用的标准日历系统。
B：Calendar 提供了一个类方法 getInstance，以获得此类型的一个通用的对象。

（2）获取更加具体的日期或时间中的某个值
get(常量)
（3）设置具体的日期或时间
set（常量，值）

问题：
A：得到一个当前的系统日期时间，比较麻烦
B：可变对象
C：没有考虑闰秒

2、针对第一代和第二代的日期格式化问题，提供了java.text包
（1）DateFormat
DateFormat 是日期/时间格式化的抽象类
（2）SimpleDateFormat，它是DateFormat的子类，可以用它
SimpleDateFormat类的API文档中给我们提供了一个表格，某个日期时间元素使用一个字母表示

方法：
A：format：对某个Date对象进行格式化
B：parse：对某个字符串进行解析，把字符串转为Date对象
 */
public class TestDateTime2 {
    @Test
    public void test03(){
        Date d = new Date();

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS E Z");
        /*
        y：年， M：月，d：月份中的天，即日， H：24小时制的小数，m：分，s：秒,S：毫秒 E：星期 Z：时区
         */
        System.out.println(sf.format(d));//2020-10-19 15:47:22 013 星期一 +0800   +0800表示东八区

    }

    @Test
    public void test02(){
        Scanner input = new Scanner(System.in);
        System.out.print("年：");
        int year = input.nextInt();

        System.out.print("月：");
        int month = input.nextInt();

        System.out.print("日：");
        int day = input.nextInt();

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month-1);
        c.set(Calendar.DATE, day);

        System.out.println("是这一年的第" + c.get(Calendar.DAY_OF_YEAR));
    }

    @Test
    public void test01(){
//        Calendar c = new Calendar();//Calendar 类是一个抽象类
        Calendar c1 = new GregorianCalendar();//创建子类对象
        Calendar c2 = Calendar.getInstance();//默认是本地语言环境对应的日历对象

//        System.out.println(c2);//信息太多了
        System.out.println("年：" + c2.get(Calendar.YEAR));
        System.out.println("月：" + c2.get(Calendar.MONTH));//月份从0开始
        System.out.println("日：" + c2.get(Calendar.DATE));

        System.out.println("时：" + c2.get(Calendar.HOUR));//12小时制
        System.out.println("时：" + c2.get(Calendar.HOUR_OF_DAY));//24小时制
        System.out.println("分：" + c2.get(Calendar.MINUTE));
        System.out.println("秒：" + c2.get(Calendar.SECOND));
        System.out.println("毫秒：" + c2.get(Calendar.MILLISECOND));

        System.out.println("星期：" + c2.get(Calendar.DAY_OF_WEEK));//2   星期一   1代表的是星期天
        //The first day of the week is _______? Sunday 不是Monday

        System.out.println("一年中的第几天" +c2.get(Calendar.DAY_OF_YEAR));//293
    }
}
