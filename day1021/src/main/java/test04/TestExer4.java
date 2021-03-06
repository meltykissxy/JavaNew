package test04;

/*
1、声明一个Person类，包含姓名和伴侣属性，其中姓名是String类型，而伴侣的类型不确定，
因为伴侣可以是Person，可以是Animal（例如：金刚），可以是Ghost鬼（例如：倩女幽魂），
可以是Demon妖（例如：白娘子），可以是Robot机器人（例如：剪刀手爱德华）。。。
2、在测试类中，创建Person对象，并为它指定伴侣，打印显示信息
 */
public class TestExer4 {
    public static void main(String[] args) {
        Animal a = new Animal("金刚");
        Person<Animal> p = new Person<>("珍妮",a);
        System.out.println(p);

        System.out.println("====================");
        Demon demon = new Demon("白娘子");
        Person<Demon> p2 = new Person<>("许仙",demon);
        System.out.println(p2);

        System.out.println("====================");
        Person<Person> husband = new Person<>("王阳");
        Person<Person> wife = new Person<>("翠花");
        husband.setFere(wife);
        wife.setFere(husband);

        System.out.println(husband);
        System.out.println(wife);
    }
}


