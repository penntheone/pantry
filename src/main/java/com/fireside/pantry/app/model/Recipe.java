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

    /**
     * Default constructor
     */
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

    /**
     * Workhorse constructor
     * @param id recipe id
     * @param title recipe title
     * @param category recipe category
     * @param region recipe region
     * @param instructions recipe instructions
     * @param thumb_url recipe thumb url
     * @param youtube_url recipe youtube url
     */
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

    /**
     * Constructor based on row
     * @param row row recipe is based on
     * @throws IllegalArgumentException
     */
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

    /**
     * Retrieves recipe id
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieves recipe title
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Retrieves recipe category
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Retrieves recipe category
     * @return the category
     */
    public String getRegion() {
        return region;
    }

    /**
     * Retrieves recipe instructions
     * @return the instructions
     */
    public String getInstructions() {
        return instructions;
    }

    /**
     * Retrieves recipe thumb url
     * @return the thumb url
     */
    public String getThumbUrl() {
        return thumb_url;
    }

    /**
     * Retrieves recipe youtube url
     * @return the youtube url
     */
    public String getYoutubeUrl() {
        return youtube_url;
    }

    /**
     * Retrieves recipe ingredients
     * @return the ingredients
     */
    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    /**
     * Retrieves recipe image
     * @return the image
     */
    public Image getImage() {
        return image;
    }

    /**
     * Sets ingredients to parameter
     * @param ingredients ingredients for recipe
     */
    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * Sets image to parameter
     * @param image image for recipe
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * Converts recipe to string
     * @return recipe as string
     */
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
