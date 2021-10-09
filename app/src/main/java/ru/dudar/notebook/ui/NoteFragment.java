package ru.dudar.notebook.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import ru.dudar.notebook.R;
import ru.dudar.notebook.domain.NoteEntity;

public class NoteFragment extends Fragment  {

    private static final String ARG_PARAM = "param";

    private EditText titleEditText;
    private EditText detailEditText;
    private Button saveButton;
    private int dataId = -1;
    private NoteEntity setData;


    public static NoteFragment newInstance(NoteEntity tempData) {
        NoteFragment noteFragment = new NoteFragment();
        Bundle bindle = new Bundle();
        bindle.putSerializable(ARG_PARAM, tempData);
        noteFragment.setArguments(bindle);
        return noteFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_note_edit, container, false);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            setData  = (NoteEntity) getArguments().getSerializable(ARG_PARAM);
        }
        titleEditText = view.findViewById(R.id.title_edit_text);
        detailEditText = view.findViewById(R.id.delail_edit_text);
        saveButton = view.findViewById(R.id.save_button);
        if (setData != null) {
            dataId = setData.getId();
            titleEditText.setText(setData.getTitle());
            detailEditText.setText(setData.getDetail());
        }

        saveButton.setOnClickListener(v -> {
            NoteEntity resultNote = new NoteEntity(
                    titleEditText.getText().toString(),
                    detailEditText.getText().toString());
            resultNote.setId(dataId);

            if (resultNote.getId() != -1) {
                ((NotesListActivity) getActivity()).notesRepo.editNote(resultNote.getId(), resultNote);
            }
            if (resultNote.getId() == -1) {
                ((NotesListActivity) getActivity()).notesRepo.createNote(resultNote);
                ((NotesListActivity) getActivity()).openListFr();
            }


        });
    }



}