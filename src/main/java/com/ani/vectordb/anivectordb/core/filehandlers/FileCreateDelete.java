package com.ani.vectordb.anivectordb.core.filehandlers;

import java.nio.file.FileAlreadyExistsException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.ani.vectordb.anivectordb.core.filehandlers.Utils.getDBRoot;

public class FileCreateDelete {
    /**
        This class has all the methods to create and delete files
        for indices and metadata
     */

    private final String dbRoot;

    FileCreateDelete(){
        this.dbRoot = getDBRoot();
    }
    private boolean createFile(String fileName, String fileLocation) {
        fileLocation = this.dbRoot + fileLocation;
        Path path = Path.of(fileLocation);

        try {
            Files.createFile(path);
            System.out.println(fileName + " created successfully.");
            return true;
        } catch (FileAlreadyExistsException e){
            System.out.println("File already exists");
            return true;
        } catch (IOException e) {
            System.out.println("File creation exception: " + e.toString());
            return false;
        }
    }

    private boolean deleteFile(String fileName, String fileLocation) {
        fileLocation = this.dbRoot + fileLocation;
        Path path = Path.of(fileLocation);

        try {
            Files.deleteIfExists(path);
            System.out.println(fileName + " deleted successfully.");
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String args[]) {
        FileCreateDelete fileOpsHandler = new FileCreateDelete();
        String filePath = "index/init2.txt";
        fileOpsHandler.createFile("init2", filePath);
//        fileOpsHandler.deleteFile("init", filePath);
    }
}
