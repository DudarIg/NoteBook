package ru.dudar.notebook.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.dudar.notebook.R;
import ru.dudar.notebook.domain.NoteEntity;


public class ListFragmentJava extends Fragment {

    private RecyclerView recyclerView;
    private NotesAdapter adapter = new NotesAdapter();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null)
            requireActivity().getSupportFragmentManager().popBackStack();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_notes_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState == null)
             initRecycleView(view);
        else
            adapter.notifyDataSetChanged();
    }

    private void initRecycleView(View view) {
        recyclerView = view.findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this::onItemClick);
        adapter.setOnItemLongClickListener(this::onItemLongClick);
        adapter.setData(((NotesListActivity) getActivity()).notesRepo.getNotes());
    }

    private void onItemClick(NoteEntity item) {
        openNoteFragment(item);
    }

    public void onItemLongClick(NoteEntity item) {
        ((NotesListActivity) getActivity()).notesRepo.deleteNote(item.getId());
        adapter.notifyDataSetChanged();
    }

    public void openNoteFragment(NoteEntity item) {
        ((Controller) requireActivity()).startNoteFragment(item);
    }

    interface Controller {
        void startNoteFragment(NoteEntity item);
    }

}