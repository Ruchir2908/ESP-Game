package com.example.esp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class HomeFragment extends Fragment{

    Adapter adapter;
    RecyclerView recyclerView;
    FloatingActionButton fab;

    Player currentPlayer;
    String currentPlayerId;
    String currentDocId;

    FirebaseFirestore root = FirebaseFirestore.getInstance();
    CollectionReference allPlayers = root.collection("Players");
    CollectionReference allTasks = root.collection("Tasks");

    ArrayList<Task> currentTasks = new ArrayList<>();

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        if(getArguments()!=null) {
            currentPlayerId = getArguments().getString("player");
            currentDocId = getArguments().getString("id");
        }
        recyclerView = view.findViewById(R.id.recyclerView);
        fab = view.findViewById(R.id.fab);

        adapter = new Adapter(currentTasks, currentPlayerId);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getContext(), "HERE", Toast.LENGTH_SHORT).show();

                allPlayers.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        int myIndex = 0;
                        for (int i=0; i<queryDocumentSnapshots.getDocuments().size(); i++) {
                            if (queryDocumentSnapshots.getDocuments().get(i).get("playerID").equals(currentPlayerId)) {
                                myIndex = i;
                                break;
                            }
                        }
                        int randomIndex = 0;
                        Random random = new Random();
                        do {
                            randomIndex = random.nextInt((queryDocumentSnapshots.getDocuments().size()));
                        }
                        while (randomIndex == myIndex);

                        DocumentSnapshot snapshot = queryDocumentSnapshots.getDocuments().get(randomIndex);

                        ArrayList<Primary> list = new ArrayList<>();
                        list.addAll(RandomData.getPrimaryImages());
                        List<Integer> indices = new ArrayList<>();
                        HashSet<Integer> checkSet = new HashSet<>();

                        adapter.notifyDataSetChanged();

                        int i=0;
                        while (i<5) {
                            Integer elem = random.nextInt(list.size());
                            if (!checkSet.contains(elem)) {
                                indices.add(elem);
                                checkSet.add(elem);
                                i++;
                            }
                        }

                        Task task = new Task(false, indices, currentPlayerId, (String) snapshot.getData().get("playerID"), "", "", "");

                        allTasks.add(task)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                    }
                                });

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "ERROR", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        allPlayers.whereEqualTo("playerID", currentPlayerId)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        DocumentSnapshot snapshot = queryDocumentSnapshots.getDocuments().get(0);
                        currentPlayer = new Player((String) snapshot.get("playerID"),(long)snapshot.get("score"),(HashMap<String, Player>) snapshot.get("opponents"));


                        allTasks.whereEqualTo("docId", "")
                                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                                    @Override
                                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                                        for (DocumentChange dc : queryDocumentSnapshots.getDocumentChanges()) {
                                            if(dc.getType() == DocumentChange.Type.ADDED){
                                                    if (!((boolean) dc.getDocument().getData().get("complete")) &&
                                                            (dc.getDocument().getData().get("firstPlayer").equals(currentPlayerId) ||
                                                                    dc.getDocument().getData().get("secondPlayer").equals(currentPlayerId))) {

                                                        Task task = new Task(
                                                                (boolean) dc.getDocument().getData().get("complete"),
                                                                (List<Integer>) dc.getDocument().getData().get("ques"),
                                                                (String) dc.getDocument().getData().get("firstPlayer"),
                                                                (String) dc.getDocument().getData().get("secondPlayer"),
                                                                dc.getDocument().getId(),
                                                                (String) dc.getDocument().getData().get("firstPlayerResponse"),
                                                                (String) dc.getDocument().getData().get("secondPlayerResponse"));
                                                        currentTasks.add(task);


                                                        adapter.notifyDataSetChanged();

                                                    }
                                            }else if(dc.getType() == DocumentChange.Type.MODIFIED){
                                                    if ((boolean)dc.getDocument().getData().get("complete") &&
                                                            (dc.getDocument().getData().get("firstPlayer").equals(currentPlayerId) ||
                                                                    dc.getDocument().getData().get("secondPlayer").equals(currentPlayerId))) {

                                                        Task modifiedTask = new Task(
                                                                (boolean) dc.getDocument().getData().get("complete"),
                                                                (List<Integer>) dc.getDocument().getData().get("ques"),
                                                                (String) dc.getDocument().getData().get("firstPlayer"),
                                                                (String) dc.getDocument().getData().get("secondPlayer"),
                                                                dc.getDocument().getId(),
                                                                (String) dc.getDocument().getData().get("firstPlayerResponse"),
                                                                (String) dc.getDocument().getData().get("secondPlayerResponse")
                                                        );

                                                        for (int i=0; i<currentTasks.size(); i++)
                                                            if (modifiedTask.getDocId().equals(currentTasks.get(i).getDocId())) {
                                                                currentTasks.remove(i);

                                                                adapter.notifyDataSetChanged();
                                                                break;
                                                            }

                                                        if (modifiedTask.getFirstPlayerResponse().equals(modifiedTask.getSecondPlayerResponse() )) {
                                                            allPlayers.whereEqualTo("playerID", currentPlayerId)
                                                                    .get()
                                                                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                                                        @Override
                                                                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                                                            DocumentSnapshot snapshot = queryDocumentSnapshots.getDocuments().get(0);
                                                                            Map<String, Object> data = new HashMap<>();
                                                                            data.put("score", currentPlayer.getScore() + 1);
                                                                            allPlayers.document(snapshot.getId()).update(data);
                                                                        }
                                                                    })
                                                                    .addOnFailureListener(new OnFailureListener() {
                                                                        @Override
                                                                        public void onFailure(@NonNull Exception e) {

                                                                        }
                                                                    });
                                                        }

                                                    }
                                            }
                                        }

                                    }
                                });


                        allPlayers.document(currentDocId).addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                                // set score here

                            }
                        });

                        Toast.makeText(getContext(), "OK", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

//        TextView usernameTextView = view.findViewById(R.id.usernameTextView);
//        usernameTextView.setText("RUCHIR");
//        Button start = view.findViewById(R.id.startTaskButton);
//
//        start.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new TaskFragment()).addToBackStack(null).commit();
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
