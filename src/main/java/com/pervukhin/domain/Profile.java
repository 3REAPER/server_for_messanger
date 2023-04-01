package com.pervukhin.domain;
public class Profile {
    private int id;
    private String name;
    private String login;
    private String password;
    private String number;

    public Profile(int id, String name, String login, String password, String number) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.number = number;
    }

    public Profile(String name, String login, String password, String number) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getNumber() {
        return number;
    }
}
