package com.findthefilm.backbookit.model;

import java.util.ArrayList;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "posters")
public class Poster {


    @Id 
    private String id;

    private String name;

    private ArrayList<String> postersImagesUrls;

    public Poster(String name, ArrayList<String> postersImagesUrls) {
        this.name = name;
        this.postersImagesUrls = postersImagesUrls;
    }

    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getPostersImagesUrls() {
        return postersImagesUrls;
    }

    public void setPostersImagesUrl(ArrayList<String> postersImagesUrls) {
        this.postersImagesUrls = postersImagesUrls;
    }
    

}
