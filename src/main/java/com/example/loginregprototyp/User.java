package com.example.loginregprototyp;

import java.util.Date;

public class User extends Person {
    private Date dateOfBirth;
    private String email;

    public User(String username, String password, Date dateOfBirth, String email) {
        super(username, password);

        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
