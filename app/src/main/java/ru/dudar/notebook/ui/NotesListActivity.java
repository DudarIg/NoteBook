package ru.dudar.notebook.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import ru.dudar.notebook.R;
import ru.dudar.notebook.domain.NoteEntity;
import ru.dudar.notebook.domain.NotesRepo;
import ru.dudar.notebook.impl.NotesRepoImpl;
import ru.dudar.notebook.ui.NoteEditActivity;

public class NotesListActivity extends AppCompatActivity implements ListFragment.Controller {

    public NotesRepo notesRepo = new NotesRepoImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        initToolbar();
       // if (savedInstanceState == null)
                    initNoteRepo();


        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.start_fragment_left, new ListFragment())
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.start_fragment, new ListFragment())
                    .commit();

        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("1", "Запущено");
    }

    public void startNoteFragment(NoteEntity item) {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.start_fragment_right, NoteFragment.newInstance(item))
                    .addToBackStack(null)
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.start_fragment, NoteFragment.newInstance(item))
                    .addToBackStack(null)
                    .commit();
        }


    }


    private void initNoteRepo() {
        notesRepo.createNote(new NoteEntity("Запись 1", "Содержание записи номер 1"));
        notesRepo.createNote(new NoteEntity("Запись 2", "Содержание записи номер 2"));
        notesRepo.createNote(new NoteEntity("Запись 3", "Содержание записи номер 3"));
        notesRepo.createNote(new NoteEntity("Запись 4", "Содержание записи номер 4"));
        notesRepo.createNote(new NoteEntity("Запись 5", "Содержание записи номер 5"));
        notesRepo.createNote(new NoteEntity("Запись 6", "Содержание записи номер 6"));
        notesRepo.createNote(new NoteEntity("Запись 7", "Содержание записи номер 7"));
        notesRepo.createNote(new NoteEntity("Запись 8", "Содержание записи номер 8"));
        notesRepo.createNote(new NoteEntity("Запись 9", "Содержание записи номер 9"));
        notesRepo.createNote(new NoteEntity("Запись 10", "Содержание записи номер 10"));
        notesRepo.createNote(new NoteEntity("Запись 11", "Содержание записи номер 11"));
        notesRepo.createNote(new NoteEntity("Запись 12", "Содержание записи номер 12"));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_note) {
            startNoteFragment(null);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    public void openNoteEditActivity(NoteEntity item) {
//        Intent intent = new Intent(this, NoteEditActivity.class);
//        intent.putExtra(NoteEditActivity.SET_KEY_IN, item);
//        startActivityForResult(intent, REQUEST_CODE);
//    }



//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
//            resultNote = (NoteEntity) data.getSerializableExtra(NoteEditActivity.SET_KEY_OUT);
//            if (resultNote.getId() != -1) {
//                notesRepo.editNote(resultNote.getId(), resultNote);
//            }
//            if (resultNote.getId() == -1) {
//                notesRepo.createNote(resultNote);
//            } else {
//                super.onActivityResult(requestCode, resultCode, data);
//            }
//            adapter.notifyDataSetChanged();
//
//        }
//    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}