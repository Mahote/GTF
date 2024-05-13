package com.findthefilm.model;

import java.util.ArrayList;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "posters")
public class Poster {


    @Id 
    private String id;

    private String title;

    private ArrayList<Image> posterImages;


    public String getId() {
        return id;
    }
    

    public String getTitle() {
        return this.title;
    }


    public ArrayList<Image> getPosterImages () {
        return this.posterImages;
    }

}
