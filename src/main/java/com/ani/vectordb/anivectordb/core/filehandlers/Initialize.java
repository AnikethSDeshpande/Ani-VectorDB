package com.ani.vectordb.anivectordb.core.filehandlers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Initialize {
    /*
        This class creates the required directories for the DB.
        Later implement code to write the directories in config file.
     */
    private String dbRoot;
    public Initialize(String dbRoot){
        if (dbRoot == null){
            this.dbRoot = getDefaultDBRoot();
        } else {
            this.dbRoot = dbRoot;
        }
    }

    private String getDefaultDBRoot(){
        String userHome = System.getProperty("user.home");
        String dbRootPath = userHome + "/AniVectorDB/";

        return dbRootPath;
    }

    private boolean createDir(String name, String location){
        Path locationPath = Path.of(location);

        if (Files.exists(locationPath) && Files.isDirectory(locationPath)){
            System.out.println(name + " is already initialized.");
            return true;
        }

        try {
            Files.createDirectory(locationPath);
            System.out.println(name + " initialized successfully.");
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean createRootDir() {
        String dbRootPath = this.dbRoot;
        return this.createDir("root", dbRootPath);
    }

    public boolean createIndexDir(){
        String indexDirPath = this.dbRoot + "index";
        return this.createDir("index", indexDirPath);
    }

    public boolean createMetaDir(){
        String metaDirPath = this.dbRoot + "metadata";
        return this.createDir("metadata", metaDirPath);
    }

    public static void main(String args[]){
        Initialize init = new Initialize("/Users/ani/tempVDB/");
        init.createRootDir();
        init.createIndexDir();
        init.createMetaDir();
    }
}
