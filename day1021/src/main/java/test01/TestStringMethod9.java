package com.atguigu.test01;

import org.junit.Test;

/*
（9）常用方法系列8：和拆分有关
public String[] split(String regex)：支持正则，按照Xx规则进行拆分
 */
public class TestStringMethod9 {
    @Test
    public void test02(){
        String str = "张三.23|李四.24|王五.25";

        String[] strings = str.split("\\|");//|在正则中是有特殊意义的，表示或，所以需要在正则中进行转义，\|，但是Java中\又有特殊的意义，再转义
        for (int i = 0; i < strings.length; i++) {
            System.out.println(strings[i]);
        }

        System.out.println("--------------------------------");
        for (int i = 0; i < strings.length; i++) {
            String[] split = strings[i].split("\\.");//.在正则中是有特殊意义的，表示任意字符，所以需要转义，\.，但是Java中\又有特殊的意义，再转义
            for (int j = 0; j < split.length; j++) {
                System.out.println(split[j]);
            }
        }
    }

    @Test
    public void test01(){
        String str = "1:张三,2:李四,3:王五";

        //把str中的数据，拆分，生成n个Student对象，放到Student数组中
        //先按照,进行拆分
        String[] strings = str.split(",");
        for (int i = 0; i < strings.length; i++) {
            System.out.println(strings[i]);
        }

        System.out.println("-----------------------------");
        Student[] students = new Student[strings.length];
        for (int i = 0; i < strings.length; i++) {
            String[] split = strings[i].split(":");
            //split[0]是编号
            //split[1]是姓名
            int id = Integer.parseInt(split[0]);
            String name = split[1];

            students[i] = new Student(id,name);
        }

        for (int i = 0; i < students.length; i++) {
            System.out.println(students[i]);
        }

    }
}
class Student{
    private int id;
    private String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}