package com.example.mcx;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Map;
import java.util.Random;


public class User extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    public static final String Database_Path = "Profile";
    private static final String CHAR_LIST =
            "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private SecureRandom random = new SecureRandom();

    TextView user1,email1,refer1;
    String user,email,refer;
    Button logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        user1=findViewById(R.id.user);
        email1=findViewById(R.id.email);
        refer1=findViewById(R.id.refer3);
        logout=findViewById(R.id.logout);

        myRef = FirebaseDatabase.getInstance().getReference(Database_Path);
        final String StudentRecordIDFromServer = myRef.push().getKey();
       myRef.child(StudentRecordIDFromServer);
       myRef.addChildEventListener(new ChildEventListener() {
           @Override
           public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
               Map<String, String> newPost = (Map<String, String>) dataSnapshot.getValue();
               String user=newPost.get("name");
               String email=newPost.get("mobile");
               String referral=newPost.get("refer");
               user1.setText(user);
               email1.setText(email);
               refer1.setText(referral);





           }

           @Override
           public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

           }

           @Override
           public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

           }

           @Override
           public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });

    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent intent=new Intent(User.this,FirstPage.class);
    }
}
