package ru.dudar.notebook.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import ru.dudar.notebook.R;
import ru.dudar.notebook.domain.NoteEntity;
import ru.dudar.notebook.domain.NotesRepo;
import ru.dudar.notebook.impl.NotesRepoImpl;
import ru.dudar.notebook.ui.NoteEditActivity;

public class NotesListActivity extends AppCompatActivity {

    static final String SET_KEY_OUT = "SET_KEY_OUT";
    public NotesRepo notesRepo = new NotesRepoImpl();
    private RecyclerView recyclerView;
    private NotesAdapter adapter = new NotesAdapter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        initToolbar();
        initNoteRepo();
        initRecycleView();




    }

    private void initRecycleView() {
        recyclerView = findViewById(R.id.recycleview);
        recyclerView .setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this::onItemClick);
        adapter.setData(notesRepo.getNotes());



    }

    private void onItemClick(NoteEntity item) {
        openNoteEditActivity();
    }

    private void initNoteRepo() {
        notesRepo.createNote(new NoteEntity(1,"Запись 1", "Содержание записи номер 1"));
        notesRepo.createNote(new NoteEntity(2,"Запись 2", "Содержание записи номер 2"));
        notesRepo.createNote(new NoteEntity(3,"Запись 3", "Содержание записи номер 3"));
        notesRepo.createNote(new NoteEntity(4,"Запись 4", "Содержание записи номер 4"));
        notesRepo.createNote(new NoteEntity(5,"Запись 5", "Содержание записи номер 5"));
        notesRepo.createNote(new NoteEntity(6,"Запись 6", "Содержание записи номер 6"));
        notesRepo.createNote(new NoteEntity(7,"Запись 7", "Содержание записи номер 7"));
        notesRepo.createNote(new NoteEntity(8,"Запись 8", "Содержание записи номер 8"));
        notesRepo.createNote(new NoteEntity(9,"Запись 9", "Содержание записи номер 9"));
        notesRepo.createNote(new NoteEntity(10,"Запись 10", "Содержание записи номер 10"));
        notesRepo.createNote(new NoteEntity(11,"Запись 11", "Содержание записи номер 11"));
        notesRepo.createNote(new NoteEntity(12,"Запись 12", "Содержание записи номер 12"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_note) {
            openNoteEditActivity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void openNoteEditActivity(){
        Intent intent = new Intent(this, NoteEditActivity.class);
        startActivity(intent);
    }
    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

}