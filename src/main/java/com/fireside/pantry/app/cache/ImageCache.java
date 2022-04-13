package com.fireside.pantry.app.cache;

import javafx.scene.image.Image;

public class ImageCache extends ObjectCache {

    public ImageCache(int size) {
        super(size);
    }

    public Image retrieve(int id) {
        return (Image) super.retrieve(id);
    }

    public void store(int id, Image image) {
        super.store(id, image);
    }
}
