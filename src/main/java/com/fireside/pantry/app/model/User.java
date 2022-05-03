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

    /**
     * Default constructor
     */
    public User() {
        this.id = -1;
        this.username = "";
        this.email = "";
        this.firstName = "";
        this.lastName = "";
        this.birthday = "";
        this.authString = "";
    }

    /**
     * Workhorse constructor
     * @param id user id
     * @param username the username
     * @param email user email
     * @param firstName user first name
     * @param lastName user last name
     * @param birthday user birthday
     * @param authString user authString
     */
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

    /**
     * Constructor based on row
     * @param row row user is based on
     * @throws IllegalArgumentException
     */
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

    /**
     * retrieves id
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * retrieves username
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * retrieves email
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * retrieves first name
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * retrieves last name
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * retrieves birthday
     * @return the birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * retrieves authString
     * @return the authString
     */
    public String getAuthString() {
        return authString;
    }

    /**
     * Converts user to string
     * @return user as string
     */
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
