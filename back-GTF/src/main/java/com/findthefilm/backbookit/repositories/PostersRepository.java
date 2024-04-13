package com.findthefilm.backbookit.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.findthefilm.backbookit.model.Poster;

public interface PostersRepository extends MongoRepository<Poster, String> {

    List<Poster> findByName(String name);
  
}