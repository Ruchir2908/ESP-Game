package com.example.esp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Task> list;
    private String playerId;
    private Context context;

    public Adapter(ArrayList<Task> list, String playerId) {
        this.list = list;
        this.playerId = playerId;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        this.context = parent.getContext();

        View view = LayoutInflater.from(context).inflate(R.layout.row_layout, null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position < 0 || position >= list.size()) return;

        ViewHolder customViewHolder = (ViewHolder) holder;
        Task task = list.get(position);
        customViewHolder.bindData(task, playerId, position+1);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
