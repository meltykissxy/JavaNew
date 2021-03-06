package test03;

import java.util.*;

/*
### 6、练习6
（1）存储咱们班每组学员信息，组长姓名为key，组员包括组长自己为value
（2）遍历显示
（3）从键盘输入一个学员姓名，查找这个学员是否咱们班
 */
public class TestMapExer2 {
    public static void main(String[] args) {
        //存储咱们班每组学员信息，组长姓名为key，组员包括组长自己为value
        //key：String
        //value：不止一个人，要么String[]，要么是ArrayList<String> 或 其他的Collection系列的集合
        HashMap<String , ArrayList<String>> map = new HashMap<>();
        //第一组：
        ArrayList<String> group1 = new ArrayList<>();
        group1.add("含笑");
        group1.add("秦杰");
        group1.add("王阳");

        map.put("含笑", group1);

        //第二组：
        ArrayList<String> group2 = new ArrayList<>();
        group2.add("王宏伟");
        group2.add("郎云鹤");
        group2.add("陈涛");

        map.put("王宏伟", group2);

        Set<Map.Entry<String, ArrayList<String>>> entries = map.entrySet();
        for (Map.Entry<String, ArrayList<String>> entry : entries) {
            System.out.println(entry);
        }

        System.out.println("-----------------------------------");
        Set<Map.Entry<String, ArrayList<String>>> entries2 = map.entrySet();
        for (Map.Entry<String, ArrayList<String>> entry : entries2) {
            //Map.Entry<K,V>接口类型有两个方法： getKey(), getValue()
            System.out.println("组长：" + entry.getKey());
            System.out.println("该组的成员们：");
            ArrayList<String> members = entry.getValue();
            for (String member : members) {
                System.out.println(member);
            }
        }

        System.out.println("------------------------------");
        Scanner input = new Scanner(System.in);
        System.out.print("请输入要查找的学生的姓名：");
        String name = input.next();

        //获取全部学员的姓名
        Collection<ArrayList<String>> values = map.values();
        //但是全部的学生情况是分为一个组一个组，遍历每一组，在每一组中查找name
        boolean flag = false;
        for (ArrayList<String> group : values) {
            if(group.contains(name)) {
                System.out.println("找到了");
                flag = true;
                break;
            }
        }
        if(!flag){
            System.out.println("没找到");
        }
    }
}
