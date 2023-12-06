package com.ani.vectordb.anivectordb.core.filehandlers;

import ch.qos.logback.core.joran.sanity.Pair;
import com.ani.vectordb.anivectordb.core.records.Record;

import java.io.*;
import java.util.*;

import static com.ani.vectordb.anivectordb.core.filehandlers.Utils.getDBRoot;

public class FileIO {
    /**
     * This class has all the methods to read, add or delete records from a file
     */

    private final String dbRoot;
    public FileIO(){
        this.dbRoot = getDBRoot();
    }


    public String parallelGet(String id, String filePath){
        long startTime = System.currentTimeMillis();
        String dbRoot = Utils.getDBRoot();
        filePath = dbRoot + filePath;

        try{
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            Optional<String> results = br.lines()
                    .parallel()
                    .filter(line->{
                        Record record = Record.fromString(line);
                        if(record.id.equals(id)){
                            return true;
                        }
                        return false;
                    })
                    .findAny();

            if (results.get() != null){
                long endTime = System.currentTimeMillis();
                System.out.println("time taken: " + (endTime-startTime));
                return results.get();
            }

        } catch (Exception e){

        }

        return "record not found";
    }

    public String get(String id, String filePath){
        long startTime = System.currentTimeMillis();
        String dbRoot = Utils.getDBRoot();
        filePath = dbRoot + filePath;

        try{
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = br.readLine()) != null){
                Record record = Record.fromString(line);
                if (record.id.equals(id)){
                    br.close();
                    long endTime = System.currentTimeMillis();
                    System.out.println("time taken: " + (endTime - startTime));
                    return record.toString();
                }
            }
            br.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        return "record not found";
    }

    public boolean upsert(String data, String fileLocation){
        long startTime = System.currentTimeMillis();

        String dbRoot = Utils.getDBRoot();
        fileLocation = dbRoot + fileLocation;
        String line;
        Record givenRecord = Record.fromString(data);
        String tempFilePath = "/Users/ani/AniVectorDB/index/temp.txt";

        File inputFile = new File(fileLocation);
        File tempFile = new File(tempFilePath);

        try{
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFilePath));

            boolean found = false;

            while((line = br.readLine()) !=null){
                Record record = Record.fromString(line);
                if (record.id.equals(givenRecord.id)){
                    bw.write(data);
                    found = true;
                } else {
                    bw.write(line);
                }

                bw.newLine();
            }


            if(!found){
                bw.write(data);
                bw.newLine();
            }
            br.close();
            bw.close();

            if(inputFile.delete()){
                tempFile.renameTo(inputFile);
                System.out.println("Upsert Success!");
            } else{
                System.out.println("Upsert Fail!");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        long endTime = System.currentTimeMillis();

        System.out.println("Time: " + (endTime-startTime));
        return true;
    }

    public static void main(String[] args){
        FileIO fio = new FileIO();
        // fio.insert("index/init.txt", "this is test data3");
        // inserting a proper record
        String vectorPart = "-0.2,0.5,-0.9,1.2,";

        String filePath = "/index/init.txt";

        int gen = 10000;
        for(int i=0; i<gen; i++){
            String s = Utils.generateUUID() + "|" + vectorPart;
            fio.upsert(s, filePath);
        }
        String id = "318f16d7-9dc3-4b07-a53f-325a8fb04ba4";

        String result = fio.get(id, filePath);
        System.out.println(result);

        String res = fio.parallelGet(id, filePath);
        System.out.println(res);
    }

}
