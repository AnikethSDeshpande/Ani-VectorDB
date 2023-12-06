package com.ani.vectordb.anivectordb.core.filehandlers;

import java.util.UUID;

public class Utils {
    /**
     * This class contains utility functions for file operations
     */

    public static String getDBRoot(){
        return "/Users/ani/AniVectorDB/";
    }
    public static String generateUUID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
