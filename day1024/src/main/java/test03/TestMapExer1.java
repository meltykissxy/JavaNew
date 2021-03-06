package test03;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/*
（1）从键盘输入本组学员的姓名和他的手机号码，存放到map中，姓名为key,手机号码为value，并且遍历显示
（2）再从键盘输入姓名，查询他的手机号码
 */
public class TestMapExer1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        //创建一个Map
        //key：姓名，value：手机号码
        HashMap<String,String> map = new HashMap<>();
        for (int i=1; i<=3; i++){
            System.out.print("请输入姓名：");
            String name = input.next();

            System.out.print("请输入手机号：");
            String tel = input.next();

            map.put(name, tel);
        }

/*        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println(entry);
        }*/

        for(Map.Entry<String, String> entry : map.entrySet()){
            System.out.println(entry);
        }

        System.out.print("请输入你要查询人的姓名：");
        String find = input.next();

        String phone = map.get(find);
        if (phone == null) {
            System.out.println("没找到");
        }else {
            System.out.println(find + "的手机号码是：" + phone);
        }

    }
}
