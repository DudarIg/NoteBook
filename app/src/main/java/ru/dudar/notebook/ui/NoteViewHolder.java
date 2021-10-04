package ru.dudar.notebook.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;

import ru.dudar.notebook.R;
import ru.dudar.notebook.domain.NoteEntity;

public class NoteViewHolder extends RecyclerView.ViewHolder {

    public TextView titleTextView = itemView.findViewById(R.id.tvTitle);
    public TextView detailTextView = itemView.findViewById(R.id.tvDetail);
    public TextView dataTextView = itemView.findViewById(R.id.tvData);


    public NoteViewHolder(@NonNull View itemView) {
          super(itemView);

    }

}
