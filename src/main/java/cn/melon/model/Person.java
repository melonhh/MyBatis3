package cn.melon.model;

public class Person {
    private int id;
    private String pName;
    private Roles roles;

    public Person() {
    }

    public Person(int id, String pName, Roles roles) {
        this.id = id;
        this.pName = pName;
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", pName='" + pName + '\'' +
                ", roles=" + roles +
                '}';
    }
}
