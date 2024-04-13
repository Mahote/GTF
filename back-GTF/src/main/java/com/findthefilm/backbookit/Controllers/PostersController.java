package com.findthefilm.backbookit.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findthefilm.backbookit.model.Poster;
import com.findthefilm.backbookit.repositories.PostersRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
public class PostersController {

    @Autowired
    PostersRepository postersRepository;

    @GetMapping("/posters")
    public ResponseEntity<List<Poster>> getAllPosters(@RequestParam(required = false) String name) {
        try {
            List<Poster> posters = new ArrayList<Poster>();

            if (name == null)
                    postersRepository.findAll().forEach(posters::add);
            else
                    postersRepository.findByName(name).forEach(posters::add);
            if (posters.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
                return new ResponseEntity<>(posters, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping("/posters/{id}") 
    public ResponseEntity<Poster> getTutorialById(@PathVariable("id") String id) {
        Optional<Poster> posterData = postersRepository.findById(id);

        if (posterData.isPresent()) {
            return new ResponseEntity<>(posterData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/posters")
    public ResponseEntity<List<Poster>> createPoster(@RequestBody List<Poster> posters) {
        List<Poster> createdPosters = new ArrayList<>();
        for (Poster poster : posters) {
            try {
                Poster createdPoster = postersRepository.save(new Poster(poster.getName(), poster.getPostersImagesUrls()));
                createdPosters.add(createdPoster);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        if (createdPosters.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(createdPosters, HttpStatus.CREATED);
        }
    }

    @PutMapping("/posters/{id}")
    public ResponseEntity<Poster> updatePoster(@PathVariable("id") String id, @RequestBody Poster poster) {
        
        Optional<Poster> posterData = postersRepository.findById(id);

        if (posterData.isPresent()) {
            Poster _poster = posterData.get();
            _poster.setName(poster.getName());
            _poster.setPostersImagesUrl(poster.getPostersImagesUrls());
            return new ResponseEntity<>(postersRepository.save(_poster), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/posters/{id}")
    public ResponseEntity<HttpStatus> deletePoster(@PathVariable("id") String id) {
        try {
            postersRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
