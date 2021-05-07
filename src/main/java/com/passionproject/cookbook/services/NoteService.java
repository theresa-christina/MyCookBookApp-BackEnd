package com.passionproject.cookbook.services;

import com.passionproject.cookbook.models.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.passionproject.cookbook.repositories.NoteRepository;

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
        return this.repo.getOne(id);
    }

    public Note updateNote(Long id, Note note) {
        Note noteToUpdate = this.repo.getOne(id);
        noteToUpdate.setNoteText(note.getNoteText());
        noteToUpdate.setDateCreated(note.getDateCreated());
        return this.repo.save(noteToUpdate);
    }

    public void deleteNote(Long id) {
        this.repo.deleteById(id);
    }

    public Note addNote (Note note) {
        return this.repo.save(note);
    }


}
