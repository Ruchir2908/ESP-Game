package com.example.esp;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder {

    TextView textView;
    ImageView imageView1, imageView2, imageView3, imageView4, imageView5;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.textView);
        imageView1 = itemView.findViewById(R.id.image1);
        imageView2 = itemView.findViewById(R.id.image2);
        imageView3 = itemView.findViewById(R.id.image3);
        imageView4 = itemView.findViewById(R.id.image4);
        imageView5 = itemView.findViewById(R.id.image5);
    }

    public void bindData(Task task, String playerId, int taskId) {

        if (playerId.equals(task.getFirstPlayer())) {
            textView.setText("Task: "+taskId);
        } else {
            textView.setText("Task: "+taskId);
        }

//        Log.i("RUCHIR", task.ques.get(0)+" "+task.ques.get(0).toString()+" "+task.ques.get(0).toString().substring(0,1));


//        Picasso.get().load(RandomData.getImage(Integer.parseInt(task.ques.get(0).toString().substring(0,1)))).resize(400,550).centerCrop().into(imageView1);
//        Picasso.get().load(RandomData.getImage(task.ques.get(1))).resize(400,550).centerCrop().into(imageView2);
//        Picasso.get().load(RandomData.getImage(task.ques.get(2))).resize(400,550).centerCrop().into(imageView3);
//        Picasso.get().load(RandomData.getImage(task.ques.get(3))).resize(400,550).centerCrop().into(imageView4);
//        Picasso.get().load(RandomData.getImage(task.ques.get(4))).resize(400,550).centerCrop().into(imageView5);

    }


}
