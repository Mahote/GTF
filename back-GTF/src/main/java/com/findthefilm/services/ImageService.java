package com.findthefilm.services;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.findthefilm.model.Image;
import com.findthefilm.repositories.ImageRepository;

@Service
public class ImageService {
    
    
    @Autowired
    private ImageRepository imageRepository;

    public String addPhoto(Image image) throws IOException {
        image = imageRepository.insert(image);
        return image.getId();
    }

    public Image getImage(String id) {
        return imageRepository.findById(id).get();

    }

    public void deleteImage(String id) throws IllegalArgumentException{
        imageRepository.deleteById(id);
    }

    public String updateImage(Image newImage) throws IOException {

        Image imageSaved = imageRepository.save(newImage);

        return imageSaved.getId();
    }
    
    

}
