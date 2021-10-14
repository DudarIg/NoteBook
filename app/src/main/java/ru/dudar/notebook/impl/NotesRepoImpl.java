package ru.dudar.notebook.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.text.SimpleDateFormat;

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
        note.setData(getCurrentTime());
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
        note.setData(getCurrentTime());
        cache.add(note);
        return true;
    }

    public String getCurrentTime() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy kk:mm", Locale.getDefault());
        return formatter.format(date);

    }
}
