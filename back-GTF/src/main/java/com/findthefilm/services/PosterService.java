package com.findthefilm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.findthefilm.model.Poster;
import com.findthefilm.repositories.PosterRepository;

@Service
public class PosterService {
    
    @Autowired
    private PosterRepository posterRepository;


    public List<Poster> getPosters(){
        return posterRepository.findAll();
    }

    public Poster getPoster(String posterId) {
        return posterRepository.findById(posterId).get();
    }
    public String addPoster(Poster poster) {
        Poster posterSaved = posterRepository.insert(poster);
        
        return posterSaved.getId();
    }

    public void deletePoster(String id){
        posterRepository.deleteById(id);
    }

    public Poster updatePoster(Poster newPoster) {
        Poster posterUpdated = posterRepository.save(newPoster);

        return posterUpdated;
    }

    
}
