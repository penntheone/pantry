package com.fireside.pantry.app;


import com.fireside.pantry.db.DatabaseConnector;
import com.fireside.pantry.db.Row;
import com.fireside.pantry.util.objects.User;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserManager {

    public static List<User> getAllUsers() {
        return getUsers("select * from pantry.users;");
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
