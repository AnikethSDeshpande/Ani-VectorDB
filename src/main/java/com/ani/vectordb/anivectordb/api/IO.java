package com.ani.vectordb.anivectordb.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
public class IO {

    @PostMapping
    public void upsert(){
        /*
            takes the index name, id and vector in request body.
            saves/upserts data in the index
        */
    }

    @GetMapping
    public void findSimilar(){
        /*
            takes index name, id and top n matching in request body
            fetches the data and returns the vector
        */
    }

    @PostMapping
    public void delete(){
        /*
            takes the index name and id
            deletes data from the index
        */
    }
}
