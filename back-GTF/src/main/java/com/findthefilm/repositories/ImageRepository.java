package com.findthefilm.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.findthefilm.model.Image;

public interface ImageRepository extends MongoRepository<Image, String>{
    
}
