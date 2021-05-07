package com.passionproject.cookbook.services;

import com.passionproject.cookbook.models.Note;
import com.passionproject.cookbook.models.Photo;
import com.passionproject.cookbook.models.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.passionproject.cookbook.repositories.RecipeRepository;

@Service
public class RecipeService {

    private final RecipeRepository repo;

    @Autowired
    public RecipeService(RecipeRepository repo) {
        this.repo = repo;
    }

    public Recipe addRecipe(Recipe recipe){
        return this.repo.save(recipe);
    }

    public Iterable<Recipe> getAllRecipes() {
        return this.repo.findAll();
    }

    public Recipe getRecipeById(Long id) {
        return this.repo.getOne(id);
    }

    public void deleteRecipe(Long id) {
        this.repo.deleteById(id);
    }

    public Iterable<Note> getNotesForRecipe(Long id) {
        return this.repo.getOne(id).getNotes();
    }

    public Iterable<Photo> getPhotosForRecipe(Long id) {
        return this.repo.getOne(id).getPhotos();
    }

    public Recipe updateRecipe(Long id, Recipe recipe) {
        Recipe recipeToUpdate = this.repo.getOne(id);
        recipeToUpdate.setRecipeName(recipe.getRecipeName());
        recipeToUpdate.setPreparationSteps(recipe.getPreparationSteps());
        recipeToUpdate.setIngredientsList(recipe.getIngredientsList());
        recipeToUpdate.setNumberServings(recipe.getNumberServings());
        return this.repo.save(recipeToUpdate);
    }

}
