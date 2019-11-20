package com.example.esp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;


public class TaskFragment extends Fragment {

    public TaskFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task, container, false);

        ImageView primaryImageView, secondaryImageView;
        primaryImageView = view.findViewById(R.id.primaryImageView);
//        secondaryImageView = view.findViewById(R.id.secondaryImageView);

        Random random = new Random();
        primaryImageView.setImageResource(MainActivity.primaryImages.get((random.nextInt(10))));

//        secondaryImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Toast.makeText(getActivity().getApplicationContext(), "RG", Toast.LENGTH_SHORT).show();
//            }
//        });

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public interface OnFragmentInteractionListener {
    }
}
