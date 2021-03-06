package test06;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Set;

/*
（三）第三代：JDK1.8之后引入的
1、增加了：
java.time
java.time.chrono
java.time.format
java.time.temporal
java.time.zone
这些包下的类型都是和日期时间有关的。

针对第一代和第二代，它们面临的问题是：
* 可变性：象日期和时间这样的类对象应该是不可变的。Calendar类中可以使用三种方法更改日历字段：set()、add() 和 roll()。
* 偏移性：Date中的年份是从1900开始的，而月份都是从0开始的。
* 格式化：格式化只对Date有用，Calendar则不行。
* 此外，它们也不是线程安全的，不能处理闰秒等。

Java 8 吸收了 Joda-Time 的精华，以一个新的开始为 Java 创建优秀的 API。

* java.time – 包含值对象的基础包
* java.time.chrono – 提供对不同的日历系统的访问。
            除默认ISO之外的日历系统的通用API。
* java.time.format – 格式化和解析时间和日期
* java.time.temporal – 包括底层框架和扩展特性
* java.time.zone – 包含时区支持的类

2、常用类型
（1）java.time包下LocalDate、LocalTime、LocalDateTime
LocalDate：本地日期
LocalTime：本地时间
LocalDateTime：本地日期加时间

A：针对你要日期，时间，还是日期时间都要，选择更多
B：考虑本地化
C：它们的API基本统一
D：不可变，如果修改，必须接收新对象

方法们：
now()：获取当前系统的日期/时间
of(...)：获取具体值对应的日期/时间
withXxx/plusXxx/minusXxx：修改或增加或 减少xxx日期、时间值
getXxx：获取具体的日期或时间的值
isLeapYear()：判断某个日期对应的年份是否是闰年
    @Override
    public boolean isLeapYear(long prolepticYear) {
        return ((prolepticYear & 3) == 0) && ((prolepticYear % 100) != 0 || (prolepticYear % 400) == 0);
    }

    //(prolepticYear & 3) == 0  等价于  (prolepticYear % 4) == 0
    //3的二进制：24个0 0000 0011
    // x & 3：结果有几种，看最后2位，有几种情况：00,01,10,11   对应：0,1,2,3  和余数一样


（2）java.time.ZonedDateTime
ZoneId.getAvailableZoneIds()：可以查看所有的Java中支持的时区
ZonedDateTime.now(ZondId对象)：获取指定时区的日期时间

（3）持续的日期或时间
本质就是两个日期，或两个时间的间隔
Period和Duration

（4）DateTimeFormatter：日期时间格式化
 */
public class TestDateTime3 {
    @Test
    public void test10() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);//2020-10-19T16:30:53.708

        //默认格式，国籍标准格式
        DateTimeFormatter df = DateTimeFormatter.ISO_DATE_TIME;
        System.out.println(df.format(now));//2020-10-19T16:31:31.74

        DateTimeFormatter df2 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
        System.out.println(df2.format(now));//20-10-19 下午4:32

        DateTimeFormatter df3 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
        System.out.println(df3.format(now));//2020年10月19日 下午04时33分05秒

        DateTimeFormatter df4 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        System.out.println(df4.format(now));//2020-10-19 16:33:05

        DateTimeFormatter df5 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).withZone(ZoneId.of("Asia/Shanghai"));
        System.out.println(df5.format(now));//2020年10月19日 星期一 下午04时34分40秒 CT

        DateTimeFormatter df6 = DateTimeFormatter.ofPattern("yyyy-MM-dd是这一年的第D天");
        System.out.println(df6.format(now));//2020-10-19是这一年的第293天
    }
    @Test
    public void test09() {
        LocalTime now = LocalTime.now();
        LocalTime off = LocalTime.of(17,0,0);
        Duration d = Duration.between(now, off);
        System.out.println(d);//PT29M57.686S
    }

    @Test
    public void test08() {
        LocalDate today = LocalDate.now();
        LocalDate newYear = LocalDate.of(2021,1,1);

        Period p = Period.between(today, newYear);
        System.out.println(p);//P2M13D  2个月13天
        System.out.println(p.getDays());//13

    }

    @Test
    public void test07() {
        ZonedDateTime z1 = ZonedDateTime.now();//本地
        ZonedDateTime z2 = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));//上海
        ZonedDateTime z3 = ZonedDateTime.now(ZoneId.of("America/Chihuahua"));//Chihuahua

        System.out.println(z1);
        System.out.println(z2);
        System.out.println(z3);
        /*
        2020-10-19T16:23:32.788+08:00[Asia/Shanghai]
        2020-10-19T02:23:32.789-06:00[America/Chihuahua]
         */
    }

    @Test
    public void test06() {
        //需要知道一些时区的id
        //Set<String>是一个集合，容器
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        //快捷模板iter
        for (String availableZoneId : availableZoneIds) {
            System.out.println(availableZoneId);
        }

    }

    @Test
    public void test05() {
        LocalDate today = LocalDate.now();
        System.out.println(today.getYear());
        System.out.println(today.getMonthValue());
        System.out.println(today.getDayOfMonth());

        System.out.println(today.isLeapYear());//true
    }

    @Test
    public void test04(){
        LocalDate today = LocalDate.now();
        LocalDate future = today.plusDays(50);
        System.out.println(today);//2020-10-19
        System.out.println(future);//2020-12-08
    }

    @Test
    public void test03(){
        LocalDate birth = LocalDate.of(2000,8,9);
        System.out.println(birth);
    }

    @Test
    public void test02(){
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);//2020-10-19T15:56:44.460
    }

    @Test
    public void test01(){
        LocalDate today = LocalDate.now();
        System.out.println(today);//2020-10-19
    }
}
