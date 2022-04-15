package com.example.loginregprototyp;

public class Admin extends Person {
    private int adminID;

    public Admin(String username, String password, int adminID) {
        super(username, password);

        this.adminID = adminID;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }
}
