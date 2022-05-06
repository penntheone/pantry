package com.fireside.pantry.db.scripts;

import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

public class ScriptUtils {

    private static void readRecipesFromURI() throws Exception {
        URL url = new URL("http://data.csail.mit.edu/im2recipe/recipes_with_nutritional_info.json");
        ReadableByteChannel inChannel = Channels.newChannel(url.openStream());
        FileOutputStream out = new FileOutputStream("recipes.json");
        FileChannel outChannel = out.getChannel();
        out.getChannel().transferFrom(inChannel, 0, Long.MAX_VALUE);
    }

    public static String escapeString(String original) {
        StringBuilder builder = new StringBuilder();
        for (char c : original.toCharArray()) {
            if (c == '\'') builder.append('\\');
            builder.append(c);
        }
        return builder.toString();
    }
}
