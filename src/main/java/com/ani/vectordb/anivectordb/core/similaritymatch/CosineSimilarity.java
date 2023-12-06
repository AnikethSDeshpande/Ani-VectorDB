package com.ani.vectordb.anivectordb.core.similaritymatch;

import java.util.List;

public class CosineSimilarity {
    public static Double cosineSimilarity(List<Double> vector1, List<Double> vector2) {
        if (vector1.size() != vector2.size()) {
            throw new IllegalArgumentException("Vector sizes are not the same");
        }

        double dotProduct = 0.0;
        double magnitude1 = 0.0;
        double magnitude2 = 0.0;

        for (int i = 0; i < vector1.size(); i++) {
            dotProduct += vector1.get(i) * vector2.get(i);
            magnitude1 += Math.pow(vector1.get(i), 2);
            magnitude2 += Math.pow(vector2.get(i), 2);
        }

        magnitude1 = Math.sqrt(magnitude1);
        magnitude2 = Math.sqrt(magnitude2);

        if (magnitude1 == 0.0 || magnitude2 == 0.0) {
            return 0.0; // Avoid division by zero
        }

        return dotProduct / (magnitude1 * magnitude2);
    }

    public static void main(String[] args) {
        List<Double> vector1 = List.of(1.0, 2.0, 3.0);
        List<Double> vector2 = List.of(4.0, 5.0, 6.0);

        Double similarity = cosineSimilarity(vector1, vector2);
        System.out.println("Cosine Similarity: " + similarity);
    }
}
