package ru.dudar.notebook.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.dudar.notebook.R;
import ru.dudar.notebook.domain.NoteEntity;

public class NotesAdapter extends RecyclerView.Adapter<NoteViewHolder> {

    private List<NoteEntity> data = new ArrayList<>();

    public void setData(List<NoteEntity> data) {
        this.data = data;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        NoteEntity note = data.get(position);
        holder.titleTextView.setText(note.getTitle());
        holder.detailTextView.setText(note.getDetail());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

//    public void setOnItemClickListener(OnItemClickListener onItemClick) {
//        //todo
//    }
//    interface OnItemClickListener {
//        void onItemClick(NoteEntity item);
//    }
}
