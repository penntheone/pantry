package com.fireside.pantry.app.cache;

import com.fireside.pantry.app.model.Recipe;

/**
 * Stores all the recipes
 */
public class RecipeCache extends ObjectCache {

    /**
     * Constructor with size
     * @param size the cache size
     */
    public RecipeCache(int size) {
        super(size);
    }

    /**
     * Retrieves recipe based on id
     * @param id the id
     * @return recipe based on the id
     */
    public Recipe retrieve(int id) {
        return (Recipe) super.retrieve(id);
    }

    /**
     * Stores the recipe and respective id to the cache
     * @param id The id
     * @param recipe The recipe
     */
    public void store(int id, Recipe recipe) {
        super.store(id, recipe);
    }
}
