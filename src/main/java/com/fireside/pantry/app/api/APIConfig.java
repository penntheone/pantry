package com.fireside.pantry.app.api;

import java.util.List;

public class APIConfig {

    private final String key;
    private final String url;
    private final List<APIPath> paths;

    /**
     * Constructor method for APIConfig based on parameters
     * @param key
     * @param url
     * @param paths
     */
    public APIConfig(String key, String url, List<APIPath> paths) {
        this.key = key;
        this.url = url;
        this.paths = paths;
    }

    /**
     * Getter method for the key
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * Getter method for the url
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Getter method for the list of paths
     * @return the list of paths
     */
    public List<APIPath> getPaths() {
        return paths;
    }

    /**
     * Gets a specified path based on the parameter name if it exists
     * @param name the specified path being looked for
     * @return the path if it exists
     * @throws NullPointerException if it does not exist
     */
    public String getPath(String name) throws NullPointerException {
        for (APIPath path : paths) {
            if (path.name.equals(name))
                return path.path;
        }
        throw new NullPointerException();
    }
}
