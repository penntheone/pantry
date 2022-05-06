package com.fireside.pantry.app.model;

import com.fireside.pantry.db.Row;
import com.google.gson.Gson;

public class User {

    private int id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String birthday;
    private String authString;
    private boolean isAdmin;
    private boolean isStaff;

    public User() { }

    public User(Row row) throws IllegalArgumentException {
        try {
            this.id = Integer.parseInt(row.get("id"));
            this.username = row.get("username");
            this.email = row.get("email");
            this.firstName = row.get("first_name");
            this.lastName = row.get("last_name");
            this.birthday = row.get("birthday");
            this.authString = row.get("auth_string");
            this.isAdmin = row.get("is_admin").equals("1");
            this.isStaff = row.get("is_staff").equals("2");
        } catch (Exception exception) {
            throw new IllegalArgumentException(exception);
        }
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getAuthString() {
        return authString;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean isStaff() {
        return isStaff;
    }

    // -- Setters

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setAuthString(String authString) {
        this.authString = authString;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void setStaff(boolean staff) {
        isStaff = staff;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
