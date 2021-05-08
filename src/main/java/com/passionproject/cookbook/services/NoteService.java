package com.passionproject.cookbook.services;

import com.passionproject.cookbook.models.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.passionproject.cookbook.repositories.NoteRepository;

import java.util.ArrayList;

@Service
public class NoteService {

    private final NoteRepository repo;

    @Autowired
    public NoteService(NoteRepository repo) {
        this.repo = repo;
    }

    public Iterable<Note> getAllNotes() {
        return this.repo.findAll();
    }

    public Note getNoteById(Long id) {
        return this.repo.findById(id).orElse(null);
    }

    public Note updateNote(Long id, Note note) {
        Note noteToUpdate = this.repo.findById(id).orElse(null);
        noteToUpdate.setNoteText(note.getNoteText());
        noteToUpdate.setDateCreated(note.getDateCreated());
        noteToUpdate.setRecipeId(note.getRecipeId());
        return this.repo.save(noteToUpdate);
    }

    public void deleteNote(Long id) {
        this.repo.deleteById(id);
    }

    public Note addNote (Note note) {
        return this.repo.save(note);
    }

    public Iterable<Note> getNotesForRecipe(Long recipeId) {
        Iterable<Note> notes = this.repo.findAll();
        ArrayList<Note> list = new ArrayList<>();
        for(Note note : notes ) {
            if (note.getRecipeId() == recipeId) {
                list.add(note);
            }
        }
        return list;
    }


}
