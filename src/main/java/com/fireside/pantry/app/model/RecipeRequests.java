package com.fireside.pantry.app.model;

import com.fireside.pantry.db.Row;
import com.google.gson.Gson;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getSubmit_date() {
        return submit_date;
    }

    public void setSubmit_date(String submit_date) {
        this.submit_date = submit_date;
    }

    public String getReview_date() {
        return review_date;
    }

    public void setReview_date(String review_date) {
        this.review_date = review_date;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public boolean isAdded() {
        return isAdded;
    }

    public void setAdded(boolean added) {
        isAdded = added;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }


}
