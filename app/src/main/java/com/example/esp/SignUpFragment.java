package com.example.esp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.HashMap;

public class SignUpFragment extends Fragment {

    FirebaseFirestore root = FirebaseFirestore.getInstance();
    CollectionReference players = root.collection("Players");

    EditText usernameEditText;
    Button loginButton;

    String currentPlayer;

    public SignUpFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void newPlayer(final String playerID){
        players.add(new Player(currentPlayer, 0, new HashMap<String, Player>()))
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Bundle bundle = new Bundle();
                        bundle.putString("player", currentPlayer);
                        bundle.putString("id",playerID);
                        HomeFragment homeFragment = new HomeFragment();
                        homeFragment.setArguments(bundle);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).addToBackStack(null).commit();
//                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).addToBackStack(null).commit();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "ERROR", Toast.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        usernameEditText = view.findViewById(R.id.usernameEditText);
        loginButton = view.findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!usernameEditText.getText().toString().isEmpty()){
                    currentPlayer = usernameEditText.getText().toString();
                    players.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull com.google.android.gms.tasks.Task<QuerySnapshot> task) {
                            if (task.isComplete()) {
                                boolean flag = false;
                                if (task.getResult() != null) {
                                    for (QueryDocumentSnapshot snapshot : task.getResult()) {
                                        if (snapshot!=null && snapshot.getData().get("playerID").equals(currentPlayer)) {
//                                            startTasksActivity(userId, snapshot.getId());
                                            Bundle bundle = new Bundle();
                                            bundle.putString("player", currentPlayer);
                                            bundle.putString("id",snapshot.getId());
                                            HomeFragment homeFragment = new HomeFragment();
                                            homeFragment.setArguments(bundle);
                                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).addToBackStack(null).commit();
                                            flag = true;
                                            break;
                                        }else{
                                            Toast.makeText(getContext(), "erororor", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    if (!flag){
                                        newPlayer(currentPlayer);
                                    }
                                }
                                else{
                                    newPlayer(currentPlayer);
                                }
                            } else {
                                Toast.makeText(getContext(), "ERROR", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(getContext(), "ENTER USERNAME", Toast.LENGTH_SHORT).show();
                }
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
