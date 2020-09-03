package com.example.menmarket.data.model;

public class User {
    private String name;
    private String passWord;

    public User() {
    }

    public User(String name, String passWord) {
        this.name = name;
        this.passWord = passWord;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }


    public String getName() {
        return name;
    }

    public String getPassWord() {
        return passWord;
    }
}
