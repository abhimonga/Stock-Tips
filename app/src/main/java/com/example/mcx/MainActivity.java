package com.example.mcx;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    Handler handler;
    private FirebaseAuth mAuth;
    public DatabaseReference mUsersDatabase;


    public String mcurrentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       FirebaseApp.initializeApp(MainActivity.this);
        mAuth = FirebaseAuth.getInstance();
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                Intent intent = new Intent(MainActivity.this, Recycler.class);
//                startActivity(intent);
//                finish();

                FirebaseUser currentUser = mAuth.getCurrentUser();
                if (currentUser == null) {


                    Intent intent = new Intent(MainActivity.this, FirstPage.class);
                    startActivity(intent);
                    finish();
                }

                else  {
                }

            }
        }, 5000);
       // onStart();
    }

    @Override
    public void onStart() {
        super.onStart();



    }
}

