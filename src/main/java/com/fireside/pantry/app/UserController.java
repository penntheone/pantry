package com.fireside.pantry.app;

import com.fireside.pantry.db.DatabaseConnector;
import com.fireside.pantry.db.Row;
import com.fireside.pantry.util.objects.User;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserController {

    public static List<User> getAllUsers() {
        return getUsers("CALL spGetAllUsers();");
    }

    public static User getUserByFirstName(String firstName) {
        return getUser(String.format("CALL spGetUserByFName('%s');", firstName));
    }

    public static User getUserByLastName(String lastName) {
        return getUser(String.format("CALL spGetUserByLName('%s');", lastName));
    }

    public static User getUserByUsername(String username) {
        return getUser(String.format("CALL spGetUserByUsername('%s');", username));
    }

    // -------------- Private methods --------------

    private static User getUser(String query) {
        try {
            List<Row> rows = new DatabaseConnector().query(query);
            if (rows.size() == 0)
                return new User();
            return new User(rows.get(0));
        } catch (Exception exception) {
            exception.printStackTrace();
            return new User();
        }
    }

    private static List<User> getUsers(String query) {
        try {
            List<Row> rows = new DatabaseConnector().query(query);
            LinkedList<User> users = new LinkedList<>();
            for (Row row : rows) {
                users.add(new User(row));
            }
            return users;
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ArrayList<>();
        }
    }
}
