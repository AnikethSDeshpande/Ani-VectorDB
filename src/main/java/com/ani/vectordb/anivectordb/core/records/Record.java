package com.ani.vectordb.anivectordb.core.records;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Record {
    public String id;
    public List<Double> vector;

    public Record(String id, List<Double> vector){
        this.id = id;
        this.vector = vector;
    }

    @Override
    public String toString(){
        String str = this.id + "|";
        for(Double d: this.vector){
            str += d.toString() + ",";
        }
        return str;
    }

    public static Record fromString(String str){
        String[] parts = str.split("\\|");
        String id = parts[0];
        String[] vectorStringElements = parts[1].split(",");

        List<Double> doubleList = new ArrayList<>();
        for(String i: vectorStringElements){
            doubleList.add(Double.parseDouble(i));
        }

        return new Record(id, doubleList);
    }

    public static void main(String args[]){
        Double[] doubleArray = {-1.2, 0.5, 2.0, 3.0};
        List<Double> doubleList = new ArrayList<>();
        for(double d: doubleArray){
            doubleList.add(d);
        }
        Record record = new Record("id1", doubleList);
        String rec = record.toString();
        System.out.println("rec=" + rec);

        Record derived = Record.fromString(rec);
        System.out.println(derived);

    }
}
