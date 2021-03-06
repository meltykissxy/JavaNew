package test02;

import java.io.Serializable;

public class Student implements Serializable {
    private int id;
    private String name;
    private int score;
    private Teacher teacher;
    private int age;
    private static final long serialVersionUID = 3016115315876162045L;

    public Student() {
    }

    public Student(int id, String name, int score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public Student(int id, String name, int score, Teacher teacher) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.teacher = teacher;
    }

    public Student(int id, String name, int score, Teacher teacher, int age) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.teacher = teacher;
        this.age = age;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", score=" + score +
                ", teacher=" + teacher +
                ", age=" + age +
                '}';
    }
}
