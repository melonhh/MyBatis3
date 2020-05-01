package cn.melon.model;

import java.sql.Date;

public class User {
    private int id;
    private String username;
    private String password;
    private Date regDate;
    private int state;

    public User() {
    }

    public User(int id, String username, String password, Date regDate, int state) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.regDate = regDate;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", regDate=" + regDate +
                ", state=" + state +
                '}';
    }
}
