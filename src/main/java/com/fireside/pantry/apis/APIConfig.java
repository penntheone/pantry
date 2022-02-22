package com.fireside.pantry.apis;

import java.util.List;

public class APIConfig {

    private final String key;
    private final String url;
    private final List<APIPath> paths;

    public APIConfig(String key, String url, List<APIPath> paths) {
        this.key = key;
        this.url = url;
        this.paths = paths;
    }

    public String getKey() {
        return key;
    }

    public String getUrl() {
        return url;
    }

    public List<APIPath> getPaths() {
        return paths;
    }

    public String getPath(String name) throws NullPointerException {
        for (APIPath path : paths) {
            if (path.name.equals(name))
                return path.path;
        }
        throw new NullPointerException();
    }
}
