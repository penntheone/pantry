package com.fireside.pantry.app.model;

import com.fireside.pantry.db.Row;
import com.google.gson.Gson;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Recipe {

    private final int id;
    private final String title;
    private final String category;
    private final String region;
    private final String instructions;
    private final String thumb_url;
    private final String youtube_url;
    private List<Ingredient> ingredients;
    private Image image;

    public Recipe() {
        this.id = -1;
        this.title = "";
        this.category = "";
        this.region = "";
        this.instructions = "";
        this.thumb_url = "";
        this.youtube_url = "";
        this.ingredients = new ArrayList<>();
    }

    public Recipe(int id, String title, String category, String region,
                  String instructions, String thumb_url, String youtube_url) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.region = region;
        this.instructions = instructions;
        this.thumb_url = thumb_url;
        this.youtube_url = youtube_url;
    }

    public Recipe(Row row) throws IllegalArgumentException {
        try {
            this.id = Integer.parseInt(row.get("id"));
            this.title = row.get("title");
            this.category = row.get("category");
            this.region = row.get("region");
            this.instructions = row.get("instructions");
            this.thumb_url = row.get("thumb_url");
            this.youtube_url = row.get("youtube_url");
        } catch (Exception exception) {
            throw new IllegalArgumentException(exception);
        }
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getRegion() {
        return region;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getThumbUrl() {
        return thumb_url;
    }

    public String getYoutubeUrl() {
        return youtube_url;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public Image getImage() {
        return image;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
