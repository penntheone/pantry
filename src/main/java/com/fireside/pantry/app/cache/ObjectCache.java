package com.fireside.pantry.app.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores all the objects
 */
public class ObjectCache {

    private final Map<Integer, Object> objects;
    private final int[] queue;
    private int nextOverwrite;

    /**
     * Constructor based of size and initializes the hash map and overwrite
     * @param size the size
     */
    public ObjectCache(int size) {
        this.objects = new HashMap<>();
        this.queue = new int[size];
        this.nextOverwrite = 0;
    }

    /**
     * Retrieves object based on id
     * @param id the id
     * @return object based on id
     */
    public Object retrieve(int id) {
        if (contains(id)) return objects.get(id);
        return null;
    }

    /**
     * Stores object and id to cache
     * @param id the object's id
     * @param image the object
     */
    public void store(int id, Object image) {
        objects.remove(queue[nextOverwrite]);
        objects.put(id, image);
        queue[nextOverwrite] = id;
        if (nextOverwrite < 10)
            nextOverwrite++;
        else
            nextOverwrite = 0;
    }

    /**
     * Retrieves object count
     * @return the object count
     */
    public int size() {
        return objects.size();
    }

    /**
     * Check if the cache has the id
     * @param id the id it is checking for
     * @return whether the cache contains the id or not
     */
    public boolean contains(int id) {
        return objects.containsKey(id);
    }
}
