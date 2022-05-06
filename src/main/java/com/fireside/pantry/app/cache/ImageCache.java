package com.fireside.pantry.app.cache;

import javafx.scene.image.Image;

/**
 * Stores all the images
 */
public class ImageCache extends ObjectCache {

    /**
     * Constructor with size
     * @param size the size
     */
    public ImageCache(int size) {
        super(size);
    }

    /**
     * Retrieves image based of id
     * @param id the id
     * @return image with the id
     */
    public Image retrieve(int id) {
        return (Image) super.retrieve(id);
    }

    /**
     * Stores the image and respective id to the cache
     * @param id The id
     * @param image The image
     */
    public void store(int id, Image image) {
        super.store(id, image);
    }
}
