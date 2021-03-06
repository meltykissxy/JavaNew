package com.atguigu.test04;

import org.junit.*;

/*
三、JUnit
1、它本身是一个测试小框架
2、它不是JRE核心类库中，是第三方的小框架
3、如何使用
第一步：引入jar包（.class文件）
方式有两种：
方式一：使用Maven（各种jar包仓库管理工具），当我们使用JUnit的相关API时，提示我们add xxx to classpath，
点击它，会自动连网，从Maven仓库下载jar，默认下载到C:\Users\用户名\.m2\repository\*

方式二：（弥补的方式）
A：在idea项目路径下，建一个libs的文件夹
把junit等第三方的jar放到libs中
B：选择File菜单-->Project structure...--> libraries-->+ -> java -> idea项目中libs
-->选择要用该jar的模块名即可

检查：择File菜单-->Project structure...-->modules --> 应用的模块-->dependencies-->查看是否有你选择的库

4、注意，你自己写的类以后不要加Test了。

5、介绍几个JUnit的相关注解
(1)@Test，它标记的方法，表示可以作为单独的单元测试方法运行，就不需要我们编写main方法了

有要求：
A：这个方法必须是在public类中
因为，JUnit框架中帮我们调用该方法，如果该方法所在的类不是public，表示跨包不可见。
B：这个方法所在的类有且只有一个无参构造
Test class should have exactly one public zero-argument constructor；
Test class can only have one constructor；
C：这个方法本身必须是public
Method test01() should be public，否则跨包不能使用
D：这个方法本身必须是无参的
Method test01 should have no parameters
E：这个方法本身必须是void
Method test01() should be void
F：这个方法不能是static

总结：在public的类中，只能有一个无参构造，方法本身是public void 无参的非静态的。


(2)@Before
标记了@Before的方法，会在每一个标记了@Test的方法前面运行
标记@Before要求和@Test一样

(3)@After
标记了@After的方法，会在每一个标记了@Test的方法后面运行
标记@After要求和@Test一样

（4）@BeforeClass
标记了@BeforeClass方法，其他要求和@Test一样，其中一条不一样，就是这个方法必须是static的
（5）@AfterClass
标记了@AfterClass方法，其他要求和@Test一样，其中一条不一样，就是这个方法必须是static的

标记了@BeforeClass的方法，在所有的@Before和@Test之前运行，只允许一次
标记了@AfterClass的方法，在所有的@After和@Test之后运行，只允许一次
 */
public class TestJUnit {

    @Test
    public void test01(){
        System.out.println("hello junit1");
    }

    @Test
    public void test02(){
        System.out.println("hello junit2");
    }

    @Before
    public void a(){
        System.out.println("aaa");
    }

    @After
    public void b(){
        System.out.println("bbb");
    }

    @BeforeClass
    public static void start(){
        System.out.println("start");
    }

    @AfterClass
    public static void end(){
        System.out.println("end");
    }
}
