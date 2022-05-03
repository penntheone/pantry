package com.fireside.pantry.app.control;

import com.fireside.pantry.app.cache.ImageCache;
import com.fireside.pantry.db.DatabaseConnector;
import com.fireside.pantry.app.model.Recipe;
import javafx.scene.image.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Map;

public class ImageController {

    private static final Logger logger = LoggerFactory.getLogger(ImageController.class);
    private static ImageController instance;

    private ImageCache imageCache;

    /**
     * A constructor method which creates an imageCache of size 50
     */
    private ImageController() {
        this.imageCache = new ImageCache(50);
    }

    /**
     * Will either return the image immediately if it is in the cache,
     * otherwise it will find the recipe id and image, store it in the cache, and then
     * return the image
     * @param recipe the specified recipe
     * @return the image of the respective recipe
     */
    public Image getRecipeImage(Recipe recipe) {
        // First check to see if the image is already stored in the cache
        if (imageCache.contains(recipe.getId()))
            return imageCache.retrieve(recipe.getId());

        String query = String.format(
                "SELECT * from RecipeImages WHERE recipe_id='%s';",
                recipe.getId()
        );
        try {
            Map<Integer, InputStream> result = new DatabaseConnector().getImages(query);
            if (result.size() == 0)
                throw new NullPointerException();
            Image image = new Image(result.get(recipe.getId()));
            imageCache.store(recipe.getId(), image);
            return image;
        } catch (Exception exception) {
            logger.error(String.format("Database query produced exception -> %s", query), exception);
            return null;
        }
    }

    /**
     * Returns the object itself if created, will create if not
     * @return the object itself
     */
    public static ImageController getInstance() {
        if (ImageController.instance == null)
            ImageController.instance = new ImageController();
        return ImageController.instance;
    }
}
