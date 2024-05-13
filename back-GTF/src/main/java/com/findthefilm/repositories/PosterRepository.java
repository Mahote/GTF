package com.findthefilm.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.findthefilm.model.Poster;

public interface PosterRepository extends MongoRepository<Poster, String> {

    List<Poster> findByName(String name);
  
}