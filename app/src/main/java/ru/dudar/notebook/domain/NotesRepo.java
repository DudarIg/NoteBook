package ru.dudar.notebook.domain;

import java.util.List;

public interface NotesRepo {
    List<NoteEntity> getNotes();
    boolean createNote(NoteEntity note);
    boolean deleteNote(int id);
    boolean editNote(int id, NoteEntity note);
}
