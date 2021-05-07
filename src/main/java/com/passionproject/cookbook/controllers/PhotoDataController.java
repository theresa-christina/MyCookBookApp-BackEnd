package com.passionproject.cookbook.controllers;

import com.passionproject.cookbook.models.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.passionproject.cookbook.services.PhotoDataService;

@RestController
@RequestMapping("/photo")
@CrossOrigin
public class PhotoDataController {

    private final PhotoDataService svc;

    @Autowired
    public PhotoDataController(PhotoDataService svc) {
        this.svc = svc;
    }

    @GetMapping("/{photoDataId}")
    public ResponseEntity<Photo> findPhotoById(@PathVariable Long photoDataId) {
        return new ResponseEntity<>(this.svc.getPhoto(photoDataId), HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<Photo> addNewPhoto(@RequestBody Photo photo) {
        return new ResponseEntity<>(this.svc.addPhoto(photo), HttpStatus.CREATED);
    }

    @DeleteMapping("/{photoDataId}")
    public Boolean deletePhotoById(@PathVariable Long photoDataId) {
        this.svc.deletePhoto(photoDataId);
        return this.svc.getPhoto(photoDataId) == null;
    }

    @PutMapping("/{photoDataId}")
    public ResponseEntity<Photo> updatePhotoById(@PathVariable Long photoDataId, @RequestBody Photo photo) {
        return new ResponseEntity<>(this.svc.updatePhoto(photoDataId, photo), HttpStatus.OK);
    }
}
