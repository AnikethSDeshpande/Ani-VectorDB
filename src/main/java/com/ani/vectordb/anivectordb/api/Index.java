package com.ani.vectordb.anivectordb.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


//@RestController
public class Index {
    @PostMapping
    public void createIndex(){
        /**
            Takes index name, vector dimension, index owner information in request body
            Checks if the index already exist
            Creates index and updates its metadata in metadata file
         */
    }

    @PostMapping
    public void updateIndex(){
        /**
            Takes the updated index name or the updated dimension in request body
            Checks if the new index name already exists
            Updates the index name and also the metadata

            Checks if the index is empty, if yes then updates the dimensional information in metadata.
            If not, throws an error
         */
    }

    @PostMapping
    public void deleteIndex() {
        /**
            Checks if the index exists, deletes the entire index.
         */
    }

}
