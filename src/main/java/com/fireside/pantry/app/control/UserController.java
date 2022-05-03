package com.fireside.pantry.app.control;

import com.fireside.pantry.db.DatabaseConnector;
import com.fireside.pantry.db.Row;
import com.fireside.pantry.app.model.User;

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


    /**
     * Adds requested recipe to the recipe database
     * params - pref - -1 = don't like, 0 = no preference, 1 = like
     * params - allergy - 0 = Not allergic, 1 = Allergic
     */
    public static String addPref(int user_id, int ingredientId, int pref, int allergy) {
        String query = String.format("CALL spAddUserPref(%s, %s, %s, %s);", user_id, ingredientId, pref,
                allergy);
        try {
            List<Row> row = new DatabaseConnector().query(query);
            return "Success";
        } catch (Exception exception) {
            return "Failed";
        }
    }

    /**
     * Edits a users preferences
     * params - pref - -1 = don't like, 0 = no preference, 1 = like
     * params - allergy - 0 = Not allergic, 1 = Allergic
     */
    public static String editPref(int user_id, int ingredientId, int pref, int allergy) {
        String query = String.format("CALL spAddUserPref(%s, %s, %s, %s);", user_id, ingredientId, pref,
                allergy);
        try {
            List<Row> row = new DatabaseConnector().query(query);
            return "Success";
        } catch (Exception exception) {
            return "Failed";
        }
    }

    /**
     * Removes a users preference
     */
    public static String remove(int user_id, int ingredientId) {
        String query = String.format("CALL spAddUserPref(%s, %s);", user_id, ingredientId);
        try {
            List<Row> row = new DatabaseConnector().query(query);
            return "Success";
        } catch (Exception exception) {
            return "Failed";
        }
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
