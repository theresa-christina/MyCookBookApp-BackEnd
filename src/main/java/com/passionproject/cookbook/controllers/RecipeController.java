package com.passionproject.cookbook.controllers;

import com.passionproject.cookbook.models.Note;
import com.passionproject.cookbook.models.Photo;
import com.passionproject.cookbook.models.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.passionproject.cookbook.services.RecipeService;

@RestController
@RequestMapping("/recipe")
@CrossOrigin
public class RecipeController {

    private final RecipeService svc;

    @Autowired
    public RecipeController(RecipeService svc) {
        this.svc = svc;
    }

    @PostMapping
    public ResponseEntity<Recipe> addNewRecipe(@RequestBody Recipe recipe) {
        return new ResponseEntity<>(this.svc.addRecipe(recipe), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Iterable<Recipe>> findAllRecipes() {
        return new ResponseEntity<>(this.svc.getAllRecipes(), HttpStatus.OK);
    }

    @GetMapping("/{recipeId}")
    public ResponseEntity<Recipe> findRecipeById(@PathVariable Long recipeId) {
        return new ResponseEntity<>(this.svc.getRecipeById(recipeId), HttpStatus.OK);
    }

    @DeleteMapping("/{recipeId}")
    public Boolean deleteRecipeById(@PathVariable Long recipeId) {
        this.svc.deleteRecipe(recipeId);
        return this.svc.getRecipeById(recipeId) == null;
    }

    @PutMapping("/{recipeId}")
    public ResponseEntity<Recipe> updateRecipeById(@PathVariable Long recipeId, @RequestBody Recipe recipe) {
        return new ResponseEntity<>(this.svc.updateRecipe(recipeId, recipe), HttpStatus.OK);
    }

}
