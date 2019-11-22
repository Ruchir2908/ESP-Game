package com.example.esp;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class TaskFragment extends Fragment {

    String docID;
    Task currentTask;
    ImageView primaryImageView, sec1ImageView, sec2ImageView;
    Button submitButton;

    FirebaseFirestore root = FirebaseFirestore.getInstance();
    CollectionReference allPlayers = root.collection("Players");
    CollectionReference allTasks = root.collection("Tasks");

    private ArrayList<Primary> questions = RandomData.getPrimaryImages();
    List<String> indices;
    private int[] responses = new int[] {-1, -1, -1, -1, -1};
    private int questionNum = 0;

    DocumentReference docRef;

    public TaskFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        docID = getArguments().getString("docId");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task, container, false);

        primaryImageView = view.findViewById(R.id.primaryImageView);
        sec1ImageView = view.findViewById(R.id.sec1ImageView);
        sec2ImageView = view.findViewById(R.id.sec2ImageView);
        submitButton = view.findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(responses[questionNum] != -1){
                    questionNum++;
                    if(questionNum <indices.size()){
                        Primary question = questions.get(Integer.parseInt(indices.get(questionNum)));
                        Picasso.get().load(question.question).resize(400,550).centerCrop().into(primaryImageView);
                        Picasso.get().load(question.options.getAns1()).resize(400,550).centerCrop().into(sec1ImageView);
                        Picasso.get().load(question.options.getAns2()).resize(400,550).centerCrop().into(sec2ImageView);
//                        primaryQuestion.setText(question.getQuestion());
//                        option1.setText(question.getOptions().getOption1());
//                        option2.setText(question.getOptions().getOption2());
                    }else{
                        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()) {
                                    Map<String, Object> data = new HashMap<>();
                                    String myResponseStr = "";

                                    currentTask = new Task(
                                            (boolean) documentSnapshot.getData().get("complete"),
                                            (List<Integer>) documentSnapshot.getData().get("ques"),
                                            (String) documentSnapshot.getData().get("firstPlayer"),
                                            (String) documentSnapshot.getData().get("secondPlayer"),
                                            documentSnapshot.getId(),
                                            (String) documentSnapshot.getData().get("firstPlayerResponse"),
                                            (String) documentSnapshot.getData().get("secondPlayerResponse")
                                    );
                                    for (int i : responses) myResponseStr += i;
                                    if (currentTask.getFirstPlayerResponse().isEmpty()) {
                                        data.put("firstPlayerResponse", myResponseStr);
                                    } else {
                                        data.put("secondPlayerResponse", myResponseStr);
                                        data.put("complete", true);
                                    }
                                    docRef.update(data)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    HomeFragment homeFragment = new HomeFragment();
                                                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).addToBackStack(null).commit();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {

                                                }
                                            });
                                }

                            }
                        });
                    }
                }
            }

        });

        sec1ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                responses[questionNum] = 0;
            }
        });

        sec2ImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                responses[questionNum] = 1;
            }
        });

        docRef = allTasks.document(docID);



//        ImageView primaryImageView, secondaryImageView;
        primaryImageView = view.findViewById(R.id.primaryImageView);
//        secondaryImageView = view.findViewById(R.id.secondaryImageView);

//        Random random = new Random();
//        primaryImageView.setImageResource(MainActivity.primaryImages.get((random.nextInt(10))));

//        secondaryImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Toast.makeText(getActivity().getApplicationContext(), "RG", Toast.LENGTH_SHORT).show();
//            }
//        });

        docRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            currentTask = new Task(
                                    (boolean) documentSnapshot.getData().get("complete"),
                                    (List<Integer>) documentSnapshot.getData().get("ques"),
                                    (String) documentSnapshot.getData().get("firstPlayer"),
                                    (String) documentSnapshot.getData().get("secondPlayer"),
                                    documentSnapshot.getId(),
                                    (String) documentSnapshot.getData().get("firstPlayerResponse"),
                                    (String) documentSnapshot.getData().get("secondPlayerResponse")
                            );

                            String questionIndexes = currentTask.getQues().toString().replace(" ", "");
                            String newquestionIndexes = questionIndexes.substring(1, questionIndexes.length()-1);
                            String[] elements = newquestionIndexes.split(",");
                            indices = Arrays.asList(elements);

                            Primary question = questions.get(Integer.parseInt(indices.get(questionNum)));

                            Picasso.get().load(question.question).resize(600,600).centerCrop().into(primaryImageView);
                            Picasso.get().load(question.options.getAns1()).resize(400,550).centerCrop().into(sec1ImageView);
                            Picasso.get().load(question.options.getAns2()).resize(400,550).centerCrop().into(sec2ImageView);
//                            primaryQuestion.setText(question.getQuestion());
//                            option1.setText(question.getOptions().getOption1());
//                            option2.setText(question.getOptions().getOption2());
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

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
