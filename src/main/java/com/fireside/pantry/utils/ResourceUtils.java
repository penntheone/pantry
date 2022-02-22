package com.fireside.pantry.utils;

import java.io.InputStream;

public class ResourceUtils {

    public static InputStream loadResource(String path) {
        return ResourceUtils.class.getClassLoader().getResourceAsStream(path);
    }
}
