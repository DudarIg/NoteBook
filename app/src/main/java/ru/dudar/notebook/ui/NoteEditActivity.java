package ru.dudar.notebook.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import ru.dudar.notebook.R;
import ru.dudar.notebook.domain.NoteEntity;

public class NoteEditActivity extends AppCompatActivity {
    private EditText titleEditText;
    private EditText detailEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);

        titleEditText = findViewById(R.id.title_edit_text);
        detailEditText = findViewById(R.id.delail_edit_text);
        saveButton = findViewById(R.id.save_button);

        saveButton.setOnClickListener(view -> {
            NoteEntity noteEntity = new NoteEntity(
                    titleEditText.getText().toString(),
                    detailEditText.getText().toString());

        });

    }
}