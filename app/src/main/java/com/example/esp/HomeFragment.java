package com.example.esp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Tasks;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeFragment extends Fragment {

    Adapter adapter;
    RecyclerView recyclerView;
    FloatingActionButton fab;

    Player player;
    String playerID;
    String docID;

    FirebaseFirestore root = FirebaseFirestore.getInstance();
    CollectionReference allPlayers = root.collection("Players");
    CollectionReference allTasks = root.collection("Tasks");

    ArrayList<Tasks> tasks = new ArrayList<>();

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        playerID = getArguments().getString("player");
        docID = getArguments().getString("id");
        recyclerView = view.findViewById(R.id.recyclerView);
        fab = view.findViewById(R.id.fab);

        allPlayers.whereEqualTo(playerId, playerID)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        DocumentSnapshot snapshot = queryDocumentSnapshots.getDocuments().get(0);
                        player = new Player((String) snapshot.get(playerId)
                                , (long)snapshot.get(score),
                                (HashMap<String, Player>) snapshot.get(paired players));

                        allTasks.get()
                                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                    @Override
                                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                                        for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {

                                            if ((boolean)snapshot.getData().get(completed) == false &&
                                                    (snapshot.getData().get(player1).equals(playerID) ||
                                                            snapshot.getData().get(player2).equals(playerID))) {

                                                tasks.add(new Task(
                                                        (boolean) snapshot.getData().get(completed),
                                                        (List<Integer>) snapshot.getData().get(questions),
                                                        (String) snapshot.getData().get(player1),
                                                        (String) snapshot.getData().get(player2),
                                                        snapshot.getId())
                                                );

                                            }

                                        }

                                        adapter = new Adapter(tasks, playerID);
                                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                                        recyclerView.setAdapter(adapter);



                                        //setTaskListener();

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getContext(), "ERROR", Toast.LENGTH_LONG).show();
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
