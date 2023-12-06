package com.ani.vectordb.anivectordb.core.resultset;

import com.ani.vectordb.anivectordb.core.records.Record;

import java.util.*;

import java.util.PriorityQueue;


public class MinHeapContainer {
    private PriorityQueue<Pair> minHeap;
    private int maxSize;

    public MinHeapContainer(int maxSize) {
        this.minHeap = new PriorityQueue<>((a, b) -> Double.compare(a.similarity, b.similarity));
        this.maxSize = maxSize;
    }

    public void insert(String id, Double similarity) {
        Pair pair = new Pair(id, similarity);
        if (minHeap.size() < maxSize) {
            minHeap.offer(pair);
        } else {
            Pair currentMin = minHeap.peek();
            if (similarity > currentMin.similarity) {
                minHeap.poll();
                minHeap.offer(pair);
            }
        }
    }

    public Pair getMin() {
        if (minHeap.isEmpty()) {
            throw new IllegalStateException("The heap is empty.");
        }
        return minHeap.peek();
    }

    public Pair removeMin() {
        if (minHeap.isEmpty()) {
            throw new IllegalStateException("The heap is empty.");
        }
        return minHeap.poll();
    }

    public List<Pair> getAllItems() {
        List<Pair> ar = new ArrayList<>();
        while(!minHeap.isEmpty()){
            ar.add(minHeap.poll());
        }
        Collections.sort(ar, Comparator.reverseOrder());
        return ar;
    }

    public static void main(String[] args) {
        MinHeapContainer heap = new MinHeapContainer(3);

        heap.insert("A", 0.5);
        heap.insert("B", 2.0);
        heap.insert("C", 9.1);
        heap.insert("D", 0.1);
        heap.insert("E", 0.6);

        Pair minPair = heap.getMin();
        System.out.println("Minimum pair: " + minPair.id + ", " + minPair.similarity);

        Pair removedMin = heap.removeMin();
        System.out.println("Removed minimum pair: " + removedMin.id + ", " + removedMin.similarity);

        minPair = heap.getMin();
        System.out.println("New minimum pair: " + minPair.id + ", " + minPair.similarity);
    }
}

