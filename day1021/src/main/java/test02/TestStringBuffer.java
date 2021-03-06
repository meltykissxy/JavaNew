package test02;

import org.junit.Test;

/*
CharSequence：字符序列，接口
    它的实现类：String, StringBuffer, StringBuilder等

六、可变字符序列：StringBuffer和StringBuilder
1、String与 StringBuffer和StringBuilder的区别是什么？
（1）String类的对象是不可变的
（2）StringBuffer和StringBuilder的对象是可变的。

差异：
    String类的对象，一旦修改，就会产生新对象。
    StringBuffer和StringBuilder的对象的修改，在原对象的基础上修改的。

2、StringBuffer和StringBuilder有什么区别？
（1）StringBuffer线程安全的可变字符序列
（2）StringBuilder一个可变的字符序列。此类提供一个与 StringBuffer 兼容的 API，但不保证同步。
    该类被设计用作 StringBuffer 的一个简易替换，用在字符串缓冲区被单个线程使用的时候（这种情况很普遍）。
    如果可能（单线程），建议优先采用该类，因为在大多数实现中，它比 StringBuffer 要快。

总结：StringBuffer线程安全，效率相对低，从JDK1.0就有了
     StringBuilder线程不安全，效率相对高，JDK1.5之后引入的

3、API
（1）创建对象，必须使用构造器
（2）其他方法们，以StringBuffer为例
A：StringBuffer append(Xxx b)：在StringBuffer对象中追加xx内容，支持各种数据类型
B：StringBuffer delete(int start, int end)  ：在StringBuffer对象中删除一段字符序列，删除[start,end)范围
   StringBuffer deleteCharAt(int index) ：在StringBuffer对象中删除一个字符，位置是[index]
C：void setCharAt(int index, char ch)：替换[index]位置的字符
   void setLength(int newLength)：修改长度
   StringBuffer replace(int start, int end, String str) ：替换[start,end)范围的字符序列为str
D：StringBuffer reverse() ：反转字符串
E：public StringBuffer insert(int dstOffset, CharSequence s)：在StringBuffer对象中插入一段字符序列
  它有多种重载形式
F：int indexOf(String str)
 int lastIndexOf(String str)

 4、底层如何存储的呢？
       底层也是用char[]数组
 然后是如果可变的呢？
       char[]数组，一旦确定长度，就不能变。
       如果现有的char[]数组长度不够了，就会扩容，用新的数组代表它。

 那么 StringBuffer str = new StringBuffer();默认的char[]数组的长度是多少？
      默认长度是16；
 那么 StringBuffer str = new StringBuffer("hello");默认的char[]数组的长度是多少？
      默认长度是"hello".length() + 16；

 如何扩容的呢？
    哪些方法涉及到扩容？append(..) 和 insert(..)

 */
public class TestStringBuffer {
    @Test
    public void test09() {
        StringBuffer str = new StringBuffer();
    }

    @Test
    public void test08() {
        StringBuffer str = new StringBuffer("helloworld");
        str.insert(1,"haha");
        System.out.println(str);//hhahaelloworld
    }
    @Test
    public void test07() {
        StringBuffer str = new StringBuffer("helloworld");
        str.reverse();
        System.out.println(str);//dlrowolleh
    }

    @Test
    public void test06() {
        StringBuffer str = new StringBuffer("helloworld");
        str.setCharAt(0,'H');
        System.out.println(str);//Helloworld

        str.setLength(5);
        System.out.println(str);//Hello

        str.replace(1,3,"aaa");
        System.out.println(str);//Haaalo
    }

    @Test
    public void test05() {
        StringBuffer str = new StringBuffer("helloworld");
        str.deleteCharAt(0).delete(1,5);
        System.out.println(str);//eorld
    }

    @Test
    public void test04(){
        StringBuffer str = new StringBuffer();
        str.append("hello").append(456).append(true);
        /*
        str.append("hello")：在StringBuffer对象的基础上追加字符串hello，它的返回值类型还是StringBuffer，并且是当前对象
        可以在原有对象基础上接着append
         */
        System.out.println(str);
    }

    @Test
    public void test03(){
        // StringBuilder str = "hello";//错误的，因为右边是String，左边是StringBuilder，它俩不是父子类关系
        StringBuilder str = new StringBuilder("hello");
        str.append("world");
        System.out.println(str);//helloworld
    }

    @Test
    public void test02(){
       // StringBuffer str = "hello";//错误的，因为右边是String，左边是StringBuffer，它俩不是父子类关系
        StringBuffer str = new StringBuffer("hello");
        str.append("world");
        System.out.println(str);//helloworld
    }

    @Test
    public void test01(){
        String str = "hello";
        String result = str.concat("world");
        System.out.println(str);//hello
        System.out.println(result);//helloworld
    }
}
