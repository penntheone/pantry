package com.fireside.pantry.app.cache;

import com.fireside.pantry.app.model.Recipe;

public class RecipeCache extends ObjectCache {

    public RecipeCache(int size) {
        super(size);
    }

    public Recipe retrieve(int id) {
        return (Recipe) super.retrieve(id);
    }

    public void store(int id, Recipe recipe) {
        super.store(id, recipe);
    }
}
