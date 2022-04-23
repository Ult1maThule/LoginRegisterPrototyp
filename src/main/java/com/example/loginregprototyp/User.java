//@author Liam Maurice Heimbach & Vinusan Sivalingam
package com.example.loginregprototyp;

import java.util.Date;

public class User extends Person {
    private Date dateOfBirth;


    public User(String firstname,String lastname,String username,String email, String password, Date dateOfBirth) {
        super(firstname,lastname,username,email,password);

        this.dateOfBirth = dateOfBirth;

    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
