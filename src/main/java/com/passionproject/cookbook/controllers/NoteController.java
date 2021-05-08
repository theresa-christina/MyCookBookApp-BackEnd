package com.passionproject.cookbook.controllers;

import com.passionproject.cookbook.models.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.passionproject.cookbook.services.NoteService;

@RestController
@RequestMapping("/note")
@CrossOrigin
public class NoteController {

    private final NoteService svc;

    @Autowired
    public NoteController(NoteService svc) {
        this.svc = svc;
    }

    @GetMapping
    public ResponseEntity<Iterable<Note>> findAllNotes() {
        return new ResponseEntity<>(this.svc.getAllNotes(), HttpStatus.OK);
    }

    @GetMapping("/{noteId}")
    public ResponseEntity<Note> findNoteById(@PathVariable Long noteId) {
        return new ResponseEntity<>(this.svc.getNoteById(noteId), HttpStatus.OK);
    }

    @PutMapping("/{noteId}")
    public ResponseEntity<Note> updateNoteById(@PathVariable Long noteId, @RequestBody Note note) {
        return new ResponseEntity<>(this.svc.updateNote(noteId, note), HttpStatus.OK);
    }

    @DeleteMapping("/{noteId}")
    public Boolean deleteNoteById(@PathVariable Long noteId) {
        this.svc.deleteNote(noteId);
        return this.svc.getNoteById(noteId) == null;
    }

    @PostMapping
    public ResponseEntity<Note> addNewNote(@RequestBody Note note) {
        return new ResponseEntity<>(this.svc.addNote(note), HttpStatus.OK);
    }

    @GetMapping("/recipe/{recipeId}")
    public ResponseEntity<Iterable<Note>> findAllNotesByRecipeId(@PathVariable Long recipeId) {
        return new ResponseEntity<>(this.svc.getNotesForRecipe(recipeId), HttpStatus.OK);
    }
}
