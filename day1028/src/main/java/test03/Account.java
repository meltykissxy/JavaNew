package test03;

import java.io.Serializable;

public class Account implements Serializable {
    private static double rate;//利率
    private String id;
    private double balance;
    private static final long serialVersionUID = 3016115315876162045L;
    private transient int a;//临时变量，希望这个变量的值不永久保存，每次随机一个

    public Account() {
    }

    public Account(String id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public Account(String id, double balance, int a) {
        this.id = id;
        this.balance = balance;
        this.a = a;
    }

    public static double getRate() {
        return rate;
    }

    public static void setRate(double rate) {
        Account.rate = rate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", balance=" + balance +
                ", a=" + a +
                '}';
    }
}
