package com.passionproject.cookbook.services;

import com.passionproject.cookbook.models.Note;
import com.passionproject.cookbook.models.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.passionproject.cookbook.repositories.PhotoDataRepository;

import java.util.ArrayList;

@Service
public class PhotoDataService {

    private final PhotoDataRepository repo;

    @Autowired
    public PhotoDataService(PhotoDataRepository repo) {
        this.repo = repo;
    }

    public Iterable<Photo> getAllPhotos() {
        return this.repo.findAll();
    }

    public Photo getPhoto(Long id) {
        return this.repo.findById(id).orElse(null);
    }

    public Photo addPhoto(Photo photo) {
        return this.repo.save(photo);
    }

    public void deletePhoto(Long id) {
        this.repo.deleteById(id);
    }

    public Photo updatePhoto(Long id, Photo photo) {
        Photo photoToUpdate = this.repo.findById(id).orElse(null);
        photoToUpdate.setDateCooked(photo.getDateCooked());
        photoToUpdate.setAwsObjectKeyName(photo.getAwsObjectKeyName());
        photoToUpdate.setImageDescription(photo.getImageDescription());
        photoToUpdate.setRecipeId(photo.getRecipeId());
        return this.repo.save(photoToUpdate);
    }

    public Iterable<Photo> getPhotosForRecipe(Long recipeId) {
        Iterable<Photo> photos = this.repo.findAll();
        ArrayList<Photo> list = new ArrayList<>();
        for(Photo photo : photos ) {
            if (photo.getRecipeId() == recipeId) {
                list.add(photo);
            }
        }
        return list;
    }
}
