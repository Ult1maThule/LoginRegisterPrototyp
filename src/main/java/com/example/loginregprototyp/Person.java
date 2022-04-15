package com.example.loginregprototyp;

public abstract class Person {
    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String password;

    public Person(String firstname,String lastname,String username, String email, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getEmail() {
        return email;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstname() {
        this.firstname=firstname;
    }

    public void setEmail() {this.email=email;}

    public void setLastname() {this.lastname=lastname;}
}
