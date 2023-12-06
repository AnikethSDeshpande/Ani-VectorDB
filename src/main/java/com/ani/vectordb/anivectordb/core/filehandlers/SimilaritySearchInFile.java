package com.ani.vectordb.anivectordb.core.filehandlers;

import com.ani.vectordb.anivectordb.core.records.Record;
import com.ani.vectordb.anivectordb.core.resultset.MinHeapContainer;
import com.ani.vectordb.anivectordb.core.resultset.Pair;
import com.ani.vectordb.anivectordb.core.similaritymatch.CosineSimilarity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Vector;

import static com.ani.vectordb.anivectordb.core.filehandlers.Utils.getDBRoot;

public class SimilaritySearchInFile {
    private final String dbRoot;
    public SimilaritySearchInFile(){
        this.dbRoot = getDBRoot();
    }

    public MinHeapContainer getSimilarVectors(List<Double> givenVector, Integer topN, String indexPath){
        String dbRoot = Utils.getDBRoot();
        indexPath = dbRoot + indexPath;

        MinHeapContainer similarRecords = new MinHeapContainer(topN);

        try{
            BufferedReader br = new BufferedReader(new FileReader(indexPath));
            String line;
            while((line=br.readLine()) != null){
                Record record = Record.fromString(line);
                Double recordSimilarity = CosineSimilarity.cosineSimilarity(givenVector, record.vector);
                similarRecords.insert(record.id, recordSimilarity);
            }

            return similarRecords;
        } catch (Exception e){
            System.out.println("Exception during SimilaritySearchInFile: " + e.getMessage());
        }
        return similarRecords;
    }

    public static void main(String[] args){
        SimilaritySearchInFile search  = new SimilaritySearchInFile();
        Double[] doubleArray = {-1.2, 0.5, 2.0, 3.0};
        long startTime = System.currentTimeMillis();
        List<Double> doubleList = new ArrayList<>();
        for(double d: doubleArray){
            doubleList.add(d);
        }

        MinHeapContainer mhc = search.getSimilarVectors(doubleList, 5, "/index/init.txt");
        List<Pair> similarItems =  mhc.getAllItems();

        for (Pair pair : similarItems) {
            System.out.println(pair.id + ", " + pair.similarity);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken for similarity match: " + (endTime-startTime) + "ms.");
    }
}
