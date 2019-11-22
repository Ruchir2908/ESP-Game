package com.example.esp;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FrameLayout container;
    public static ArrayList<Integer> primaryImages;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if(count==3){

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = findViewById(R.id.container);

        primaryImages = new ArrayList<>();
        primaryImages.add(R.drawable.image1);
        primaryImages.add(R.drawable.image2);
        primaryImages.add(R.drawable.image3);
        primaryImages.add(R.drawable.image4);
        primaryImages.add(R.drawable.image5);
        primaryImages.add(R.drawable.image6);
        primaryImages.add(R.drawable.image7);
        primaryImages.add(R.drawable.image8);
        primaryImages.add(R.drawable.image9);
        primaryImages.add(R.drawable.image10);





        getSupportFragmentManager().beginTransaction().replace(R.id.container, new SignUpFragment()).addToBackStack(null).commit();

    }
}
