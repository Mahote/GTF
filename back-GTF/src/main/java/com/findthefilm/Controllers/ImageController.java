package com.findthefilm.Controllers;

import org.apache.catalina.connector.Response;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.findthefilm.model.Image;
import com.findthefilm.services.ImageService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("api/images")
public class ImageController {

    @Autowired
    ImageService imageService;

    @GetMapping("/{id}")
    public ResponseEntity<Image> getImage(@PathVariable String idString) {
        try{
            Image _image = imageService.getImage(idString);
            
            return new ResponseEntity<>(_image, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/add")
    public ResponseEntity<String> addImage(@RequestParam("image") Image image) {
        
        try {

            String id = imageService.addPhoto(image);
            return new ResponseEntity<>(id, HttpStatus.CREATED);
        } 
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateImage(@RequestParam String id, @RequestParam("image") MultipartFile imageFile) {

        try {
            Image newImage = imageService.getImage(id);
            newImage.setImage(new Binary(BsonBinarySubType.BINARY, imageFile.getBytes()));
            imageService.updateImage(newImage);
            return new ResponseEntity<>(newImage.getId(),HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    
}
