package design.maliang.domain;

public class Account {
    private int uid;
    private String name;
    private long money;

    @Override
    public String toString() {
        return "Account{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", money=" + money +
                '}';
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public Account() {

    }

    public Account(int uid, String name, long money) {

        this.uid = uid;
        this.name = name;
        this.money = money;
    }
}
