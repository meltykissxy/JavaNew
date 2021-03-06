package test04;

public class Person<T>{
    private String name;
    private T fere;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, T fere) {
        this.name = name;
        this.fere = fere;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getFere() {
        return fere;
    }

    public void setFere(T fere) {
        this.fere = fere;
    }

    @Override
    public String toString() {
        if(!(fere instanceof Person)) {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", fere=" + fere +
                    '}';
        }
        return "Person{" + "name='" + name + ",fere =" + ((Person)fere).getName() + "}";
        //编译时类型是T，T代表任意引用数据类型，不一定有getName()
    }
}
