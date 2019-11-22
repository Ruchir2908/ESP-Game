package com.example.esp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (position < 0 || position >= list.size()) return;

        ViewHolder viewHolder = (ViewHolder) holder;
        Task task = list.get(position);

        viewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("docId", list.get(position).getDocId());
                TaskFragment taskFragment = new TaskFragment();
                taskFragment.setArguments(bundle);
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.container,taskFragment).addToBackStack(null).commit();
            }
        });
        viewHolder.bindData(task, playerId, position+1);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
