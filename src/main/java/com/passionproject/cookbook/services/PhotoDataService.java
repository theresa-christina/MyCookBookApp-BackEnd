package com.passionproject.cookbook.services;

import com.passionproject.cookbook.models.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.passionproject.cookbook.repositories.PhotoDataRepository;

@Service
public class PhotoDataService {

    private final PhotoDataRepository repo;

    @Autowired
    public PhotoDataService(PhotoDataRepository repo) {
        this.repo = repo;
    }

    public Photo getPhoto(Long id) {
        return this.repo.getOne(id);
    }

    public Photo addPhoto(Photo photo) {
        return this.repo.save(photo);
    }

    public void deletePhoto(Long id) {
        this.repo.deleteById(id);
    }

    public Photo updatePhoto(Long id, Photo photo) {
        Photo photoToUpdate = this.repo.getOne(id);
        photoToUpdate.setDateCooked(photo.getDateCooked());
        photoToUpdate.setAwsObjectKeyName(photo.getAwsObjectKeyName());
        photoToUpdate.setRecipe(photo.getRecipe());
        photoToUpdate.setImageDescription(photo.getImageDescription());
        return this.repo.save(photoToUpdate);
    }
}
