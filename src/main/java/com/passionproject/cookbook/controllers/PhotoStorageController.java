package com.passionproject.cookbook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.passionproject.cookbook.services.PhotoStorageService;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/photo-storage")
@CrossOrigin
public class PhotoStorageController {

    private final PhotoStorageService svc;

    @Autowired
    public PhotoStorageController(PhotoStorageService svc) {
        this.svc = svc;
    }

    @PostMapping("/upload")
    public ResponseEntity<Long> uploadPhoto(@RequestParam(value = "file") MultipartFile file) {
        return new ResponseEntity<>(this.svc.uploadPhoto(file), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{filename}")
    public ResponseEntity<Long> deleteFile (@PathVariable String filename) {
        return new ResponseEntity<>(this.svc.deleteFile(filename), HttpStatus.OK);
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<ByteArrayResource> getPhotoByName(@PathVariable String filename) {
        byte[] data = this.svc.downloadPhotoByName(filename);
        ByteArrayResource resource = new ByteArrayResource(data);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<ByteArrayResource> getPhotoByKey(@PathVariable String keyName) {
        byte[] data = this.svc.downloadPhotoByKey(keyName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }
}
