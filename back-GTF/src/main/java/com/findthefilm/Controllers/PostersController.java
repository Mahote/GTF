package com.findthefilm.Controllers;

import java.util.List;
import java.util.Random;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.findthefilm.model.Poster;
import com.findthefilm.services.PosterService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/posters")
public class PostersController {

    @Autowired
    PosterService posterService;

    @GetMapping("/")
    public ResponseEntity<List<Poster>> getAllPosters() {
        try {

            return new ResponseEntity<>(posterService.getPosters(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{id}") 
    public ResponseEntity<Poster> getPosterById(@PathVariable("id") String id) {
        
        try {
            Poster poster = posterService.getPoster(id);
            return new ResponseEntity<>(poster, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public ResponseEntity<String> createPoster(@RequestBody Poster poster) {
        
        try {
            String posterId = posterService.addPoster(poster);
            return new ResponseEntity<>(posterId, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Poster> updatePoster(@RequestBody Poster newPoster) {
        

        try {
            Poster poster = posterService.updatePoster(newPoster);
            return new ResponseEntity<>(poster, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePoster(@PathVariable("id") String id) {
        try {
            posterService.deletePoster(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/random")
    public ResponseEntity<Poster> getRandomFilmPoster() {
        try {
            List<Poster> posters = posterService.getPosters();
            Random random = new Random();
            Poster poster = posters.get(random.nextInt(posters.size()));
            return new ResponseEntity<>(poster, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
