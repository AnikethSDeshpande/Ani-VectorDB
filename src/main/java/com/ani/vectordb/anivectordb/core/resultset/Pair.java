package com.ani.vectordb.anivectordb.core.resultset;

import java.util.Collections;

public class Pair implements Comparable<Pair>{
    public String id;
    public Double similarity;

    public Pair(String id, Double similarity) {
        this.id = id;
        this.similarity = similarity;
    }


    @Override
    public int compareTo(Pair other) {
        return Double.compare(this.similarity, other.similarity);
    }
}