package com.fireside.pantry.app.control;

import com.fireside.pantry.app.model.RecipeRequests;
import com.fireside.pantry.db.DatabaseConnector;
import com.fireside.pantry.db.Row;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RequestController {

    public static List<RecipeRequests> getAllRequests() {
        return getRequests("CALL spGetAllUsers();");
    }

    public static List<RecipeRequests> getUsersRequests(int userID) {
        return getRequests(String.format("CALL spGetUserRequests(%s);", userID));
    }

    public static List<RecipeRequests> getActiveRequests() {
        return getRequests("CALL spShowActiveRequests();");
    }

    /**
     * Adds requested recipe to the recipe database
     */
    public static String finalizeRequest(int requestID) {
        String query = String.format("CALL spGetUserRequests(%s);", requestID);
        try {
            List<Row> row = new DatabaseConnector().query(query);
            return "Success";
        } catch (Exception exception) {
            return "Failed";
        }
    }

    /**
     * Rejects a requested recipe
     */
    public static String rejectRequest(int requestID) {
        String query = String.format("CALL spRejectRequest(%s);", requestID);
        try {
            List<Row> row = new DatabaseConnector().query(query);
            return "Success";
        } catch (Exception exception) {
            return "Failed";
        }
    }

    /**
     * Used if a user wants to remove on of their active a requests
     */
    public static String cancelRequest(int requestID) {
        String query = String.format("CALL spDeleteRequest(%s);", requestID);
        try {
            List<Row> row = new DatabaseConnector().query(query);
            return "Success";
        } catch (Exception exception) {
            return "Failed";
        }
    }

    /**
     * Used if an admin wants to approve a request
     */
    public static String approveRequest(int requestID) {
        String query = String.format("CALL spApproveRequest(%s);", requestID);
        try {
            List<Row> row = new DatabaseConnector().query(query);
            return "Success";
        } catch (Exception exception) {
            return "Failed";
        }
    }

    /**
     * Creates a recipeRequest
     */
    public static String createRequest(int userId, String title, String category, String region,
                                       String instructions, String image_url, String video_url) {
        String query = String.format("CALL spRequestRecipe(%d, '%s', '%s', '%s',\n" +
                "'%s','%s','%s');", userId, title, category, region, instructions, image_url, video_url);
        try {
            List<Row> row = new DatabaseConnector().query(query);
            return "Success";
        } catch (Exception exception) {
            return "Failed";
        }
    }


    /**
     * retrieves a request based on query
     * @param query the search query
     * @return the recipe request
     */
    private static RecipeRequests getRequest(String query) {
        try {
            List<Row> rows = new DatabaseConnector().query(query);
            if (rows.size() == 0)
                return new RecipeRequests();
            return new RecipeRequests(rows.get(0));
        } catch (Exception exception) {
            exception.printStackTrace();
            return new RecipeRequests();
        }
    }

    /**
     * retrieves requests based on query
     * @param query the search query
     * @return the recipe requests
     */
    private static List<RecipeRequests> getRequests(String query) {
        try {
            List<Row> rows = new DatabaseConnector().query(query);
            LinkedList<RecipeRequests> requests = new LinkedList<>();
            for (Row row : rows) {
                requests.add(new RecipeRequests(row));
            }
            return requests;
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ArrayList<>();
        }
    }
}
