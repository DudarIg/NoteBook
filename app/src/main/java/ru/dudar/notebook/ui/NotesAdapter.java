package ru.dudar.notebook.ui;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ru.dudar.notebook.R;
import ru.dudar.notebook.domain.NoteEntity;

public class NotesAdapter extends RecyclerView.Adapter<NoteViewHolder> {

    private List<NoteEntity> data = new ArrayList<>();
    private OnItemClickListener clickListener = null;
    private  OnItemLongClickListener clickLongListener = null;

    public void setData(List<NoteEntity> data) {
        this.data = data;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        NoteEntity note = data.get(position);
        holder.titleTextView.setText(note.getTitle());
        holder.detailTextView.setText(note.getDetail());
        holder.dataTextView.setText(note.getData());

        holder.itemView.setOnClickListener(view -> {
            clickListener.onItemClick(note);
        });
        holder.itemView.setOnLongClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
            popupMenu.inflate(R.menu.popup_menu);
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                clickLongListener.onItemLongClick(note);
                return true;
            });
            popupMenu.show();
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        clickListener = listener;
    }
    public void setOnItemLongClickListener(OnItemLongClickListener listenerLong) {
        clickLongListener = listenerLong;
    }

    interface OnItemClickListener {
        void onItemClick(NoteEntity item);
    }

    interface OnItemLongClickListener {
        void onItemLongClick(NoteEntity item);
    }
}
