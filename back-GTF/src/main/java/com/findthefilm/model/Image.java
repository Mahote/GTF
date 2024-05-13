package com.findthefilm.model;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

import nonapi.io.github.classgraph.json.Id;


@Document(collection = "posterImages")
public class Image {

    @Id
    private String id;

    private Binary image;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public Binary getImage() {
        return image;
    }

    public void setImage(Binary image) {
        this.image = image;
    }
}
