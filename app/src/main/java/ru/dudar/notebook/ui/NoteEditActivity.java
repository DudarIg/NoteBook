package ru.dudar.notebook.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;

import ru.dudar.notebook.R;
import ru.dudar.notebook.domain.NoteEntity;
import ru.dudar.notebook.domain.NotesRepo;
import ru.dudar.notebook.impl.NotesRepoImpl;

public class NoteEditActivity extends AppCompatActivity {
    static final String SET_KEY_IN = "SET_KEY_IN";


    private EditText titleEditText;
    private EditText detailEditText;
    private Button saveButton;
    private int dataId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);

        titleEditText = findViewById(R.id.title_edit_text);
        detailEditText = findViewById(R.id.delail_edit_text);
        saveButton = findViewById(R.id.save_button);

       // loadIntent();


        saveButton.setOnClickListener(view -> {
            NoteEntity noteEntity = new NoteEntity(dataId,
                    titleEditText.getText().toString(),
                    detailEditText.getText().toString());
//            NotesListActivity.
//            Intent intent = new Intent(this, NotesListActivity.class);
//            intent.putExtra(NotesListActivity.SET_KEY_OUT, noteEntity);
//            startActivity(intent);
//            finish();

        });

    }

    private void loadIntent() {
        Intent data = getIntent();
        if (data.getSerializableExtra(SET_KEY_IN) != null) {
            NoteEntity setData = (NoteEntity) data.getSerializableExtra(SET_KEY_IN);
            dataId = setData.getId();
            titleEditText.setText(setData.getTitle());
            detailEditText.setText(setData.getDetail());
        }
    }
}