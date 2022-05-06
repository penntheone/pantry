package com.fireside.pantry.app.model;

import com.fireside.pantry.db.Row;
import com.google.gson.Gson;

/**
 * Class for all recipe requests
 */
public class RecipeRequests {
    private int id = -1;
    private int user_id;
    private String submit_date;
    private String review_date;
    private boolean isApproved;
    private boolean isActive;
    private String title;
    private String category;
    private String region;
    private String instructions;
    private String image_url;
    private String video_url;
    private String comments;
    private boolean isAdded;

    /**
     * Default constructor
     */
    public RecipeRequests() {
        this.id = -1;
        this.user_id = -1;
        this.title = "";
        this.category = "";
        this.region = "";
        this.instructions = "";
        this.image_url = "";
        this.video_url = "";
    }

    /**
     * Workhorse constructor
     * @param id request id
     * @param title request title
     * @param category request category
     * @param region request region
     * @param instructions request instructions
     * @param thumb_url request thumb url
     * @param youtube_url request youtube url
     */
    public RecipeRequests(int user_id, String title, String category, String region, String instructions,
                          String image_url, String video_url) {
        this.user_id = user_id;
        this.title = title;
        this.category = category;
        this.region = region;
        this.instructions = instructions;
        this.image_url = image_url;
        this.video_url = video_url;
    }

    /**
     * Constructor based on row
     * @param row row request is based on
     * @throws IllegalArgumentException
     */
    public RecipeRequests(Row row) throws IllegalArgumentException {
        try {
            this.id = Integer.parseInt(row.get("request_id"));
            this.user_id = Integer.parseInt(row.get("user_id"));
            this.submit_date = row.get("submit_date");
            this.review_date = row.get("review_date");
            if (Integer.parseInt(row.get("isApproved")) == 0) {
                this.isApproved = false;
            } else {
                this.isApproved = true;
            }
            if (Integer.parseInt(row.get("isActive")) == 0) {
                this.isActive = false;
            } else {
                this.isActive = true;
            }
            this.title = row.get("recipe_title");
            this.category = row.get("recipe_category");
            this.region = row.get("recipe_region");
            this.instructions = row.get("recipe_instructions");
            this.image_url = row.get("recipe_thumb_url");
            this.video_url = row.get("recipe_youtube_url");
            this.comments = row.get("comments");
            if (Integer.parseInt(row.get("isAdded")) == 0) {
                this.isAdded = false;
            } else {
                this.isAdded = true;
            }
        } catch (Exception exception) {
            throw new IllegalArgumentException(exception);
        }
    }

    /**
     * Converts request to string
     * @return request as string
     */
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }


}
