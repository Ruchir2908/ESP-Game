package com.example.esp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {

    TextView textView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.textView);
    }

    public void bindData(Task task, String playerId, int taskId) {

        if (playerId.equals(task.getFirstPlayer())) {
            textView.setText("Task #"+taskId+" paired with "+task.getSecondPlayer());
        } else {
            textView.setText("Task #"+taskId+" paired with "+task.getFirstPlayer());
        }

    }


}
