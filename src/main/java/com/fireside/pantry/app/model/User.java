package com.fireside.pantry.app.model;

import com.fireside.pantry.db.Row;
import com.google.gson.Gson;

public class User {

    private final int id;
    private final String username;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final String birthday;
    private final String authString;

    public User() {
        this.id = -1;
        this.username = "";
        this.email = "";
        this.firstName = "";
        this.lastName = "";
        this.birthday = "";
        this.authString = "";
    }

    public User(int id,
                String username, String email,
                String firstName, String lastName,
                String birthday, String authString) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.authString = authString;
    }

    public User(Row row) throws IllegalArgumentException {
        try {
            this.id = Integer.parseInt(row.get("id"));
            this.username = row.get("username");
            this.email = row.get("email");
            this.firstName = row.get("first_name");
            this.lastName = row.get("last_name");
            this.birthday = row.get("birthday");
            this.authString = row.get("auth_string");
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

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
