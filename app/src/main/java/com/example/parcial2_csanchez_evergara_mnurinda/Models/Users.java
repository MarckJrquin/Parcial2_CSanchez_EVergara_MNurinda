package com.example.parcial2_csanchez_evergara_mnurinda.Models;

public class Users {
    private String name;
    private String ID;
    private int age;
    private String username;
    private String password;
    private String usertype;

    public Users() {

    }

    public Users(String name, String ID, int age, String username, String password, String usertype) {
        this.name = name;
        this.ID = ID;
        this.age = age;
        this.username = username;
        this.password = password;
        this.usertype = usertype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }
}
