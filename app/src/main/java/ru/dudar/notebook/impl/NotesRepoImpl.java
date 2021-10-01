package ru.dudar.notebook.impl;

import java.util.ArrayList;
import java.util.List;

import ru.dudar.notebook.domain.NoteEntity;
import ru.dudar.notebook.domain.NotesRepo;

public class NotesRepoImpl implements NotesRepo {

    private final ArrayList<NoteEntity> cache = new ArrayList<>();
    private int counter = 0;

    @Override
    public List<NoteEntity> getNotes() {
        return cache;
    }

    @Override
    public boolean createNote(NoteEntity note) {
        note.setId(++counter);
        cache.add(note);
        return true;
    }

    @Override
    public boolean deleteNote(int id) {
        for (int i = 0; i < cache.size(); i++) {
            if (cache.get(i).getId() == id) {
                cache.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean editNote(int id, NoteEntity note) {
        deleteNote(id);
        note.setId(id);
        cache.add(note);
        return true;
    }
}
