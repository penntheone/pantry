package com.fireside.pantry.util.objects;

public class User {

    private final int userId;
    private final String username;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final String birthday;
    private final String authString;

    public User(int userId,
                String username, String email,
                String firstName, String lastName,
                String birthday, String authString) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.authString = authString;
    }

    public int getUserId() {
        return userId;
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
        return String.format("%s:%s:%s:%s:%s:%s",
                userId,
                username,
                email,
                firstName,
                lastName,
                birthday
        );
    }
}
