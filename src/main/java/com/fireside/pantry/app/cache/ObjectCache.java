package com.fireside.pantry.app.cache;

import java.util.HashMap;
import java.util.Map;

public class ObjectCache {

    private final Map<Integer, Object> objects;
    private final int[] queue;
    private int nextOverwrite;

    public ObjectCache(int size) {
        this.objects = new HashMap<>();
        this.queue = new int[size];
        this.nextOverwrite = 0;
    }

    public Object retrieve(int id) {
        if (contains(id)) return objects.get(id);
        return null;
    }

    public void store(int id, Object image) {
        objects.remove(queue[nextOverwrite]);
        objects.put(id, image);
        queue[nextOverwrite] = id;
        if (nextOverwrite < 10)
            nextOverwrite++;
        else
            nextOverwrite = 0;
    }

    public int size() {
        return objects.size();
    }

    public boolean contains(int id) {
        return objects.containsKey(id);
    }
}
