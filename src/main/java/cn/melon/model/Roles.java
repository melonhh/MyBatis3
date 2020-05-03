package cn.melon.model;

import java.util.List;

public class Roles {
    private int id;
    private String roleName;
    private int state;

    private List<Person> persons;

    public Roles() {
    }

    public Roles(int id, String roleName, int state, List<Person> persons) {
        this.id = id;
        this.roleName = roleName;
        this.state = state;
        this.persons = persons;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", state=" + state +
                ", persons=" + persons +
                '}';
    }
}
