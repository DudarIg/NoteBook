package ru.dudar.notebook.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.dudar.notebook.R;

public class NoteViewHolder extends RecyclerView.ViewHolder {

    public TextView titleTextView = itemView.findViewById(R.id.tvTitle);
    public TextView detailTextView = itemView.findViewById(R.id.tvDetail);

    public NoteViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, NoteEditActivity.class);
            context.startActivity(intent);

        });
    }




}
