package com.fireside.pantry.app;

import com.fireside.pantry.db.DatabaseConnector;
import com.fireside.pantry.db.Row;
import com.fireside.pantry.util.objects.User;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserController {

    /**
     * Gets list of users
     * @return All users
     */
    public static List<User> getAllUsers() {
        return getUsers("CALL spGetAllUsers();");
    }

    /**
     * Gets a user based on a specified first name
     * @param firstName the first name
     * @return a user based on this first name
     */
    public static User getUserByFirstName(String firstName) {
        return getUser(String.format("CALL spGetUserByFName('%s');", firstName));
    }

    /**
     * Gets a user based on a specified last name
     * @param lastName the last name
     * @return a user based on this last name
     */
    public static User getUserByLastName(String lastName) {
        return getUser(String.format("CALL spGetUserByLName('%s');", lastName));
    }

    /**
     * Gets a user based on a specified username
     * @param username the username
     * @return a user based on this username
     */
    public static User getUserByUsername(String username) {
        return getUser(String.format("CALL spGetUserByUsername('%s');", username));
    }

    // -------------- Private methods --------------

    /**
     * Gets a user based on a search query
     * @param query The search query
     * @return Search results based on query
     */
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

    /**
     * Gets users based on a search query
     * @param query The search query
     * @return Search results based on query
     */
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
