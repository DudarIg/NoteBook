package ru.dudar.notebook.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.Serializable;
import java.util.List;

import ru.dudar.notebook.R;
import ru.dudar.notebook.domain.NoteEntity;
import ru.dudar.notebook.domain.NotesRepo;
import ru.dudar.notebook.impl.NotesRepoImpl;


public class NotesListActivity extends AppCompatActivity implements ListFragment.Controller {


    private BottomNavigationView bottomMenuView;

    public NotesRepo notesRepo = new NotesRepoImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        initToolbar();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            initBottomMenu();
        }

        if (savedInstanceState == null) {
            initNotesRepo();
        } else {
            notesRepo = (NotesRepo) savedInstanceState.getSerializable("NOTESREPO");
        }

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            openListFr();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.start_fragment, new ListFragment())
                    .commit();
        }
    }
    public  void openListFr() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.start_fragment_left, new ListFragment())
                .commit();


        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.start_fragment_right, NoteFragment.newInstance(null))
                    .addToBackStack(null)
                    .commit();
        }

    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStackImmediate();
        } else {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.exit)
                    .setMessage(R.string.exit_note)
                    .setNegativeButton(android.R.string.no, null)
                    .setPositiveButton(android.R.string.yes, (arg0, arg1) -> NotesListActivity.super.onBackPressed()).create().show();
        }
    }

    private void initBottomMenu() {
        bottomMenuView = findViewById(R.id.bottom_nav_menu);
        bottomMenuView.setOnItemSelectedListener(item -> {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.as_programm: {
                    fragment = new ProgrammFragment();
                    break;
                }
                case R.id.one_fragment: {
                    fragment = new ListFragment();
                    break;
                }
                case R.id.users_fragment: {
                    fragment = new UsersFragment();
                    break;
                }
                default: {
                    fragment = new ListFragment();
                }
            }
            transaction.replace(R.id.start_fragment, fragment);
            transaction.commit();
            return true;
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("NOTESREPO", (Serializable) notesRepo);

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

    private void initNotesRepo() {
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

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}